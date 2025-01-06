package com.example.tpspring;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface LikeDislikeRepository extends CrudRepository<LikeDislike, Integer> {

    // Trouver si un utilisateur a déjà liké ou disliké un article donné
    Optional<LikeDislike> findByArticleAndUser(Blog article, User user);

    // Trouver tous les likes pour un article spécifique
    Iterable<LikeDislike> findByArticle(Blog article);

    // Trouver tous les utilisateurs ayant liké un article
    Iterable<LikeDislike> findByArticleAndLikedTrue(Blog article);

    // Trouver tous les utilisateurs ayant disliké un article
    Iterable<LikeDislike> findByArticleAndLikedFalse(Blog article);
}
