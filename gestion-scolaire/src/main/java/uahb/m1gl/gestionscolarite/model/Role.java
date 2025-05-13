package uahb.m1gl.gestionscolarite.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30)
    private String nom;

    //Jointure de la table role et utilisateur avec une table secondaire combinant les deux
    @ManyToMany(mappedBy = "roles")
    private Set<Utilisateur> Utilisateurs = new HashSet<>();

    public Role(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
