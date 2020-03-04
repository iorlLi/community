package life.majiang.community.community.exception;

public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(String message) {
        super(message);
        this.message = message;
    }
    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
        this.message = iCustomizeErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
