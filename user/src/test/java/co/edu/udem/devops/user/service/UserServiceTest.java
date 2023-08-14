package co.edu.udem.devops.user.service;

import co.edu.udem.devops.user.model.User;
import co.edu.udem.devops.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void UserService_PostUser_ReturnsSavedUser() {
        User user = User.builder()
                .userId(1L)
                .email("ctabaresgonzalez@gmail.com")
                .password("123")
                .name("Camilo")
                .birthdate(Date.valueOf("1998-10-29"))
                .walletAddress("1Lbcfr7sAHTD9CgdQo3HTMTkV8LK4ZnX71")
                .about("Hi! I'm Camilo")
                .build();

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getUserId() > 0);
    }

    @Test
    public void UserService_GetUser_ReturnsUserByID() {
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

        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        Optional<User> foundUser = userService.findUserById(userId);

        Assertions.assertTrue(foundUser.isPresent());
        assert user != null;
        Assertions.assertSame(user.getUserId(), foundUser.get().getUserId());
    }
}
