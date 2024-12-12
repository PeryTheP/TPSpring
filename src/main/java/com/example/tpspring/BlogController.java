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

    @PostMapping("")
    public ResponseEntity<String> addNewBlog( @RequestBody CreateBlogRequest request) {
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

    @GetMapping("")
    public ResponseEntity<Iterable<Blog>> getAllBlogs() {
        Iterable<Blog> blogs = blogRepository.findAll();
        return ResponseEntity.ok(blogs);
    }
}
