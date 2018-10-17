package com.gmail.nogovitsyndmitriy.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_PROFILE")
public class Profile implements Serializable {
    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))

    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "F_USER_ID", unique = true, nullable = false)
    private Long userId;
    @Column(name = "F_ADDRESS")
    private String address;
    @Column(name = "F_TELEPHONE")
    private String telephone;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return Objects.equals(userId, profile.userId) &&
                Objects.equals(address, profile.address) &&
                Objects.equals(telephone, profile.telephone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, address, telephone);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", user=" + user +
                '}';
    }
}
