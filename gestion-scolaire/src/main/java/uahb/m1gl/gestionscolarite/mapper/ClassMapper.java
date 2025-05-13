// Déclaration du package contenant le mapper
package uahb.m1gl.gestionscolarite.mapper;

// Importation des classes nécessaires
import org.springframework.stereotype.Component;
import uahb.m1gl.gestionscolarite.dto.ClasseRequest;
import uahb.m1gl.gestionscolarite.dto.ClasseResponse;
import uahb.m1gl.gestionscolarite.dto.ClasseUpdateRequest;
import uahb.m1gl.gestionscolarite.dto.FiliereDTO;
import uahb.m1gl.gestionscolarite.model.Classe;
import uahb.m1gl.gestionscolarite.model.Filiere;

// Annotation indiquant que cette classe est un composant Spring et peut être injectée dans d'autres classes
@Component
public class ClassMapper {

    // Méthode pour convertir un objet ClasseRequest en une entité Classe
    public Classe classeRequestToClasseEntity(ClasseRequest classeRequest){
        // On utilise le pattern Builder pour créer une instance de Classe avec les valeurs de classeRequest
        return Classe.builder()
                .nom(classeRequest.getNom())  // Nom de la classe
                .code(classeRequest.getCode())  // Code de la classe
                .mensualite(classeRequest.getMensualite())  // Mensualité de la classe
                .montantInscription(classeRequest.getMontantInscription())  // Montant d'inscription
                .autreFrais(classeRequest.getAutreFrais())  // Autres frais
                // On crée une instance de Filiere en utilisant l'ID de la filière de classeRequest
                .filiere(Filiere.builder().id(classeRequest.getFiliereId()).build())
                .build();  // Retourne une instance de Classe
    }

    // Méthode pour convertir une entité Classe en un objet ClasseResponse
    public ClasseResponse ClasseEntityToClasseResponse(Classe classe){
        // On utilise le pattern Builder pour construire la réponse en fonction de l'entité classe
        return ClasseResponse.builder()
                .id(classe.getId())  // ID de la classe
                .nom(classe.getNom())  // Nom de la classe
                .code(classe.getCode())  // Code de la classe
                .mensualite(classe.getMensualite())  // Mensualité
                .montantInscription(classe.getMontantInscription())  // Montant d'inscription
                .autreFrais(classe.getAutreFrais())  // Autres frais
                // Conversion de l'entité Filiere en DTO FiliereDTO
                .filiere(filiereEntityToFiliereDto(classe.getFiliere()))
                .build();  // Retourne un objet ClasseResponse
    }

    // Méthode pour convertir une entité Filiere en un DTO FiliereDTO
    public FiliereDTO filiereEntityToFiliereDto(Filiere filiere){
        // Utilisation du Builder pour créer le DTO FiliereDTO à partir de l'entité Filiere
        return FiliereDTO.builder()
                .id(filiere.getId())  // ID de la filière
                .nom(filiere.getNom())  // Nom de la filière
                .build();  // Retourne le DTO FiliereDTO
    }

    // Méthode pour convertir une classeUpdateRequest en une entité Classe
    public Classe classeUpdateRequestToClasseEntity(ClasseUpdateRequest classeUpdateRequest){
        // On réutilise la méthode classeRequestToClasseEntity pour obtenir une classe de base à partir de classeRequest
        Classe classe = classeRequestToClasseEntity(classeUpdateRequest.getClasseRequest());
        // On définit l'ID de la classe qui est passé dans la requête de mise à jour
        classe.setId(classeUpdateRequest.getId());
        return classe;  // Retourne l'entité Classe mise à jour avec l'ID
    }
}
