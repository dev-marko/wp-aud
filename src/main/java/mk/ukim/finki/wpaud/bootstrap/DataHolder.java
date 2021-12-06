package mk.ukim.finki.wpaud.bootstrap;

import mk.ukim.finki.wpaud.model.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

// Za ova da bide Singleton, odnosno da se napravi samo
// edna instanca koga ke ja startuvame aplikacijata
// mora da ja iskoristime anotacijata Component

@Component
public class DataHolder {
    public static List<Category> categoryList = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Manufacturer> manufacturerList = new ArrayList<>();
    public static List<Product> productList = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    // Shtom ke se instancira klasava, ke se povika metodov
    // za da se popolnat nekoi parametri (kako da povikuva konstruktor)
//    @PostConstruct
//    public void init() {
//        categoryList.add(new Category("Software", "Software desc"));
//        categoryList.add(new Category("Books", "Books desc"));
//        categoryList.add(new Category("Hardware", "Hardware desc"));
//
//        userList.add(new User("marko123", "ms", "Marko", "Spasenovski"));
//        userList.add(new User("andrej123", "aa", "Andrej", "Acevski"));
//
//        Manufacturer manufacturer = new Manufacturer("Nike", "Queens, Brooklyn");
//        manufacturerList.add(manufacturer);
//
//        Category category = new Category("Sport", "Sport category");
//        categoryList.add(category);
//
//        productList.add(new Product("Basketball", 20.0, 9, category, manufacturer));
//        productList.add(new Product("Soccer ball", 15.0, 9, category, manufacturer));
//        productList.add(new Product("Tennis Racket", 30.0, 9, category, manufacturer));
//
//    }
}
