package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if (texto == null) throw new NullPointerException();
        throw new IllegalArgumentException();
    }

    @Override
    public String descriptografar(String texto) {
        if (texto == null) throw new NullPointerException();
        throw new IllegalArgumentException();
    }
}
