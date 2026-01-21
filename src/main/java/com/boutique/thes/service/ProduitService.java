package com.boutique.thes.service;

import com.boutique.thes.model.Produit;
import com.boutique.thes.repository.ProduitRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service gérant la logique métier des produits de thé.
 * Fait le lien entre le controller et le repository.
 */
@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    /**
     * Récupère tous les produits sans tri particulier
     * @return Liste de tous les produits
     */
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    /**
     * Récupère tous les produits avec tri
     * @param sort Critère de tri (ex: Sort.by("nom"))
     * @return Liste triée des produits
     */
    public List<Produit> getAllProduits(Sort sort) {
        return produitRepository.findAll(sort);
    }

    /**
     * Sauvegarde un produit (création ou mise à jour)
     * @param produit Le produit à sauvegarder
     * @return Le produit sauvegardé avec son ID
     */
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    /**
     * Trouve un produit par son identifiant.
     *
     * @param id L'identifiant du produit
     * @return Un Optional contenant le produit s'il existe, sinon Optional.empty()
     */
    public Optional<Produit> findById(Long id) {
        return produitRepository.findById(id);
    }

    /**
     * Supprime un produit par son identifiant.
     * Vérifie que le produit existe avant suppression.
     *
     * @param id L'identifiant du produit à supprimer
     * @throws IllegalArgumentException si le produit n'existe pas
     */
    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new IllegalArgumentException("Produit non trouvé avec l'ID : " + id);
        }
        produitRepository.deleteById(id);
    }

    /**
     * Recherche des produits par nom (insensible à la casse)
     * @param nom Le nom ou partie du nom à rechercher
     * @return Liste des produits correspondants
     */
    public List<Produit> searchByNom(String nom) {
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }

    /**
     * Recherche des produits par nom avec tri
     * @param nom Le nom ou partie du nom à rechercher
     * @param sort Critère de tri
     * @return Liste triée des produits correspondants
     */
    public List<Produit> searchByNom(String nom, Sort sort) {
        return produitRepository.findByNomContainingIgnoreCase(nom, sort);
    }

    /**
     * Filtre les produits par type de thé
     * @param typeThe Le type de thé recherché
     * @return Liste des produits de ce type
     */
    public List<Produit> findByTypeThe(String typeThe) {
        return produitRepository.findByTypeThe(typeThe);
    }

    /**
     * Filtre les produits par type avec tri
     * @param typeThe Le type de thé recherché
     * @param sort Critère de tri
     * @return Liste triée des produits de ce type
     */
    public List<Produit> findByTypeThe(String typeThe, Sort sort) {
        return produitRepository.findByTypeThe(typeThe, sort);
    }

    /**
     * Recherche avec filtre combiné : nom ET type
     * @param nom Nom ou partie du nom
     * @param typeThe Type de thé
     * @return Liste des produits correspondant aux deux critères
     */
    public List<Produit> searchByNomAndType(String nom, String typeThe) {
        return produitRepository.findByNomContainingIgnoreCaseAndTypeThe(nom, typeThe);
    }

    /**
     * Recherche avec filtre combiné et tri
     * @param nom Nom ou partie du nom
     * @param typeThe Type de thé
     * @param sort Critère de tri
     * @return Liste triée des produits correspondant aux deux critères
     */
    public List<Produit> searchByNomAndType(String nom, String typeThe, Sort sort) {
        return produitRepository.findByNomContainingIgnoreCaseAndTypeThe(nom, typeThe, sort);
    }

}
