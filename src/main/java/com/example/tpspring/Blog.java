package com.example.tpspring;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ajout de la génération automatique de l'ID
    private Integer id;

    private LocalDate datePubli;
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private User auteur;

    // Constructeurs, getters, setters

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
