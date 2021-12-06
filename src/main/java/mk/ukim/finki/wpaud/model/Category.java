package mk.ukim.finki.wpaud.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Data anotacijata ni sluzi za avtomatsko generiranje na
// getter-i i setter-i. Taa e del od Lombok paketot


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 4000)
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
