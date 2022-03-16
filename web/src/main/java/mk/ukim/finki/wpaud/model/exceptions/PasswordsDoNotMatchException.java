package mk.ukim.finki.wpaud.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Passwords don't match!");
    }
}
