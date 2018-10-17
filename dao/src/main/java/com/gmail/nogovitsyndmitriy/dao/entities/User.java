package com.gmail.nogovitsyndmitriy.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", nullable = false, updatable = false)
    private Long id;
    @Column(name = "F_EMAIL", updatable = false, unique = true)
    @Email
    @NotNull
    private String email;
    @Column(name = "F_NAME")
    private String name;
    @Column(name = "F_SURNAME")
    private String surname;
    @Column(name = "F_PASSWORD")
    private String password;
    @Column(name = "F_DISABLED")
    private Boolean disabled;
    //  Profile
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Profile profile;
    //  Audit
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "F_USER_ID")
    private Set<Audit> audits = new HashSet<>();
    //    Roles
    @ManyToOne
    @JoinColumn(name = "F_ROLE_ID")
    private Role role;
    //    Discount
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "F_DISCOUNT_ID", unique = true)
    private Discount discount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(password, user.password) &&
                Objects.equals(disabled, user.disabled);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, name, surname, password, disabled);
    }
}
