package challenge;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Estacionamento {
    private List<Carro> estacionados = new LinkedList<>();

    public void estacionar(Carro carro) {
        validarMotorista(carro.getMotorista());
        if (estacionados.size() == 10) {
            if (estacionados.get(0).getMotorista().getIdade() > 55) {
                estacionados.remove(1);
            } else {
                estacionados.remove(0);
            }
        }
        estacionados.add(carro);
    }

    public int carrosEstacionados() {
        return Math.min(estacionados.size(), 10);
    }

    public boolean carroEstacionado(Carro carro) {
        return carro.getMotorista().getIdade() > 55;
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
