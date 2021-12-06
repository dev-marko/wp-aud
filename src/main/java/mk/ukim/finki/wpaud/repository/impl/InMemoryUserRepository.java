package mk.ukim.finki.wpaud.repository.impl;

import mk.ukim.finki.wpaud.bootstrap.DataHolder;
import mk.ukim.finki.wpaud.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {

    public Optional<User> findByUsername(String username) {
        return DataHolder.userList.stream().filter(i -> i.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.userList.stream().filter(i -> i.getUsername().equals(username) && i.getPassword().equals(password)).findFirst();
    }

    // Save or update bidejki ke bide idempotenten metod
    // prvo ke go brisheme korisnikot od bazata, a posle go dodavame
    // noviot ILI promenetiot, nas ne ni pravi razlika kakov e
    public User saveOrUpdate (User user) {
        DataHolder.userList.removeIf(i -> i.getUsername().equals(user.getUsername()));
        DataHolder.userList.add(user);
        return user;
    }

    public void delete(String username) {
        DataHolder.userList.removeIf(i -> i.getUsername().equals(username));
    }

}
