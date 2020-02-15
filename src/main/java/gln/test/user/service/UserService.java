package gln.test.user.service;

import gln.test.user.model.UserModel;

import java.util.Map;

public interface UserService {
    UserModel createUser(UserModel user);
    UserModel updateUser(Integer id, Map<Object, Object> fields);
    void deleteUser(Integer id);
    UserModel getUser(Integer id);
    Map<String,Object> getUserList(Integer page);
}
