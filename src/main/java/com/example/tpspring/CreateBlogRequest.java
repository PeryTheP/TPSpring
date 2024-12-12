package com.example.tpspring;


import java.time.LocalDate;

public class CreateBlogRequest {


    private LocalDate datePubli;



    private String contenu;


    private Integer userId;

    public CreateBlogRequest(LocalDate datePubli, Integer userId, String contenu) {
        this.datePubli = datePubli;
        this.userId = userId;
        this.contenu = contenu;
    }

    public String getContenu() {
        return contenu;
    }

    public Integer getUserId() {
        return userId;
    }

    public LocalDate getDatePubli() {
        return datePubli;
    }
}
