package dev.wayne.contacts.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ApiException extends RuntimeException {

    private int statusCode;

    public ApiException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApiException(int statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"statusCode\": " + statusCode + ",\n" +
                "  \"message\": \"" + getMessage() + "\"\n" +
                "}";
    }
}
