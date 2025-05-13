// Déclaration du package contenant le DTO pour la requête de la classe
package uahb.m1gl.gestionscolarite.dto;

// Importation des annotations Lombok pour générer automatiquement des méthodes (getter, setter, etc.)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Builder : permet d'utiliser le pattern "Builder" pour une création d'objets fluide et lisible
@Builder

// @Data : génère automatiquement les méthodes getter, setter, toString, equals, et hashCode
@Data

// @NoArgsConstructor : génère un constructeur sans argument (utile pour la sérialisation/désérialisation)
@NoArgsConstructor

// @AllArgsConstructor : génère un constructeur avec tous les arguments pour initialiser les champs
@AllArgsConstructor
public class ClasseRequest {

    // code : Code de la classe (ex : "CL123")
    private String code;

    // nom : Nom de la classe (ex : "Informatique")
    private String nom;

    // montantInscription : Montant à payer pour l'inscription à la classe
    private int montantInscription;

    // mensualite : Montant à payer chaque mois pour la classe
    private int mensualite;

    // autreFrais : Autres frais associés à la classe
    private int autreFrais;

    // filiereId : Identifiant de la filière à laquelle cette classe appartient
    private long filiereId;
}
