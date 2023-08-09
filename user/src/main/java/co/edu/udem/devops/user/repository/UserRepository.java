package co.edu.udem.devops.user.repository;

import co.edu.udem.devops.user.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :newPassword where u.userId = :userId")
    void updateUserPassword(@Param("newPassword") String newPassword, @Param("userId") Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.walletAddress = :newWalletAddress where u.userId = :userId")
    void updateUserWalletAddress(@Param("newWalletAddress") String newWalletAddress, @Param("userId") Long userId);
}
