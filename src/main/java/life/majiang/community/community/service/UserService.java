package life.majiang.community.community.service;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import life.majiang.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
        } else {
            User user1 = users.get(0);
            User user2 = new User();
            user2.setGmtModified(System.currentTimeMillis());
            user2.setToken(user1.getToken());
            //dbUser.setAccountId(user.getAccountId());
            user2.setName(user1.getName());
            user2.setAvatarUrl(user1.getAvatarUrl());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(user1.getId());
            userMapper.updateByExampleSelective(user2, example);
        }
    }
}
