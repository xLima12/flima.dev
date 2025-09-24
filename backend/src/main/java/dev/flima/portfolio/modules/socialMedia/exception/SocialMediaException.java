package dev.flima.portfolio.modules.socialMedia.exception;

public class SocialMediaException extends RuntimeException {
  public SocialMediaException(String message) {
    super(message);
  }

  public SocialMediaException() {
    super("Social media not found.");
  }
}
