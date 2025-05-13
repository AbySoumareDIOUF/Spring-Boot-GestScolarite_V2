// Déclaration du package du dépôt (repository)
package uahb.m1gl.gestionscolarite.repository;

// Importation de JpaRepository : interface Spring Data JPA pour gérer les opérations de base (CRUD)
import org.springframework.data.jpa.repository.JpaRepository;

// Annotation Spring pour indiquer que cette interface est un composant de type Repository
import org.springframework.stereotype.Repository;

// Importation du modèle Filiere pour pouvoir l'utiliser comme entité cible du repository
import uahb.m1gl.gestionscolarite.model.Filiere;

// Annotation qui marque cette interface comme un bean Repository géré par Spring
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {

    // Méthode personnalisée pour récupérer une filière par son ID (même si JpaRepository propose déjà findById avec Optional)
    // Ici, tu redéclares une version qui retourne directement un objet Filiere (attention à gérer les cas où il n’existe pas)
    Filiere findById(long id);
}
