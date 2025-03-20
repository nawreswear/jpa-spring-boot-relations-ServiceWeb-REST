package iset.master.spring.controller;

import iset.master.spring.model.Categorie;
import iset.master.spring.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    // 1️⃣ Récupérer toutes les catégories
    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    //  Récupérer une catégorie par son ID
   /* @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable Long id) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        return categorie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3️⃣ Ajouter une nouvelle catégorie
    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        if (categorie.getNom() == null || categorie.getNom().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Categorie newCategorie = categorieRepository.save(categorie);
        return ResponseEntity.ok(newCategorie);
    }

    // 4️⃣ Mettre à jour une catégorie
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long id, @RequestBody Categorie updatedCategorie) {
        return categorieRepository.findById(id).map(categorie -> {
            categorie.setNom(updatedCategorie.getNom());
            return ResponseEntity.ok(categorieRepository.save(categorie));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5️⃣ Supprimer une catégorie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        if (categorieRepository.existsById(id)) {
            categorieRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }*/
}
