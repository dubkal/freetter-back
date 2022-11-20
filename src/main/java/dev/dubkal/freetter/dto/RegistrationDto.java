package dev.dubkal.freetter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    public String toString() {
        return String.format(
                "User:[username='%s', firstName='%s', lastName='%s', password='%s']",
                username, firstName, lastName, password);
    }
}
