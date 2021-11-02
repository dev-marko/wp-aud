package mk.ukim.finki.wpaud.repository;

import mk.ukim.finki.wpaud.bootstrap.DataHolder;
import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Manufacturer;
import mk.ukim.finki.wpaud.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll() {
        return DataHolder.productList;
    }

    public Optional<Product> findById(Long id) {
        return DataHolder.productList
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    // bidejki momentalno nemame ID za razlicnite products
    // ovoj save metod ke raboti POGRESNO dokolku editirame postoecki produkt
    // i mu go smenime imeto (nema da moze da se najde i da se izbrise stariot)
    public Optional<Product>  save(String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        DataHolder.productList.removeIf(i -> i.getName().equals(name));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.productList.add(product);
        return Optional.of(product);
    }

    public void deleteById(Long id) {
        DataHolder.productList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Product> findByName(String name) {
        return DataHolder.productList
                .stream()
                .filter(i -> i.getName().equals(name))
                .findFirst();
    }
}
