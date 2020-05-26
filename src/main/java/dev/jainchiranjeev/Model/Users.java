package dev.jainchiranjeev.Model;

import javax.persistence.*;

@Entity
@Table(name = "online_notebook_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "on_id")
    private int id;

    @Column(name = "on_username")
    private String username;

    @Column(name = "on_user_password")
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    public int getId() {
        return id;
    }

}