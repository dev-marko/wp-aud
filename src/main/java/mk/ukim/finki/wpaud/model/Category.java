package mk.ukim.finki.wpaud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Data anotacijata ni sluzi za avtomatsko generiranje na
// getter-i i setter-i. Taa e del od Lombok paketot

@Data
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 4000)
    private String description;

    public Category(String name, String description) {
        //this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
    }
}
