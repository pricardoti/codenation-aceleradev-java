package br.com.codenation.paymentmethods;

public class Transfer implements PriceStrategy {
    @Override
    public Double calculate(Double price) {
        return price * 0.92;
    }
}
