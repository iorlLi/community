package life.majiang.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "问题找不到了,换一个试试吧!"),
    TARGET_NOT_FOUND(2002, "目标问题或评论不存在了"),
    TYPE_NOT_FOUND(2002, "问题类型不存在"),
    NO_LOGIN(2003, "未登录"),
    SYSTEM_ERROR(2004, "系统异常"),
    TYPE_WRONG(2005, "回复类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "评论已删除")
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
    }
}
