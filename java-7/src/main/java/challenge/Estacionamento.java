package challenge;

import java.util.ArrayList;

public class Estacionamento {
    private int estacionados = 0;

    public void estacionar(Carro carro) {
        validarMotorista(carro.getMotorista());
        estacionados++;
    }

    public int carrosEstacionados() {
        return Math.min(estacionados, 10);
    }

    public boolean carroEstacionado(Carro carro) {
        return false;
    }

    private void validarMotorista(Motorista motorista) {
        if (motorista == null)
            throw new EstacionamentoException("Não pode ter carro autônomo");
        if (motorista.getIdade() < 18)
            throw new EstacionamentoException("Não pode ter motorista menor de idade");
        if (motorista.getPontos() > 20)
            throw new EstacionamentoException("Não pode ter motorista com habilitação suspensa");
    }
}
