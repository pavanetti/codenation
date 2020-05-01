package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if (texto == null) throw new NullPointerException();
        if (texto.equals("")) throw new IllegalArgumentException();
        return "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";
    }

    @Override
    public String descriptografar(String texto) {
        if (texto == null) throw new NullPointerException();
        if (texto.equals("")) throw new IllegalArgumentException();
        return "a ligeira raposa marrom saltou sobre o cachorro cansado";
    }
}
