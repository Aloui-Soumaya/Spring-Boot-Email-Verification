package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto implements Serializable {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String cin;
    private String phoneNumber;
    private Boolean emailConfirmed;
    private Timestamp dateNaissance;
    private String adresse;
    private String gender;
    private Timestamp dateEmploi;
    private String matricule;
    private Boolean etatCompte;
    private Boolean acceptPromotion;
}

