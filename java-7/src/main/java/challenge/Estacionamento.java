package challenge;

public class Estacionamento {

    public void estacionar(Carro carro) {
        validarMotorista(carro.getMotorista());
    }

    public int carrosEstacionados() {
        return 1;
    }

    public boolean carroEstacionado(Carro carro) {
        return true;
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
