package life.majiang.community.community.mapper;

import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified,avatar_url,bio) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl},#{bio})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User selectByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update USER set GMT_MODIFIED = #{gmtModified}, AVATAR_URL = #{avatarUrl}, " +
            " NAME = #{name}, TOKEN = #{token} where id = #{id}")
    void update(User dbUser);
}
