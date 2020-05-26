package dev.jainchiranjeev.DBController;

import dev.jainchiranjeev.Model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepo extends JpaRepository<Users, Integer> {
    public List<Users> findByUsernameEqualsAndPasswordEquals(String username, String password);

    public List<Users> findByUsernameEquals(String username);
}