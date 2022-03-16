package mk.ukim.finki.wpaud.service;

import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Product> listAllProductsInCart(Long cartId);
    ShoppingCart getActiveCart(String username);
    ShoppingCart addProductToCart(String username, Long productId);
}
