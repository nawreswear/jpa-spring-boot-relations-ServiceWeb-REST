package iset.master.spring.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import iset.master.spring.model.Produit;
import jakarta.transaction.Transactional;
public interface ProduitRepository extends JpaRepository<Produit, Long> {
@Query ("select p from Produit p where p.designation like %:x% ")
public List<Produit> findByDesignation(@Param ("x") String mc);

@Query("SELECT p FROM Produit p WHERE p.designation LIKE %:mc% AND p.prix > :prix")
List<Produit> findByDesignationAndPrix(@Param("mc") String mc, @Param("prix") double prix);
   @Transactional
   @Modifying
   @Query("UPDATE Produit p SET p.designation = :designation WHERE p.id = :id")
   int mettreAJourDesignation(@Param("id") Long idProduit, @Param("designation") String designation);

public List<Produit> findByPrixGreaterThan(double prixMin);

// Trier les produits par prix croissant
List<Produit> findAllByOrderByPrixAsc();

// Sélectionner les produits achetés après une date donnée
@Query("SELECT p FROM Produit p WHERE p.dateAchat > :date")
List<Produit> findByDateAchatAfter(@Param("date") Date date);
   }

