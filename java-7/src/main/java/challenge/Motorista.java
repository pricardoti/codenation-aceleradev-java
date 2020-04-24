package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;
    private final String habilitacao;

    private final int idade;
    private final int pontos;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        this.nome = nome;
        this.idade = idade;
        this.pontos = pontos;
        this.habilitacao = habilitacao;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return String.format("Motorista{nome='%s', idade=%s, pontos=%s, habilitacao='%s'}",
                nome, idade, pontos, habilitacao);
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }

    public static class MotoristaBuilder {

        private String nome;
        private String habilitacao;

        private int idade;
        private int pontos;

        private MotoristaBuilder() {
        }

        public MotoristaBuilder withNome(String nome) {
            this.nome = nome;
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            validarParametroInt(idade);
            this.idade = idade;
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            validarParametroInt(pontos);
            this.pontos = pontos;
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            this.habilitacao = habilitacao;
            return this;
        }

        public Motorista build() {
            if (validarCampoString(nome)
                    || validarCampoString(habilitacao))
                throw new NullPointerException("Um ou mais campos obrigatórios não informados ou o valor é inválido.");
            return new Motorista(nome, idade, pontos, habilitacao);
        }

        private boolean validarCampoString(String valor) {
            return valor == null || valor.trim().isEmpty();
        }

        private void validarParametroInt(int valor) {
            if (valor < 0)
                throw new IllegalArgumentException("Valor inválido! são permitidos apenas numeros positivos.");
        }
    }
}
