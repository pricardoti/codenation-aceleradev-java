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
import java.util.Collections;
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
        jogadores = jogadores.stream().map(element -> {
            if (element.getIdTime().equals(jogador.getIdTime()))
                element.setCapitao(element.equals(jogador));
            return element;
        }).collect(Collectors.toList());
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        buscarTimePorId(idTime);
        Optional<Jogador> resultado = jogadores.stream()
                .filter(jogador -> jogador.getIdTime().equals(idTime) && jogador.isCapitao()).findFirst();
        if (!resultado.isPresent())
            throw new CapitaoNaoInformadoException();
        return resultado.get().getId();
    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        return buscarJogadorPorId(idJogador).getNome();
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {
        return buscarTimePorId(idTime).getNome();
    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {
        buscarTimePorId(idTime);
        List<Long> idsJogadoresTime = new ArrayList<>();
        jogadores.forEach(jogador -> {
            if (jogador.getIdTime().equals(idTime))
                idsJogadoresTime.add(jogador.getId());
        });
        return idsJogadoresTime;
    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        buscarTimePorId(idTime);
        Jogador melhorJogador=  null;
        for (Jogador jogador : jogadores) {
            if (melhorJogador == null || (jogador.getIdTime().equals(idTime)
                    && jogador.getNivelHabilidade().compareTo(melhorJogador.getNivelHabilidade()) > 0))
                melhorJogador = jogador;
        };
        return melhorJogador.getId();
    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        buscarTimePorId(idTime);
        Jogador jogadorMaisVelho = null;
        for (Jogador jogador : jogadores) {
            if (jogadorMaisVelho == null  || (jogador.getIdTime().equals(idTime)
                    && jogador.getDataNascimento().isBefore(jogadorMaisVelho.getDataNascimento())))
                jogadorMaisVelho = jogador;
        };
        return jogadorMaisVelho.getId();
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
        if (!resultado.isPresent())
            throw new TimeNaoEncontradoException();
        return resultado.get();
    }

    private Jogador buscarJogadorPorId(Long id) {
        Optional<Jogador> resultado = jogadores.stream().filter(jogador -> jogador.getId().equals(id)).findFirst();
        if (!resultado.isPresent())
            throw new JogadorNaoEncontradoException();
        return resultado.get();
    }
}
