package com.challenge.interfaces;

import java.math.BigDecimal;

public interface Calculavel {

    /**
     * Esta operacao realiza a soma dos atributos que possuem a annotation (“Somar”)
     * para determinar quais atributos serão somados em uma determinada classe.
     */
    BigDecimal somar(Object objeto);

    /**
     * Esta operacao realiza a subtrair dos atributos que possuem a annotation (“Subtrair”)
     * para determinar quais atributos serão subtraidos em uma determinada classe.
     */
    BigDecimal subtrair(Object objeto);

    /**
     * Devera subtrair os atributos annotados com “Subtrair” dos atributos annotados com “Somar”.
     */
    BigDecimal totalizar(Object objeto);
}
