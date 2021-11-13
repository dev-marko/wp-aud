package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.ShoppingCart;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wpaud.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.wpaud.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wpaud.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wpaud.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wpaud.repository.InMemoryShoppingCartRepository;
import mk.ukim.finki.wpaud.repository.InMemoryUserRepository;
import mk.ukim.finki.wpaud.service.ProductService;
import mk.ukim.finki.wpaud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository inMemoryShoppingCartRepository;
    private final InMemoryUserRepository inMemoryUserRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository inMemoryShoppingCartRepository, InMemoryUserRepository inMemoryUserRepository, ProductService productService) {
        this.inMemoryShoppingCartRepository = inMemoryShoppingCartRepository;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInCart(Long cartId) {
        if (this.inMemoryShoppingCartRepository.findById(cartId).isEmpty()) {
            throw new ShoppingCartNotFoundException(cartId);
        }

        return this.inMemoryShoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveCart(String username) {
        return this.inMemoryShoppingCartRepository.findByUsernameAndStatus(username, ShoppingCartStatus.CREATED).orElseGet(() -> {
            User user = this.inMemoryUserRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
            ShoppingCart shoppingCart = new ShoppingCart(user);
            return this.inMemoryShoppingCartRepository.save(shoppingCart);
        });
    }

    @Override
    public ShoppingCart addProductToCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveCart(username);
        Product product = this.productService.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        if (shoppingCart.getProducts().stream().anyMatch(i -> i.getId().equals(productId))){
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }

        shoppingCart.getProducts().add(product);

        return this.inMemoryShoppingCartRepository.save(shoppingCart);
    }
}
