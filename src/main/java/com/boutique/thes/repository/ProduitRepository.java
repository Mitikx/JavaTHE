package com.boutique.thes.repository;

import com.boutique.thes.model.Produit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'entité Produit.
 * Spring Data JPA génère automatiquement l'implémentation des méthodes.
 */
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    /**
     * Recherche les produits dont le nom contient la chaîne donnée
     * @param nom La chaîne à rechercher dans le nom
     * @return Liste des produits correspondants
     */
    List<Produit> findByNomContainingIgnoreCase(String nom);

    /**
     * Recherche les produits par nom avec tri
     * @param nom La chaîne à rechercher
     * @param sort Critère de tri
     * @return Liste triée des produits correspondants
     */
    List<Produit> findByNomContainingIgnoreCase(String nom, Sort sort);

    /**
     * Filtre les produits par type de thé
     * @param typeThe Le type de thé exact
     * @return Liste des produits de ce type
     */
    List<Produit> findByTypeThe(String typeThe);

    /**
     * Filtre les produits par type avec tri
     * @param typeThe Le type de thé
     * @param sort Critère de tri
     * @return Liste triée des produits
     */
    List<Produit> findByTypeThe(String typeThe, Sort sort);

    /**
     * Recherche combinée : nom ET type de thé
     * @param nom Nom ou partie du nom
     * @param typeThe Type de thé exact
     * @return Liste des produits correspondants
     */
    List<Produit> findByNomContainingIgnoreCaseAndTypeThe(String nom, String typeThe);

    /**
     * Recherche combinée avec tri
     * @param nom Nom ou partie du nom
     * @param typeThe Type de thé
     * @param sort Critère de tri
     * @return Liste triée des produits correspondants
     */
    List<Produit> findByNomContainingIgnoreCaseAndTypeThe(String nom, String typeThe, Sort sort);

}
