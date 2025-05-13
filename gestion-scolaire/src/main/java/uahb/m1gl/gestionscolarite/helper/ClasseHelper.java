// Déclaration du package contenant la classe helper
package uahb.m1gl.gestionscolarite.helper;

// Importation des classes nécessaires
import org.springframework.stereotype.Component;
import uahb.m1gl.gestionscolarite.dto.ClasseRequest;
import uahb.m1gl.gestionscolarite.dto.ClasseResponse;
import uahb.m1gl.gestionscolarite.dto.ClasseUpdateRequest;
import uahb.m1gl.gestionscolarite.exception.ScolariteException;
import uahb.m1gl.gestionscolarite.exception.ScolariteNotFoundException;
import uahb.m1gl.gestionscolarite.mapper.ClassMapper;
import uahb.m1gl.gestionscolarite.model.Classe;
import uahb.m1gl.gestionscolarite.repository.ClasseRepository;
import uahb.m1gl.gestionscolarite.service.ClasseService;
import uahb.m1gl.gestionscolarite.service.FiliereService;

import java.util.List;
import java.util.Optional;

// Annotation Spring indiquant que cette classe est un composant pouvant être injecté dans d'autres classes
@Component
public class ClasseHelper {

    // Dépendances injectées
    private final ClasseService classeService;
    private final FiliereService filiereService;
    private final ClassMapper classMapper;

    // Constructeur de ClasseHelper qui initialise les services nécessaires
    public ClasseHelper(ClasseService classeService, FiliereService filiereService, ClassMapper classMapper, ClasseRepository classeRepository) {
        this.classeService = classeService;
        this.filiereService = filiereService;
        this.classMapper = classMapper;
    }

    // Méthode pour récupérer toutes les classes sous forme de DTOs
    public List<ClasseResponse> findAllClasses() {
        return classeService.findAll().stream()
                .map(classMapper::ClasseEntityToClasseResponse)  // Transformation des entités Classe en ClasseResponse
                .toList();  // Retourne la liste des réponses
    }

    // Méthode pour sauvegarder une classe
    public ClasseResponse saveClasse(ClasseRequest classeRequest) {
        // Vérification de l'existence de la filière via le service FiliereService
        if (filiereService.findById(classeRequest.getFiliereId()) == null) {
            throw new ScolariteNotFoundException("Aucune filière avec l'id " + classeRequest.getFiliereId() + " n'est trouvée ");
        }

        // Vérification de l'existence d'une classe avec le même code dans la base de données
        if (classeService.findByCode(classeRequest.getCode()) != null) {
            throw new ScolariteNotFoundException("Une classe avec le code " + classeRequest.getCode() + " existe déjà");
        }

        // Vérification de l'existence d'une classe avec le même nom dans la base de données
        if (classeService.findByNom(classeRequest.getNom()) != null) {
            throw new ScolariteNotFoundException("Une classe avec le nom " + classeRequest.getNom() + " existe déjà");
        }

        // Validation des montants (inscription, mensualité, autre frais)
        valideMontant(classeRequest.getMontantInscription(), 30000);  // Minimum 30 000 pour l'inscription
        valideMontant(classeRequest.getMensualite(), 30000);  // Minimum 30 000 pour la mensualité
        valideMontant(classeRequest.getAutreFrais(), 10000);  // Minimum 10 000 pour autres frais

        // Sauvegarde de la classe dans la base de données et retour de la classe sauvegardée sous forme de DTO
        var savedClasse = classeService.save(classMapper.classeRequestToClasseEntity(classeRequest));
        return classMapper.ClasseEntityToClasseResponse(savedClasse);
    }

    // Méthode pour valider un montant (le montant doit être supérieur ou égal à un seuil donné)
    private void valideMontant(int montant, int seuil) {
        if (montant < seuil) {
            throw new ScolariteNotFoundException("Le montant doit être d'au moins " + seuil);
        }
    }

    // Méthode pour rechercher une classe par son ID
    public ClasseResponse findClasseById(long id) {
        Classe classe = classeService.findById(id);
        if (classe == null) {
            throw new ScolariteNotFoundException("Aucune classe avec l'id " + id + " n'est trouvée ");
        }    // Méthode pour mettre à jour une classe

        return classMapper.ClasseEntityToClasseResponse(classe);
    }

    public ClasseResponse updateClasse(ClasseUpdateRequest classeUpdateRequest) {
        // Vérification de l'existence de la classe à mettre à jour
        Classe classe = classeService.findById(classeUpdateRequest.getId());
        if (classe == null) {
            throw new ScolariteNotFoundException("Aucune classe avec l'id " + classeUpdateRequest.getId() + " n'est trouvée ");
        }

        // Vérification des doublons de code et de nom parmi les autres classes
        List<Classe> otherClasses = classeService.SelectWithoutId(classe.getId());
        Optional<Classe> doublonCode = otherClasses.stream()
                .filter(c -> c.getCode().equals(classe.getCode())).findAny();
        if (doublonCode.isPresent()) {
            throw new ScolariteException("Une classe avec le code " + doublonCode.get().getCode() + " existe déjà");
        }

        Optional<Classe> doublonNom = otherClasses.stream()
                .filter(c -> c.getNom().equals(classe.getNom())).findAny();
        if (doublonNom.isPresent()) {
            throw new ScolariteException("Une classe avec le nom " + doublonNom.get().getNom() + " existe déjà");
        }

        // Validation des montants pour la mise à jour (inscription, mensualité, autre frais)
        valideMontant(classeUpdateRequest.getClasseRequest().getMontantInscription(), 30000);  // Minimum 30 000
        valideMontant(classeUpdateRequest.getClasseRequest().getMensualite(), 30000);  // Minimum 30 000
        valideMontant(classeUpdateRequest.getClasseRequest().getAutreFrais(), 10000);  // Minimum 10 000

        // Sauvegarde de la classe mise à jour et retour du résultat sous forme de DTO
        return classMapper.ClasseEntityToClasseResponse(classeService.save(classMapper.classeUpdateRequestToClasseEntity(classeUpdateRequest)));
    }
    // Méthode pour la suppression une classe
    public ClasseResponse deleteClasse(long id) {
        Classe classe = classeService.findById(id);
        if (classe == null) {
            throw new ScolariteNotFoundException("Aucune classe avec l'id " + id + " n'est trouvée.");
        }

        Classe deleted = classeService.delete(id);
        return classMapper.ClasseEntityToClasseResponse(deleted);
    }


}
