package com.example.tpspring;

import jakarta.persistence.*;

@Entity
public class LikeDislike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Blog article;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean liked; // true pour like, false pour dislike

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Blog getArticle() {
        return article;
    }

    public void setArticle(Blog article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
