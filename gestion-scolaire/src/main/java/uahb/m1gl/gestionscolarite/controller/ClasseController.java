// Déclaration du package du contrôleur
package uahb.m1gl.gestionscolarite.controller;

// Importation des classes nécessaires pour la validation, annotations REST, etc.
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository; // Cette importation semble inutile, elle peut être supprimée
import org.springframework.web.bind.annotation.*;

// Importation des DTOs et autres composants nécessaires pour le controller
import uahb.m1gl.gestionscolarite.dto.ClasseRequest;
import uahb.m1gl.gestionscolarite.dto.ClasseResponse;
import uahb.m1gl.gestionscolarite.dto.ClasseUpdateRequest;
import uahb.m1gl.gestionscolarite.helper.ClasseHelper;
import uahb.m1gl.gestionscolarite.model.Classe;
import uahb.m1gl.gestionscolarite.service.ClasseService;

import java.util.List;

// Annotation indiquant que cette classe est un contrôleur REST Spring
@RestController

// Définition du chemin de base pour les URL des requêtes : /class
public class ClasseController {

    // Déclaration de la dépendance au helper pour la logique métier
    private final ClasseHelper classeHelper;

    // Injection de la dépendance ClasseHelper via le constructeur
    public ClasseController(ClasseHelper classeHelper) {
        this.classeHelper = classeHelper;
    }

    // ------------------------------
    // Méthodes CRUD pour les classes
    // ------------------------------

    // Récupère toutes les classes
    // @GetMapping : correspond à la méthode HTTP GET
    @GetMapping("/anonym/class/all")
    public @ResponseBody List<ClasseResponse> allClasses() {
        // Appel au helper pour récupérer toutes les classes et les retourner sous forme de DTO
        return classeHelper.findAllClasses();
    }

    // Crée une nouvelle classe
    // @PostMapping : correspond à la méthode HTTP POST pour la création de données
    @PostMapping("/admin/class/add")
    public @ResponseBody ClasseResponse saveClasse(@RequestBody ClasseRequest classeRequest) {
        // Appel au helper pour créer la classe à partir de la demande et retourner un DTO en réponse
        return classeHelper.saveClasse(classeRequest);
    }

    // Récupère une classe spécifique par son ID
    // @GetMapping("/{id}") : correspond à un GET avec un paramètre ID dans l'URL
    @GetMapping("/user/class/{id}")
    public @ResponseBody ClasseResponse FindClasse(@PathVariable long id) {
        // Appel au helper pour récupérer la classe via son ID et retourner le DTO correspondant
        return classeHelper.findClasseById(id);
    }

    // Met à jour une classe existante
    // @PutMapping : correspond à la méthode HTTP PUT pour la mise à jour des données
    @PutMapping("/admin/class/update")
    public @ResponseBody ClasseResponse updateClasse(@RequestBody ClasseUpdateRequest classeUpdateRequest) {
        // Appel au helper pour mettre à jour la classe et retourner un DTO en réponse
        return classeHelper.updateClasse(classeUpdateRequest);
    }

    // Suppression une classe existante
    // @DeleteMapping : correspond à la méthode HTTP Delete pour suppression des données
    @DeleteMapping("/admin/class/{id}")
    public ResponseEntity<ClasseResponse> deleteClasse(@PathVariable long id) {
        ClasseResponse deleted = classeHelper.deleteClasse(id);
        return ResponseEntity.ok(deleted); // On retourne la classe supprimée
    }


}
