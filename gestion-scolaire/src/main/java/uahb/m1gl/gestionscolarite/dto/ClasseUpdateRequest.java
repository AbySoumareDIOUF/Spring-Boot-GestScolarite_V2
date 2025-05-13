// Déclaration du package contenant le DTO pour la mise à jour d'une classe
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
public class ClasseUpdateRequest {

    // id : Identifiant de la classe à mettre à jour
    private long id;

    // classeRequest : Les nouvelles données de la classe à mettre à jour, encapsulées dans un DTO ClasseRequest
    private ClasseRequest classeRequest;
}
