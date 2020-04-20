package br.com.codenation.calculadora;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculadoraSalarioTest {

    private CalculadoraSalario calculadoraSalario;

    @Before
    public void before() {
        calculadoraSalario = new CalculadoraSalario();
    }

    @Test()
    public void salarioLiquidoIsNotNull() {
        assertNotNull(calculadoraSalario.calcularSalarioLiquido(1000.0));
    }

    @Test()
    public void salarioAbaixoDoMinimo() {
        assertEquals(0.0, calculadoraSalario.calcularSalarioLiquido(1000.0), 0.0);
    }

    @Test()
    public void salarioLiquido() {
        assertEquals(1380.0, calculadoraSalario.calcularSalarioLiquido(1500.0), 0.0);
        assertEquals(7565.00, calculadoraSalario.calcularSalarioLiquido(10000.0), 0.0);
    }

    @Test
    public void calcularDescontoInss() {
        assertEquals(80.0, calculadoraSalario.calcularInss(1000.0), 0.0);
        assertEquals(120.0, calculadoraSalario.calcularInss(1500.0), 0.0);
        assertEquals(135.09, calculadoraSalario.calcularInss(1501.0), 135.09);
        assertEquals(360.0, calculadoraSalario.calcularInss(4000.0), 360.0);
        assertEquals(1100.0, calculadoraSalario.calcularInss(10000.0), 1100.0);
    }

    @Test
    public void calcularDescontoIRRF() {
        // ISENTO
        assertEquals(0.0, calculadoraSalario.calcularIRRF(3000.0), 0.0);

        // Percentual: 7.5%
        assertEquals(225.00075, calculadoraSalario.calcularIRRF(3000.01), 0.0);
        assertEquals(337.5, calculadoraSalario.calcularIRRF(4500.00), 0.0);
        assertEquals(450.0, calculadoraSalario.calcularIRRF(6000.00), 0.0);

        // Percentual: 15%
        assertEquals(900.0015, calculadoraSalario.calcularIRRF(6000.01), 0.0);
        assertEquals(1500.0, calculadoraSalario.calcularIRRF(10000.00), 0.0);
    }

}