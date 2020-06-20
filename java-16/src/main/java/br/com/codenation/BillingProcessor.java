package br.com.codenation;

public class BillingProcessor {

    public Double calculate(Order order) {
        return order.getPaymentMethod()
                .getPaymentStrategy()
                .calculate(order.getPrice());
    }
}