package iset.master.spring.controller;

import iset.master.spring.model.Produit;
import iset.master.spring.repository.ProduitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produits")
@CrossOrigin(origins = "*")
public class ProduitRESTController {

    @Autowired
    private ProduitRepository produitRepos;

    // Message d'accueil
    @GetMapping(value = "/index")
    public String accueil() {
        return "Bienvenue au service Web REST 'produits'...";
    }

    // Afficher la liste des produits
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Produit> getAllProduits() {
        return produitRepos.findAll();
    }

    // Afficher un produit par ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProduitById(@PathVariable Long id) {
        Optional<Produit> produit = produitRepos.findById(id);
        return produit.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit non trouvé"));
    }

/*@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
    Optional<Produit> produit = produitRepos.findById(id);
    if (produit.isPresent()) {
        return ResponseEntity.ok(produit.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}
*/
    // Ajouter un produit
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> saveProduit(@RequestBody Produit p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produitRepos.save(p));
    }

    // Mettre à jour un produit
   /* @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduit(@PathVariable Long id, @RequestBody Produit p) {
        if (p.getId() == null || !p.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("L'ID dans l'URL et l'ID du corps JSON doivent correspondre.");
        }

        Optional<Produit> existingProduit = produitRepos.findById(id);
        if (existingProduit.isPresent()) {
            p.setId(id); // S'assurer que l'ID est bien défini
            return ResponseEntity.ok(produitRepos.save(p));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit non trouvé avec ID : " + id);
        }
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return produitRepos.findById(id)
                .map(existingProduit -> {
                    existingProduit.setDesignation(produit.getDesignation());
                    existingProduit.setPrix(produit.getPrix());
                    existingProduit.setQuantite(produit.getQuantite());
                    existingProduit.setCategorie(produit.getCategorie());
                    produitRepos.save(existingProduit);
                    return ResponseEntity.ok(existingProduit);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    // Supprimer un produit par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable Long id) {
        if (!produitRepos.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit non trouvé avec ID : " + id);
        }
        produitRepos.deleteById(id);
        return ResponseEntity.ok("Produit supprimé avec succès");
    }
}
