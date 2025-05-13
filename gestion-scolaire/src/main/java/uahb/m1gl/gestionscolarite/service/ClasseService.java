// Déclaration du package de la couche service
package uahb.m1gl.gestionscolarite.service;

// Annotation Spring pour désigner cette classe comme un service métier
import org.springframework.stereotype.Service;

// Importation du modèle Classe et du repository associé
import uahb.m1gl.gestionscolarite.model.Classe;
import uahb.m1gl.gestionscolarite.repository.ClasseRepository;

import java.util.List;
import java.util.Optional;

// Annotation qui indique que cette classe est un service géré par Spring (injection possible dans les controllers)
@Service
public class ClasseService {

    // --- OPTION : annotation pour injection automatique (commentée ici car constructeur utilisé)
    // @Autowired
    // private ClasseRepository classeRepository;

    // Déclaration du repository en tant qu'attribut final pour l’injection via constructeur
    private final ClasseRepository classeRepository;

    // Constructeur pour injecter le repository (meilleure pratique que @Autowired)
    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    // -------------------------
    // Méthodes du service CRUD
    // -------------------------

    public Classe save(Classe classe){
        return classeRepository.save(classe);
    }

    // Retourne toutes les classes de la base
    public List<Classe> findAll(){    // Crée ou met à jour une entité Classe

        return classeRepository.findAll();
    }

    // Recherche une classe par son code
    public Classe findByCode(String code){
        return classeRepository.findByCode(code);
    }

    // Recherche une classe par son nom
    public Classe findByNom(String nom){
        return classeRepository.findByNom(nom);
    }

    // Recherche une classe par ID (renvoie null si non trouvée)
    public Classe findById(long id){
        return classeRepository.findById(id).orElse(null); // ⚠️ Risque de null, à sécuriser
    }

    // Retourne toutes les classes sauf celle ayant l’ID spécifié
    public List<Classe> SelectWithoutId(Long id){
        return classeRepository.SelectWithoutId(id);
    }
    // Supprimer une entité Classe

    public Classe delete(long id) {
        Classe classe = classeRepository.findById(id).orElse(null);
        if (classe != null) {
            classeRepository.deleteById(id);
        }
        return classe;
    }


}

