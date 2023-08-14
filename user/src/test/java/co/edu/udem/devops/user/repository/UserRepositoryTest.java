package co.edu.udem.devops.user.repository;

import co.edu.udem.devops.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_PostUser_ReturnsSavedUser() {
        User user = User.builder()
                .email("ctabaresgonzalez@gmail.com")
                .password("123")
                .name("Camilo")
                .birthdate(Date.valueOf("1998-10-29"))
                .walletAddress("1Lbcfr7sAHTD9CgdQo3HTMTkV8LK4ZnX71")
                .about("Hi! I'm Camilo")
                .build();

        User savedUser = userRepository.save(user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getUserId() > 0);
    }

    @Test
    public void UserRepository_GetUser_ReturnsUserByID() {
        User user = User.builder()
                .email("ctabaresgonzalez@gmail.com")
                .password("123")
                .name("Camilo")
                .birthdate(Date.valueOf("1998-10-29"))
                .walletAddress("1Lbcfr7sAHTD9CgdQo3HTMTkV8LK4ZnX71")
                .about("Hi! I'm Camilo")
                .build();

        User savedUser = userRepository.save(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getUserId());

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertSame(savedUser.getUserId(), foundUser.get().getUserId());
    }
}
