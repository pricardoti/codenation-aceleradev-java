package br.com.codenation.util;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.model.AbstractModel;

import java.util.List;

public class ValidatorUtil {

    public static void validarValorId(Long id) {
        if (isEmpty(id)) {
            throw new IllegalArgumentException("O valor informado para o 'id' é inválido !");
        }
    }

    public static void validarCamposObrigatorios(Object... args) {
        for (Object arg : args) {
            if (isEmpty(arg)) {
                throw new IllegalArgumentException("Um ou mais campos obrigatórios inválidos!");
            }
        }
    }

    public static void validarIdDuplicado(Long id, List<? extends AbstractModel> itens) {
        if (isIdExiste(id, itens)) {
            throw new IdentificadorUtilizadoException("O 'id' informado já existe!");
        }
    }

    public static boolean isIdExiste(Long id, List<? extends AbstractModel> itens) {
        return itens.stream().filter(iten -> iten.getId().equals(id)).findFirst().isPresent();
    }

    public static boolean isEmpty(Long id) {
        return id == null || id < 0;
    }

    public static boolean isEmpty(Object arg) {
        return arg == null || arg.toString().trim().isEmpty();
    }
}
