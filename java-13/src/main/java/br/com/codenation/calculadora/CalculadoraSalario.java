package br.com.codenation.calculadora;


public class CalculadoraSalario {

    public long calcularSalarioLiquido(double salarioBase) {
        double resultado = salarioBase;
        if (resultado < 1039.0) {
            resultado = 0.0;
        } else {
            resultado -= calcularInss(resultado);
            resultado -= calcularIRRF(resultado);
        }
        return Math.round(resultado);
    }

    public double calcularInss(double salarioBase) {
        double aliquota;
        if (salarioBase < 1500.01) {
            aliquota = 0.08;
        } else if (salarioBase < 4000.01) {
            aliquota = 0.09;
        } else {
            aliquota = 0.11;
        }
        return salarioBase * aliquota;
    }

    public double calcularIRRF(double salarioBase) {
        double aliquota;
        if (salarioBase < 3000.01) {
            aliquota = 0.0;
        } else if (salarioBase < 6000.01) {
            aliquota = 0.075;
        } else {
            aliquota = 0.15;
        }
        return salarioBase * aliquota;
    }
}