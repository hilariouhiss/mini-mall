package me.lhy.commons.exception;

public class UserMarkedAsDeletedException extends RuntimeException{

    public UserMarkedAsDeletedException() {
        super();
    }

    public UserMarkedAsDeletedException(String message) {
        super(message);
    }
}
