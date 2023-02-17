package dev.dubkal.freetter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Timestamp createdDate;

    public User(String username, String email, String firstName, String lastName, String password, Timestamp createdDate) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.createdDate = createdDate;
    }
}
