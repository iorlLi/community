package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.count();
        Integer totalPage = ((totalCount % size) == 0) ? (totalCount / size) : ((totalCount / size) + 1);
        page = page <= 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;
        paginationDTO.setPagition(totalPage, page);
        Integer offset = page <= 1 ? 0 : size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.findById(question.getCreator());
            if (user != null) {
                questionDTO.setUser(user);
            }
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }

    public PaginationDTO getByUserId(Integer id, Integer page, Integer size) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.countByUserId(id);  ;
        Integer totalPage = ((totalCount % size) == 0) ? (totalCount / size) : ((totalCount / size) + 1);
        page = page <= 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;
        paginationDTO.setPagition(totalPage, page);
        Integer offset = page <= 1 ? 0 : size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(id, offset, size);

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.findById(question.getCreator());
            if (user != null) {
                questionDTO.setUser(user);
            }
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        List<Question> questions1 = questionMapper.listByUserId(id, offset, size);
        return paginationDTO;
    }
}
