// Déclaration du package contenant le DTO pour la "Filière"
package uahb.m1gl.gestionscolarite.dto;

// Importation des annotations Lombok pour générer automatiquement des méthodes (getter, setter, etc.)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data : génère automatiquement les méthodes getter, setter, toString, equals, et hashCode
@Data

// @NoArgsConstructor : génère un constructeur sans argument (utile pour la sérialisation/désérialisation)
@NoArgsConstructor

// @AllArgsConstructor : génère un constructeur avec tous les arguments pour initialiser les champs
@AllArgsConstructor

// @Builder : permet d'utiliser le pattern "Builder" pour une création d'objets fluide et lisible
@Builder
public class FiliereDTO {

    // id : Identifiant unique de la filière
    private long id;

    // nom : Nom de la filière
    private String nom;
}
