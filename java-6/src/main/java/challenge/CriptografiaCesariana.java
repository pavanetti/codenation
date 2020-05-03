package challenge;

import java.util.function.Function;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if (texto == null)
            throw new NullPointerException();
        if (texto.equals(""))
            throw new IllegalArgumentException();
        return shift(texto.toLowerCase(), 3);
    }

    @Override
    public String descriptografar(String texto) {
        if (texto == null)
            throw new NullPointerException();
        if (texto.equals(""))
            throw new IllegalArgumentException();
        return shift(texto.toLowerCase(), -3);
    }

    private String shift(String text, int key) {
        Function<Character, Boolean> isAlpha = c -> c >= 'a' && c <= 'z';
        return text.chars().map(c -> isAlpha.apply((char) c) ? c + key : c)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }
}
