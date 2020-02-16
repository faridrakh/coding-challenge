import gln.test.config.AppConfig;
import gln.test.config.JdbcConfig;
import gln.test.config.WebConfig;
import gln.test.user.model.UserModel;
import gln.test.user.service.UserService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, JdbcConfig.class, AppConfig.class})
@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void addUser_OkStatus() throws Exception {
        String postBody = "{" +
                "\"email\":\"test@test.com\"," +
                "\"firstName\":\"Test2\"," +
                "\"lastName\":\"Test2\"," +
                "\"avatar\":\"test2\"," +
                "\"job\":\"tester2\"" +
                "}";
                mockMvc.perform(post("/api/users/").contentType(MediaType.APPLICATION_JSON_UTF8).content(postBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.firstName").isNotEmpty())
                .andExpect(jsonPath("$.lastName").isNotEmpty())
                .andExpect(jsonPath("$.email").isNotEmpty())
                .andExpect(jsonPath("$.avatar").isNotEmpty())
                .andExpect(jsonPath("$.job").isNotEmpty())
                .andExpect(jsonPath("$.createAt").isNotEmpty())
        ;
    }

    @Test
    public void updateUser_OkStatus() throws Exception {
        String postBody = "{" +
                "\"email\":\"test@test.com\"," +
                "\"firstName\":\"Test2\"," +
                "\"lastName\":\"Test2\"," +
                "\"avatar\":\"test2\"," +
                "\"job\":\"tester2\"" +
                "}";
        mockMvc.perform(patch("/api/users/2").contentType(MediaType.APPLICATION_JSON_UTF8).content(postBody))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.firstName").isNotEmpty())
                .andExpect(jsonPath("$.lastName").isNotEmpty())
                .andExpect(jsonPath("$.email").isNotEmpty())
                .andExpect(jsonPath("$.avatar").isNotEmpty())
                .andExpect(jsonPath("$.job").isNotEmpty())
                .andExpect(jsonPath("$.updateAt").isNotEmpty())

        ;
    }

    @Test
    public void getUser_OkStatus() throws Exception {
        UserModel user = new UserModel();
        user.setId(1);
        user.setFirstName("Test2");
        user.setLastName("Test2");
        user.setEmail("test@test.com");
        user.setAvatar("test2");
        user.setJob("tester2");
        user.setCreateAt("2020-02-15T10:22:41.206+0700");

        mockMvc.perform(get("/api/users/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(user.getId())))
                .andExpect(jsonPath("$.data.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.data.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.data.email", is(user.getEmail())))
                .andExpect(jsonPath("$.data.avatar", is(user.getAvatar())))
                .andExpect(jsonPath("$.data.job", is(user.getJob())))
                .andExpect(jsonPath("$.data.createAt", is(user.getCreateAt())))
        ;
    }

    @Test
    public void getUser_NotFoundStatus() throws Exception {
        JSONObject jsonObject = new JSONObject("{}");
        mockMvc.perform(get("/api/users/{id}", 0))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").isEmpty())
        ;
    }

}

