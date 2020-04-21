package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.model.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private List<Time> times = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (isEmpty(id) || isEmpty(nome) || isEmpty(dataCriacao) || isEmpty(corUniformePrincipal) || isEmpty(corUniformeSecundario)) {
            throw new IllegalArgumentException("Um ou mais campos obrigatórios não informado !");
        }

        Time time = buscarTimePorId(id);
        if (!isEmpty(time)) {
            throw new IdentificadorUtilizadoException();
        }

        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }


    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        throw new UnsupportedOperationException();
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        Time time = buscarTimePorId(idTime);
        return isEmpty(time) ? null : time.getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        throw new UnsupportedOperationException();
    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        throw new UnsupportedOperationException();
    }

    private boolean isEmpty(Object arg) {
        return arg == null || arg.toString().trim().isEmpty();
    }

    private Time buscarTimePorId(Long id) {
        Optional<Time> resultado = times.stream().filter(time -> time.getId().equals(id)).findFirst();
        return resultado.isPresent() ? resultado.get() : null;
    }
}
