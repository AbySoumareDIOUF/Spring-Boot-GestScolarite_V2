// Déclaration du package de la couche service
package uahb.m1gl.gestionscolarite.service;

// Annotation qui marque cette classe comme un composant de type Service géré par Spring
import org.springframework.stereotype.Service;

// Importation du modèle Filiere pour le manipuler dans le service
import uahb.m1gl.gestionscolarite.model.Filiere;

// Importation du repository Filiere pour accéder aux données
import uahb.m1gl.gestionscolarite.repository.FiliereRepository;

// Déclare que cette classe est un service métier dans l'application
@Service
public class FiliereService {

    // Déclaration d’un champ final pour injecter le repository Filiere
    private final FiliereRepository filiereRepository;

    // Constructeur utilisé par Spring pour injecter automatiquement le repository via l'injection de dépendances
    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    // Méthode publique pour retrouver une filière à partir de son identifiant
    public Filiere findById(long id){
        // Appel de la méthode personnalisée du repository
        return filiereRepository.findById(id);
    }
}
