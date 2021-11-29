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
//    @OneToMany(mappedBy = "user")
//    private List<ShoppingCart> carts;
}
