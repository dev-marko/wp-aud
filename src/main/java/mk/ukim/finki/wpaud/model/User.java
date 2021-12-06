package mk.ukim.finki.wpaud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="shop_users")
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
