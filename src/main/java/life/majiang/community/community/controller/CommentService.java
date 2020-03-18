package life.majiang.community.community.controller;

import life.majiang.community.community.enums.CommentTypeEnum;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.CommentMapper;
import life.majiang.community.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author iorlLi
 * @version 1.0
 * @date 2020/3/9 22:55
 */
@Service
public class CommentService {
@Autowired
    private CommentMapper commentMapper;
    public  void insert(Comment comment) {
        if(comment.getId()== null || comment.getId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }
        if(comment.getType() == null || CommentTypeEnum.isNotExit(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TARGET_NOT_FOUND);
        }


        commentMapper.insert(comment);
    }
}
