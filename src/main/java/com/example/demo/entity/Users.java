package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String cin;
    private String phoneNumber;
    private Boolean emailConfirmed;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp dateNaissance;
    private String adresse;
    private String gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp dateEmploi;
    private String matricule;
    private Boolean etatCompte;
    private Boolean acceptPromotion;


}
