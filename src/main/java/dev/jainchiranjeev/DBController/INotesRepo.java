package dev.jainchiranjeev.DBController;

import dev.jainchiranjeev.Model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotesRepo extends JpaRepository<Notes, Integer> {
    public List<Notes> findByUsernameEquals(String username);
}