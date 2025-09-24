package dev.flima.portfolio.modules.contact.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }

    public ContactNotFoundException() {
        super("Contact not found.");
    }
}
