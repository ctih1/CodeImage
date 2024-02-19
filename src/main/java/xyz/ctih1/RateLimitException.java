package xyz.ctih1;

public class RateLimitException extends Exception{
    public RateLimitException(String message) {
        super(message);
    }

}
