package gln.test.user.dao;

import gln.test.user.model.UserModel;

import java.util.List;
import java.util.Map;

public interface UserDao {
    void insertUser(UserModel user);
    void deleteUser(UserModel user);
    void updateUser(UserModel user);
    UserModel selectUser(Integer id);
    Map<String,Object> selectUserList(Integer page);
}
