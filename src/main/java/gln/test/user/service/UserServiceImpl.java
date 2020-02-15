package gln.test.user.service;

import gln.test.common.CommonHelper;
import gln.test.user.model.UserModel;
import gln.test.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private CommonHelper ch = new CommonHelper();

    UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserModel createUser(UserModel user) {
        user.setDtCreate(Calendar.getInstance().getTime());
        userDao.insertUser(user);
        return user;
    }

    @Override
    public UserModel updateUser(Integer id,Map<Object, Object> fields) {
        UserModel uModel = userDao.selectUser(id);
        uModel = (UserModel) ch.objectMapper(uModel,fields);
        uModel.setDtUpdate(Calendar.getInstance().getTime());
        userDao.updateUser(uModel);
        return uModel;
    }

    @Override
    public void deleteUser(Integer id) {
        UserModel user = new UserModel();
        user.setId(id);
        userDao.deleteUser(user);
    }

    @Override
    public UserModel getUser(Integer id) {
        return userDao.selectUser(id);
    }

    @Override
    public Map<String,Object> getUserList(Integer page) {
        Map<String,Object> res = userDao.selectUserList(page);
        return res;
    }
}
