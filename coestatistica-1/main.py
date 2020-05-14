import pandas as pd


def exercise():
    data = pd.read_csv('desafio1.csv')
    score_by_state = data.groupby('estado_residencia')['pontuacao_credito']
    mode = score_by_state.agg(pd.Series.mode)
    median = score_by_state.median()
    mean = score_by_state.mean()
    std = score_by_state.std()
    final = pd.DataFrame({'moda': mode, 'mediana': median,
                          'media': mean, 'desvio_padrao': std})
    final.to_json('submission.json', orient='index')


if __name__ == '__main__':
    exercise()
