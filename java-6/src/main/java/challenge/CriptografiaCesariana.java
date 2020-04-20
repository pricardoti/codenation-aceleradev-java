package challenge;

public class CriptografiaCesariana implements Criptografia {

    private final int SALT = 3;

    @Override
    public String criptografar(String texto) {
        validateContent(texto);

        char[] letters = texto.toLowerCase().toCharArray();

        StringBuilder output = new StringBuilder();
        for (char letter : letters) {
            int ascii = (int) letter;
            if (isValidCodeAscii(ascii)) {
                ascii = toPostionOrigin((toPositionInAlphabet(ascii) + SALT) % 26);
            }
            output.append((char) ascii);
        }

        return output.toString();
    }

    @Override
    public String descriptografar(String texto) {
        validateContent(texto);

        char[] letters = texto.toLowerCase().toCharArray();

        StringBuilder output = new StringBuilder();
        for (char letter : letters) {
            int ascii = (int) letter;
            if (isValidCodeAscii(ascii)) {
                ascii = toPostionOrigin(((toPositionInAlphabet(ascii) - SALT) % 26));
            }
            output.append((char) ascii);
        }

        return output.toString();
    }

    private void validateContent(String texto) {
        if (texto.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidCodeAscii(int ascii) {
        return ascii >= 97 && ascii <= 122;
    }

    private int toPositionInAlphabet(int ascii) {
        return ascii - 97;
    }

    private int toPostionOrigin(int result) {
        return ((result < 0) ? 26 + result : result) + 97;
    }
}