package life.majiang.community.community.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(String message) {
        super(message);
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
        this.message = iCustomizeErrorCode.getMessage();
        this.code = iCustomizeErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
