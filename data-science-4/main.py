#!/usr/bin/env python
# coding: utf-8

# # Desafio 6
# 
# Neste desafio, vamos praticar _feature engineering_, um dos processos mais importantes e trabalhosos de ML. Utilizaremos o _data set_ [Countries of the world](https://www.kaggle.com/fernandol/countries-of-the-world), que contém dados sobre os 227 países do mundo com informações sobre tamanho da população, área, imigração e setores de produção.
# 
# > Obs.: Por favor, não modifique o nome das funções de resposta.

# ## _Setup_ geral

# In[1]:


import pandas as pd
import numpy as np

from sklearn.datasets import fetch_20newsgroups

from sklearn.compose import ColumnTransformer
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
from sklearn.impute import SimpleImputer
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import KBinsDiscretizer, OneHotEncoder, StandardScaler


# In[1]:


countries = pd.read_csv("countries.csv", decimal=',')


# In[1]:


new_column_names = [
    "Country", "Region", "Population", "Area", "Pop_density", "Coastline_ratio",
    "Net_migration", "Infant_mortality", "GDP", "Literacy", "Phones_per_1000",
    "Arable", "Crops", "Other", "Climate", "Birthrate", "Deathrate", "Agriculture",
    "Industry", "Service"
]

countries.columns = new_column_names

countries.head(5)


# ## Observações
# 
# Esse _data set_ ainda precisa de alguns ajustes iniciais. Primeiro, note que as variáveis numéricas estão usando vírgula como separador decimal e estão codificadas como strings. Corrija isso antes de continuar: transforme essas variáveis em numéricas adequadamente.
# 
# Além disso, as variáveis `Country` e `Region` possuem espaços a mais no começo e no final da string. Você pode utilizar o método `str.strip()` para remover esses espaços.

# ## Inicia sua análise a partir daqui

# In[1]:


# Sua análise começa aqui.
net_migration = countries['Net_migration'].dropna()
quartile_1, quartile_3 = np.quantile(net_migration, [0.25, 0.75])
inter_quartile = quartile_3 - quartile_1
lower_bound = quartile_1 - (1.5 * inter_quartile)
upper_bound = quartile_3 + (1.5 * inter_quartile)

lowers = (net_migration < lower_bound).sum()
uppers = (net_migration > upper_bound).sum()
(lowers, uppers)


# ## Questão 1
# 
# Quais são as regiões (variável `Region`) presentes no _data set_? Retorne uma lista com as regiões únicas do _data set_ com os espaços à frente e atrás da string removidos (mas mantenha pontuação: ponto, hífen etc) e ordenadas em ordem alfabética.

# In[1]:


def q1():
    regions = countries['Region'].unique()
    stripped_from = np.vectorize(lambda name: name.strip())
    normalized = stripped_from(regions)
    normalized.sort()
    return normalized.tolist()


# ## Questão 2
# 
# Discretizando a variável `Pop_density` em 10 intervalos com `KBinsDiscretizer`, seguindo o encode `ordinal` e estratégia `quantile`, quantos países se encontram acima do 90º percentil? Responda como um único escalar inteiro.

# In[1]:


def q2():
    data = countries['Pop_density'].values.reshape(-1, 1)
    discretizer = KBinsDiscretizer(n_bins=10, encode='ordinal', strategy='quantile')
    bins = discretizer.fit_transform(data)
    count = (bins == 9).sum()
    return count.item()


# # Questão 3
# 
# Se codificarmos as variáveis `Region` e `Climate` usando _one-hot encoding_, quantos novos atributos seriam criados? Responda como um único escalar.

# In[1]:


def q3():
    data = countries[['Region', 'Climate']]
    processed = data.fillna({ 'Climate': 0 })
    encoded = OneHotEncoder().fit_transform(processed)
    return encoded.shape[1]


# ## Questão 4
# 
# Aplique o seguinte _pipeline_:
# 
# 1. Preencha as variáveis do tipo `int64` e `float64` com suas respectivas medianas.
# 2. Padronize essas variáveis.
# 
# Após aplicado o _pipeline_ descrito acima aos dados (somente nas variáveis dos tipos especificados), aplique o mesmo _pipeline_ (ou `ColumnTransformer`) ao dado abaixo. Qual o valor da variável `Arable` após o _pipeline_? Responda como um único float arredondado para três casas decimais.

# In[1]:


test_country = [
    'Test Country', 'NEAR EAST', -0.19032480757326514,
    -0.3232636124824411, -0.04421734470810142, -0.27528113360605316,
    0.13255850810281325, -0.8054845935643491, 1.0119784924248225,
    0.6189182532646624, 1.0074863283776458, 0.20239896852403538,
    -0.043678728558593366, -0.13929748680369286, 1.3163604645710438,
    -0.3699637766938669, -0.6149300604558857, -0.854369594993175,
    0.263445277972641, 0.5712416961268142
]


# In[1]:


def q4():
    selected = countries.select_dtypes('number').columns
    pipeline = Pipeline(steps=[
        ('imputing', SimpleImputer(strategy='median')),
        ('scaling', StandardScaler())
    ])
    preprocessing = ColumnTransformer(
        transformers=[('median_padronize', pipeline, selected)],
        remainder='drop'
    )
    preprocessing.fit(countries)
    input = pd.DataFrame([test_country], columns=countries.columns)
    output = preprocessing.transform(input)
    index_of_arable = selected.get_loc('Arable')
    arable = output[0][index_of_arable]
    return arable.round(3).item()


# ## Questão 5
# 
# Descubra o número de _outliers_ da variável `Net_migration` segundo o método do _boxplot_, ou seja, usando a lógica:
# 
# $$x \notin [Q1 - 1.5 \times \text{IQR}, Q3 + 1.5 \times \text{IQR}] \Rightarrow x \text{ é outlier}$$
# 
# que se encontram no grupo inferior e no grupo superior.
# 
# Você deveria remover da análise as observações consideradas _outliers_ segundo esse método? Responda como uma tupla de três elementos `(outliers_abaixo, outliers_acima, removeria?)` ((int, int, bool)).

# In[1]:


def q5():
    net_migration = countries['Net_migration'].dropna()
    quartile_1, quartile_3 = np.quantile(net_migration, [0.25, 0.75])
    inter_quartile = quartile_3 - quartile_1
    lower_bound = quartile_1 - (1.5 * inter_quartile)
    upper_bound = quartile_3 + (1.5 * inter_quartile)

    lowers = (net_migration < lower_bound).sum()
    uppers = (net_migration > upper_bound).sum()
    # Procurando critérios de eliminação de outlier, encontrei o critério de Peirce mas ainda não sei utilizá-lo
    return (lowers.item(), uppers.item(), False)


# ## Questão 6
# Para as questões 6 e 7 utilize a biblioteca `fetch_20newsgroups` de datasets de test do `sklearn`
# 
# Considere carregar as seguintes categorias e o dataset `newsgroups`:
# 
# ```
# categories = ['sci.electronics', 'comp.graphics', 'rec.motorcycles']
# newsgroup = fetch_20newsgroups(subset="train", categories=categories, shuffle=True, random_state=42)
# ```
# 
# 
# Aplique `CountVectorizer` ao _data set_ `newsgroups` e descubra o número de vezes que a palavra _phone_ aparece no corpus. Responda como um único escalar.

# In[1]:


categories = ['sci.electronics', 'comp.graphics', 'rec.motorcycles']
newsgroup = fetch_20newsgroups(subset="train",
                               categories=categories,
                               shuffle=True, random_state=42)

def q6():
    vectorizing = CountVectorizer()
    vectorizing.fit(newsgroup.data)
    processed = vectorizing.transform(newsgroup.data)
    index_of_phone = vectorizing.vocabulary_['phone']
    phone_count = processed[:, index_of_phone].sum()
    return phone_count.item()


# ## Questão 7
# 
# Aplique `TfidfVectorizer` ao _data set_ `newsgroups` e descubra o TF-IDF da palavra _phone_. Responda como um único escalar arredondado para três casas decimais.

# In[1]:


def q7():
    vectorizing = TfidfVectorizer()
    vectorizing.fit(newsgroup.data)
    processed = vectorizing.transform(newsgroup.data)
    index_of_phone = vectorizing.vocabulary_['phone']
    phone_frequency = processed[:, index_of_phone].sum()
    return phone_frequency.round(3).item()


# In[ ]:




