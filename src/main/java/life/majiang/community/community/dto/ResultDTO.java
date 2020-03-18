package life.majiang.community.community.dto;

import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.exception.ICustomizeErrorCode;
import lombok.Data;

/**
 * @author iorlLi
 * @version 1.0
 * @date 2020/3/9 22:01
 */
@Data
public class ResultDTO {
    private String message;
    private Integer code;

    public static ResultDTO errof(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errof(ICustomizeErrorCode iCustomizeErrorCode){
        return errof(iCustomizeErrorCode.getCode(), iCustomizeErrorCode.getMessage());
    }

    public static ResultDTO errof(CustomizeException e){
        return errof(e.getCode(), e.getMessage());
    }

    public static ResultDTO eokof(ICustomizeErrorCode iCustomizeErrorCode) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("sucess");
        return resultDTO;
    }
}
