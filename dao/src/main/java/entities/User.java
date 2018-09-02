package entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private long id;
    private String login;
    private String password;
    private String role;
    private String name;
    private String lastName;
    private String age;
    private String email;

    public User(String login, String password, String role, String name, String lastName, String age, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public User(String login, String password, String name, String lastName, String age, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }
}
