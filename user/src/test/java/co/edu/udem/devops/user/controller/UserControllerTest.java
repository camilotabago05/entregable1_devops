package co.edu.udem.devops.user.controller;

import co.edu.udem.devops.user.model.User;
import co.edu.udem.devops.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void UserController_PostUser_ReturnsSavedUser() throws Exception {
        given(userService.saveUser(any(User.class))).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        User user = User.builder()
                .userId(1L)
                .email("ctabaresgonzalez@gmail.com")
                .password("123")
                .name("Camilo")
                .birthdate(Date.valueOf("1998-10-29"))
                .walletAddress("1Lbcfr7sAHTD9CgdQo3HTMTkV8LK4ZnX71")
                .about("Hi! I'm Camilo")
                .build();

        this.mockMvc.perform(post("/api/users/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(user.getUserId().intValue())));
    }

    @Test
    public void UserController_GetUser_ReturnsUserById() throws Exception {
        Long userId = 1L;

        User user = User.builder()
                .userId(userId)
                .email("ctabaresgonzalez@gmail.com")
                .password("123")
                .name("Camilo")
                .birthdate(Date.valueOf("1998-10-29"))
                .walletAddress("1Lbcfr7sAHTD9CgdQo3HTMTkV8LK4ZnX71")
                .about("Hi! I'm Camilo")
                .build();

        given(userService.findUserById(userId)).willReturn(Optional.of(user));

        this.mockMvc.perform(get("/api/users/get/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(user.getUserId().intValue())));
    }
}
