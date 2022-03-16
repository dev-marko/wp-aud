package mk.ukim.finki.wpaud.repository.impl;

import mk.ukim.finki.wpaud.bootstrap.DataHolder;
import mk.ukim.finki.wpaud.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Ovaa anotacija se koristi za da mu kazeme na Spring
// deka klasava ke se koristi kako adapter preku koja
// ke pristapuvame do nekoi informacii in storage (bilo je in memory ili database)
@Repository
public class InMemoryCategoryRepository {

    public List<Category> findAll() {
        return DataHolder.categoryList;
    }

    public Category save(Category c) {
        if (c == null || c.getName() == null && c.getName().isEmpty()) {
            return null;
        }
        DataHolder.categoryList.removeIf(i -> i.getName().equals(c.getName()));
        DataHolder.categoryList.add(c);
        return c;
    }
    // Optional - ako ne ja najde da bide null optional, za polesno spravuvanje so exception
    public Optional<Category> findByName(String name) {
        return DataHolder.categoryList.stream().filter(i -> i.getName().equals(name)).findFirst();
    }

    public Optional<Category> findById(Long id) {
        return DataHolder.categoryList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public List<Category> search(String text) {
        return DataHolder.categoryList.stream().filter(i -> i.getName().contains(text) || i.getDescription().contains(text)).collect(Collectors.toList());
    }

    public void delete(String name) {
        if (name == null) {
            return;
        }
        DataHolder.categoryList.removeIf(i -> i.getName().equals(name));
    }
}
