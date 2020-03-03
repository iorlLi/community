package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.QuestionExample;
import life.majiang.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        Integer totalPage = ((totalCount % size) == 0) ? (totalCount / size) : ((totalCount / size) + 1);
        page = page <= 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;
        paginationDTO.setPagition(totalPage, page);
        Integer offset = page <= 1 ? 0 : size * (page - 1);

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset,size));

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user =  userMapper.selectByPrimaryKey(question.getCreator());
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

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);

        Integer totalCount = (int)questionMapper.countByExample(example);

        Integer totalPage = ((totalCount % size) == 0) ? (totalCount / size) : ((totalCount / size) + 1);
        page = page <= 1 ? 1 : page;
        page = page > totalPage ? totalPage : page;
        paginationDTO.setPagition(totalPage, page);
        Integer offset = page <= 1 ? 0 : size * (page - 1);

        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset,size));

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            if (user != null) {
                questionDTO.setUser(user);
            }
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        if (user != null) {
            questionDTO.setUser(user);
        }
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.insert(question);
        }else{
            Question question1 = new Question();
            question1.setGmtModified(System.currentTimeMillis());
            question1.setTag(question.getTag());
            question1.setTitle(question.getTitle());
            question1.setDescription(question.getDescription());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());

            //question1 承载变量，example承载where条件？
            questionMapper.updateByExampleSelective(question1, example);
        }
    }
}
