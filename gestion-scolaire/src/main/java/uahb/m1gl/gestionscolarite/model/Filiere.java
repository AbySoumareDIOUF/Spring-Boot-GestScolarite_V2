// Déclaration du package dans lequel se trouve cette classe
package uahb.m1gl.gestionscolarite.model;

// Importation des annotations JPA pour la persistance et des annotations Lombok pour générer du code automatiquement
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Indique que cette classe est une entité JPA (elle sera mappée à une table de la base de données)
@Entity //(name = "filier_table") // Optionnel : permet de spécifier un nom de table différent
// Génère un constructeur avec tous les arguments (grâce à Lombok)
@AllArgsConstructor
// Génère un constructeur sans argument
@NoArgsConstructor
// Génère automatiquement les getters, setters, toString, equals, hashCode, etc.
@Data
// Permet d’utiliser le pattern Builder pour construire des objets Filiere
@Builder
public class Filiere {

    // Clé primaire de l'entité, avec auto-incrémentation (IDENTITY dépend du SGBD utilisé)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Colonne "nom" avec une taille maximale de 50 caractères
    @Column(length = 50)
    private String nom;

    // Relation One-to-Many avec l'entité Classe (une filière peut avoir plusieurs classes)
    // Le champ "filiere" dans la classe Classe est la clé étrangère qui référence cette relation
    @OneToMany(mappedBy = "filiere")
    private List<Classe> classes;
}
