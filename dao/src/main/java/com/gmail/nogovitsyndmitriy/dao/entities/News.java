package com.gmail.nogovitsyndmitriy.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_NEWS")
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", nullable = false, updatable = false)
    private long id;
    @Column(name = "F_TITLE")
    private String title;
    @Column(name = "F_CONTENT")
    private String content;
    @Column(name = "F_CREATED")
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "F_USER_ID")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(title, news.title) &&
                Objects.equals(content, news.content) &&
                Objects.equals(created, news.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, content, created);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                '}';
    }
}
