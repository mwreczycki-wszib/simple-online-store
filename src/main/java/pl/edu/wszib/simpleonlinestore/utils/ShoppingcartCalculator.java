package pl.edu.wszib.simpleonlinestore.utils;

import pl.edu.wszib.simpleonlinestore.model.ShoppingCart;

import java.math.BigDecimal;

public class ShoppingcartCalculator {

    private ShoppingcartCalculator(){

    }

    public static BigDecimal calculateTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getItems()
                .stream()
                .map(item ->
                    BigDecimal.valueOf(item.getAmount())
                            .multiply(item.getProduct().getPrice())
                            .multiply(item.getProduct().getCategory().getTaxRate().getRate().add(BigDecimal.ONE))
                ).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

}
