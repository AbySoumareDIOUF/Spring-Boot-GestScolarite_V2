// Déclaration du package du repository
package uahb.m1gl.gestionscolarite.repository;

// Importation des classes nécessaires de Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uahb.m1gl.gestionscolarite.model.Classe;

import java.util.List;

// Indique que cette interface est un bean Spring de type Repository
@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    // Recherche d'une classe par son code (Spring génère automatiquement la requête)
    Classe findByCode(String code);

    // Recherche d'une classe par son nom
    Classe findByNom(String nom);

    // Requête personnalisée JPQL : sélectionne toutes les classes sauf celle ayant l'id donné
    @Query("SELECT c FROM Classe c WHERE c.id != :id")
    List<Classe> SelectWithoutId(Long id);

    // Requête personnalisée JPQL : sélectionne la classe ayant l'id donné
    @Query("SELECT c.id FROM Classe c WHERE c.id = :id")
    List<Classe> delete(Long id); 
}
