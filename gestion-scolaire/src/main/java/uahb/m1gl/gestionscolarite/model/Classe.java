// Déclaration du package
package uahb.m1gl.gestionscolarite.model;

// Importation des annotations JPA pour l'entité, les relations, etc., et Lombok pour réduire le code boilerplate
import jakarta.persistence.*;
import lombok.*;

@Builder // Lombok : permet de construire des objets avec le pattern Builder
@Data    // Lombok : génère getters, setters, toString, equals, hashCode, etc.
@NoArgsConstructor // Lombok : génère un constructeur sans arguments
@AllArgsConstructor // Lombok : génère un constructeur avec tous les arguments
@Entity // Indique que cette classe est une entité JPA persistante (table dans la base de données)
public class Classe {

    // Clé primaire auto-générée (auto-incrémentée selon le moteur de base utilisé)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Colonne "code" avec une taille maximale de 8 caractères
    @Column(length = 8)
    private String code;

    // Colonne "nom" avec une taille maximale de 150 caractères
    @Column(length = 150)
    private String nom;

    // Montant des frais d'inscription pour la classe
    private int montantInscription;

    // Montant de la mensualité pour la classe
    private int mensualite;

    // Montant des autres frais éventuels
    private int autreFrais;

    // Relation Many-to-One : plusieurs classes peuvent appartenir à une même filière
    @ManyToOne
    // Spécifie la colonne de jointure dans la table Classe (clé étrangère vers Filiere)
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
}
