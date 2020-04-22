package br.com.codenation;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.Jogador;
import br.com.codenation.model.Time;
import br.com.codenation.util.ValidatorUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private List<Time> times = new ArrayList<>();
    private List<Jogador> jogadores = new ArrayList<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        ValidatorUtil.validarValorId(id);
        ValidatorUtil.validarCamposObrigatorios(nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        ValidatorUtil.validarIdDuplicado(id, times);
        times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        ValidatorUtil.validarValorId(id);
        ValidatorUtil.validarCamposObrigatorios(nome, dataNascimento, nivelHabilidade, salario);

        if (nivelHabilidade < 0 || nivelHabilidade > 100) {
            throw new IllegalArgumentException("O nivel de habilidade informado é inválido !");
        }

        if (salario.compareTo(BigDecimal.ZERO) < 1) {
            throw new IllegalArgumentException("O salário informado é inválido !");
        }

        if (!ValidatorUtil.isIdExiste(idTime, times)) {
            throw new TimeNaoEncontradoException("O id do time informado não foi encontrado.");
        }

        ValidatorUtil.validarIdDuplicado(id, jogadores);

        jogadores.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {
        Jogador jogador = buscarJogadorPorId(idJogador);

        if (jogador == null) {
            throw new JogadorNaoEncontradoException();
        }

        jogadores = jogadores.stream().map(element -> {
            if (element.getIdTime().equals(jogador.getIdTime())) {
                boolean capitao = element.equals(jogador);
                element.setCapitao(capitao);
            }
            return element;
        }).collect(Collectors.toList());
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        if (!ValidatorUtil.isIdExiste(idTime, times)) {
            throw new TimeNaoEncontradoException("O id do time informado não foi encontrado.");
        }

        Optional<Jogador> resultado = jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime) && jogador.isCapitao()).findFirst();
        if (!resultado.isPresent()) {
            throw new CapitaoNaoInformadoException();
        }

        return resultado.get().getId();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        Jogador jogador = buscarJogadorPorId(idJogador);
        if (jogador == null) {
            throw new JogadorNaoEncontradoException();
        }
        return jogador.getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        Time time = buscarTimePorId(idTime);
        if (time == null) {
            throw new TimeNaoEncontradoException();
        }
        return time.getNome();
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

    private Time buscarTimePorId(Long id) {
        Optional<Time> resultado = times.stream().filter(time -> time.getId().equals(id)).findFirst();
        return resultado.isPresent() ? resultado.get() : null;
    }

    private Jogador buscarJogadorPorId(Long id) {
        Optional<Jogador> resultado = jogadores.stream().filter(jogador -> jogador.getId().equals(id)).findFirst();
        return resultado.isPresent() ? resultado.get() : null;
    }
}
