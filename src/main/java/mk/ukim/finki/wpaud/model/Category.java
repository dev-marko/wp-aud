package mk.ukim.finki.wpaud.model;

import lombok.Data;

// Data anotacijata ni sluzi za avtomatsko generiranje na
// getter-i i setter-i. Taa e del od Lombok paketot

@Data
public class Category {
    private Long id;
    private String name;
    private String description;

    public Category(String name, String description) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }
}
