package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,creator,tag,gmt_Create,gmt_modified) values(#{title},#{description},#{creator},#{tag},#{gmtCreate},#{gmtModified})")
    void save(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(*) from question")
    Integer count();

    @Select("select * from question where creator = #{userId} limit #{offset}, #{size}")
    List<Question>
    listByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(*) from question where creator = #{userId} ")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);
}
