package challenge;

import java.util.LinkedList;
import java.util.List;

public class Estacionamento {

    private static final int TOTAL_MAXIMO_PONTOS = 21;
    private static final int CAPACIDADE_MAXIMA = 10;
    private static final int IDADE_MINIMA_HABILITACAO = 18;

    List<Carro> carros = new LinkedList<>();

    public void estacionar(Carro carro) {
        if (carro.getMotorista() == null
                || carro.getPlaca() == null
                || carro.getCor() == null
                || carro.getMotorista().getPontos() > TOTAL_MAXIMO_PONTOS
                || carro.getMotorista().getIdade() < IDADE_MINIMA_HABILITACAO)
            throw new EstacionamentoException("Não foi possivel estacionar.");

        if (carros.size() >= CAPACIDADE_MAXIMA) {
            Carro carroSaida = carros.stream()
                    .filter(carroEstacionado -> carroEstacionado.getMotorista().getIdade() < 56)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("Não há vagas disponíveis, estacionamento lotado!"));
            carros.remove(carroSaida);
        }

        carros.add(carro);
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }
}
