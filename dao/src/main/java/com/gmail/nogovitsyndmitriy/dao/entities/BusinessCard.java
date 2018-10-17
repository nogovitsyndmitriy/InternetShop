package com.gmail.nogovitsyndmitriy.dao.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Setter
@Getter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_BUSINESS_CARD")
public class BusinessCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", nullable = false, updatable = false)
    private Long id;
    @Column(name = "F_TITLE")
    private String title;
    @Column(name = "F_FULL_NAME")
    private String fullName;
    @Column(name = "F_PHONE")
    private String workingTelephone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "F_USER_ID")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessCard)) return false;
        BusinessCard that = (BusinessCard) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(workingTelephone, that.workingTelephone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, fullName, workingTelephone);
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workingTelephone='" + workingTelephone + '\'' +
                '}';
    }
}
