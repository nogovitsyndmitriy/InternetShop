package com.gmail.nogovitsyndmitriy.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "T_COMMENT")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private long id;
    @Column(name = "F_CONTENT")
    private String content;
    @Column(name = "F_CREATED")
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "F_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "F_NEWS_ID")
    private News news;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Objects.equals(content, comment.content) &&
                Objects.equals(created, comment.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, content, created);
    }
}
