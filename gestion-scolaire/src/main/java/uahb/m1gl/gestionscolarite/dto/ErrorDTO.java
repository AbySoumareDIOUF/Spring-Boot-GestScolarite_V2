// Déclaration du package contenant le DTO (Data Transfer Object) pour les erreurs
package uahb.m1gl.gestionscolarite.dto;

// Importation des annotations Lombok pour la génération automatique des méthodes (getter, setter, etc.)
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// @Data : génère les méthodes getter, setter, equals, hashCode, toString automatiquement
@Data

// @AllArgsConstructor : génère un constructeur avec tous les arguments pour initialiser les variables d'instance
@AllArgsConstructor

// @Builder : permet de construire des objets de cette classe de manière fluide et lisible
@Builder
public class ErrorDTO {

    // code : code de l'erreur (par exemple "NOT_FOUND", "BAD_REQUEST", etc.)
    private String code;

    // message : message détaillant l'erreur
    private String message;
}
