package life.majiang.community.community.enums;

/**
 * @author iorlLi
 * @version 1.0
 * @date 2020/3/9 22:01
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer code;

    public static boolean isNotExit(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getCode() == type) {
                return false;
            }
        }
        return true;
    }

    public static boolean isExit(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getCode() == type) {
                return true;
            }
        }
        return false;
    }

    public Integer getCode() {
        return code;
    }

    CommentTypeEnum(Integer code) {
        this.code = code;
    }
}
