import gln.test.user.controller.UserController;
import gln.test.user.dao.UserDao;
import gln.test.user.model.UserModel;
import gln.test.user.service.UserService;
import gln.test.user.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    UserController userController;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserService userServiceMock;

    @Test
    public void testGetUserController(){
        UserModel user = new UserModel();
        user.setId(1);
        user.setFirstName("Test2");
        user.setLastName("Test2");
        user.setEmail("test@test.com");
        user.setAvatar("test2");
        user.setJob("tester2");
        user.setCreateAt("2020-02-15T10:22:41.206+0700");

        when(userServiceMock.getUser(1)).thenReturn(user);
        ResponseEntity<?> responseEntity = userController.doGetUserRsApi(1);
        Map<String, Object> res = (Map<String, Object>) responseEntity.getBody();
        UserModel resUser = (UserModel) res.get("data");
        assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        assertEquals(resUser,user);
    }

    @Test
    public void testGetUserService(){
        UserModel user = new UserModel();
        user.setId(1);
        user.setFirstName("Test2");
        user.setLastName("Test2");
        user.setEmail("test@test.com");
        user.setAvatar("test2");
        user.setJob("tester2");
        user.setDtCreate(Calendar.getInstance().getTime());

        when(userDao.selectUser(1)).thenReturn(user);

        UserModel userRes = userServiceImpl.getUser(1);
        assertEquals(userRes,user);
    }
}
