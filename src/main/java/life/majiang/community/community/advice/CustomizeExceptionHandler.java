package life.majiang.community.community.advice;

import com.alibaba.fastjson.JSON;
import life.majiang.community.community.dto.ResultDTO;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {
        //希望ajax请求，后台不刷新页面。
        if ("application/json".equals(request.getContentType())) {
            ResultDTO resultDTO = null;
            if (ex instanceof CustomizeException) {
                resultDTO = ResultDTO.errof((CustomizeException) ex);
            } else {
                resultDTO = ResultDTO.errof(CustomizeErrorCode.SYSTEM_ERROR);
            }
            response.setCharacterEncoding("utf-8");
            response.setStatus(200);
            response.setContentType("application/json");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }


}
