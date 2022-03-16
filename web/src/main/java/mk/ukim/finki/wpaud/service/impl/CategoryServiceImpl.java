package mk.ukim.finki.wpaud.service.impl;

import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.repository.jpa.CategoryRepository;
import mk.ukim.finki.wpaud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Sekoj service mora da se anotira so Service anotacijata
// za Spring da go prepoznae
@Service
public class CategoryServiceImpl implements CategoryService {

    // Pozelno e site Dependencies da bidat final
    private final CategoryRepository categoryRepository;

    // Constructor based Dependency Injection
    @Autowired
    public CategoryServiceImpl (CategoryRepository cR) {
        this.categoryRepository = cR;
    }

    @Override
    public Category create(String name, String description) {
        // Vakvi constraints treba da bidat napisani vo service slojot
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c); // save metodot e update safe
        return c;
    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
