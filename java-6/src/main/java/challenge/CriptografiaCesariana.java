package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if (texto == null) throw new NullPointerException();
        if (texto.equals("")) throw new IllegalArgumentException();
        return shift(texto.toLowerCase(), 3);
    }

    @Override
    public String descriptografar(String texto) {
        if (texto == null) throw new NullPointerException();
        if (texto.equals("")) throw new IllegalArgumentException();
        return shift(texto.toLowerCase(), -3);
    }

    private String shift(String text, int key) {
        return text
            .chars()
            .map(c -> c >= 'a' && c <= 'z' ? c + key : c)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
