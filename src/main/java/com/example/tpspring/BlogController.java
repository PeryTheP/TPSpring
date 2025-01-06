package com.example.tpspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // Utilisation de RestController pour simplifier les réponses JSON
@RequestMapping(path = "/blog") // Toutes les URL commencent par /blog
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private jwtService jwtService; // Injection du service JWT

    @Autowired
    private LikeDislikeRepository likeDislikeRepository; // Injection du repository LikeDislike

    // Création d'un nouvel article
    @PostMapping("")
    public ResponseEntity<String> addNewBlog(@RequestBody CreateBlogRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().body("Auteur introuvable pour l'ID : " + request.getUserId());
        }

        User user = optionalUser.get();

        Blog blog = new Blog();
        blog.setAuteur(user);
        blog.setContenu(request.getContenu());
        blog.setDatePubli(request.getDatePubli());

        blogRepository.save(blog);

        return ResponseEntity.ok("Blog créé avec succès !");
    }

    // Récupération de tous les articles
    @GetMapping("")
    public ResponseEntity<Iterable<Blog>> getAllBlogs() {
        Iterable<Blog> blogs = blogRepository.findAll();
        return ResponseEntity.ok(blogs);
    }

    // Liker un article
    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeArticle(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        // Extraction de l'utilisateur à partir du token JWT
        User user = jwtService.extractUserFromToken(token);

        Optional<Blog> blogOpt = blogRepository.findById(id);
        if (blogOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Article introuvable");
        }

        Blog blog = blogOpt.get();

        // Vérifier si l'utilisateur a déjà liké ou disliké cet article
        Optional<LikeDislike> existingLike = likeDislikeRepository.findByArticleAndUser(blog, user);
        if (existingLike.isPresent()) {
            return ResponseEntity.badRequest().body("Vous avez déjà interagi avec cet article");
        }

        // Création de l'objet LikeDislike pour enregistrer l'interaction
        LikeDislike like = new LikeDislike();
        like.setArticle(blog);
        like.setUser(user);
        like.setLiked(true); // Si l'utilisateur aime l'article

        likeDislikeRepository.save(like);

        return ResponseEntity.ok("Article liké avec succès");
    }

    // Définir une méthode pour "disliker" si nécessaire
    @PostMapping("/{id}/dislike")
    public ResponseEntity<String> dislikeArticle(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        // Extraction de l'utilisateur à partir du token JWT
        User user = jwtService.extractUserFromToken(token);

        Optional<Blog> blogOpt = blogRepository.findById(id);
        if (blogOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Article introuvable");
        }

        Blog blog = blogOpt.get();

        // Vérifier si l'utilisateur a déjà liké ou disliké cet article
        Optional<LikeDislike> existingLike = likeDislikeRepository.findByArticleAndUser(blog, user);
        if (existingLike.isPresent()) {
            return ResponseEntity.badRequest().body("Vous avez déjà interagi avec cet article");
        }

        // Création de l'objet LikeDislike pour enregistrer l'interaction
        LikeDislike dislike = new LikeDislike();
        dislike.setArticle(blog);
        dislike.setUser(user);
        dislike.setLiked(false); // Si l'utilisateur n'aime pas l'article

        likeDislikeRepository.save(dislike);

        return ResponseEntity.ok("Article disliké avec succès");
    }
}
