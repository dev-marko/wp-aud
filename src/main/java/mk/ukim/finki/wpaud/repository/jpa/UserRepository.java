package mk.ukim.finki.wpaud.repository.jpa;

import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.enumerations.Role;
import mk.ukim.finki.wpaud.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // EntityGraphType.FETCH -> site atributi sto ke gi specificiram ke bidat tretirani so EAGER, a site koi ne se specificirani ke bidat tretirani so LAZY load
    // EntityGraphType.LOAD -> site atributi sto ke gi specificiram ke bidat tretirani so EAGER, a site koi ne se specificirani ke bidat tretirani po nivniot default setting
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"carts"})
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"carts", "discount"})
    @Query("select u from User u")
    List<User> loadAll();

    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

    UserProjection findByRole(Role role);
}
