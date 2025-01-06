package com.example.tpspring;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate datePubli;
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private User auteur;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<LikeDislike> reactions;

    public Blog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuteur() {
        return auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDatePubli() {
        return datePubli;
    }

    public void setDatePubli(LocalDate datePubli) {
        this.datePubli = datePubli;
    }
}
