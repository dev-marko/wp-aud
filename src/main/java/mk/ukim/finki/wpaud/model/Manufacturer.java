package mk.ukim.finki.wpaud.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name="manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="manufacturer_address")
    private String address;

    public Manufacturer(String name, String address) {
        //this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.address = address;
    }
}
