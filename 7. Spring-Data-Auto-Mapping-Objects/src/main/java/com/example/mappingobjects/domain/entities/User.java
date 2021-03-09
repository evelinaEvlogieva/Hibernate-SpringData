package com.example.mappingobjects.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private Set<Game> games;

    public User() {
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-z]{2,4}", message = "Incorrect email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "(?=^.{6,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)[0-9a-zA-Z!@#$%^&*()]*$",
            message = "Incorrect password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(targetEntity = Game.class)
    @JoinTable(
            name = "users_games",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id")
    )
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
