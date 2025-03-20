package iset.master.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import iset.master.spring.model.Categorie;
import iset.master.spring.model.Produit;
import iset.master.spring.repository.CategorieRepository;
import iset.master.spring.repository.ProduitRepository;

@SpringBootApplication
public class JpaSpringBootApplicationTests {

    static ProduitRepository produitRepos;
    static CategorieRepository categorieRepos;

    // Rendre les dates accessibles globalement
    static java.util.Date date1, date2, date3;

    public static void main(String[] args) {
        ApplicationContext contexte = SpringApplication.run(JpaSpringBootApplication.class, args);

        produitRepos = contexte.getBean(ProduitRepository.class);
        categorieRepos = contexte.getBean(CategorieRepository.class);

        // Créer deux catégories
        Categorie cat1 = new Categorie("AL", "Alimentaire");
        Categorie cat2 = new Categorie("PL", "Plastique");

        // Attacher les deux catégories à la BD (insertion)
        categorieRepos.save(cat1);
        categorieRepos.save(cat2);

        // Initialiser les dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = sdf.parse("2020-04-15");
            date2 = sdf.parse("2020-02-15");
            date3 = sdf.parse("2020-05-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Insérer 3 produits
        Produit p1 = new Produit("Yaourt", 0.400, 20, date1, cat1);
        Produit p2 = new Produit("Chocolat", 2000.0, 5, date2, cat1);
        Produit p3 = new Produit("Panier", 1.200, 30, date3, cat2);
        produitRepos.save(p1);
        produitRepos.save(p2);
        produitRepos.save(p3);

        // Afficher la liste des produits
        afficherTousLesProduits();

        // Afficher la liste des produits d'une catégorie donnée
        afficherTousLesProduitsDeLaCategorie(1L);

        // Modifier la catégorie d'un produit
        p1.setCategorie(cat2);
        produitRepos.saveAndFlush(p1);

        // Afficher la liste des produits d'une catégorie donnée après modification
        afficherTousLesProduitsDeLaCategorie(1L);
    }

    // Afficher tous les produits
    static void afficherTousLesProduits() {
        System.out.println("***************************************");
        List<Produit> lp = produitRepos.findAll();
        System.out.println("Afficher tous les produits...");
        for (Produit p : lp) {
            System.out.println(p);
        }
        System.out.println("***************************************");
    }

    // Afficher tous les produits d'une catégorie donnée
    @Transactional
    static void afficherTousLesProduitsDeLaCategorie(Long id) {
        System.out.println("***************************************");

        // Récupérer l'entité "Catégorie" ayant l'id en paramètres
        Optional<Categorie> cDOptional = categorieRepos.findById(id);

        if (cDOptional.isPresent()) {
            Categorie cD = cDOptional.get();
            System.out.println("Afficher tous les produits de la catégorie [" + id + "]");
            Collection<Produit> lC = cD.getProduits();
            for (Produit p : lC) {
                System.out.println(p);
            }

            // Modifier une catégorie existante par l'ajout d'un nouveau produit
            Produit p4 = new Produit("ADOL", 7.400, 20, date1);
            p4.setCategorie(cD);
            cD.getProduits().add(p4);
            categorieRepos.save(cD);  // Sauvegarder la catégorie après ajout du produit

        } else {
            System.out.println("Catégorie non existante...");
        }

        System.out.println("***************************************");
        // Supprimer une catégorie (si nécessaire)
        // Après avoir effectué toutes les modifications nécessaires
        System.out.println("*********************");
        System.out.println("Suppression de catégorie...");
        Optional<Categorie> csOptional = categorieRepos.findById(3L);
        csOptional.ifPresent(categorieRepos::delete);
        System.out.println("*********************");

        // Afficher la liste des produits après suppression de la catégorie
        afficherTousLesProduits();
        
     // Insérer une nouvelle  catégorie  avec l'ajout d'un nouveu produit 
        Produit p5 =new Produit("Stylo", 0.400, 20, date1 ); 
        Categorie cat3 = new Categorie("S", " BUREATIQUE"); 
        p5.setCategorie(cat3); 
        cat3.getProduits().add(p5); 
        categorieRepos.save(cat3); 
        //Afficher la liste des produits            
        afficherTousLesProduits();
    }
}
