package dev.flima.portfolio.modules.faq.exception;

public class FaqException extends RuntimeException {
    public FaqException(String message) {
        super(message);
    }

    public FaqException() {
        super("FAQ not found.");
    }
}
