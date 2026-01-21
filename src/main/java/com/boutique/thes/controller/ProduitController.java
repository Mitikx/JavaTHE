package com.boutique.thes.controller;

import com.boutique.thes.model.Produit;
import com.boutique.thes.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller gérant les requêtes HTTP pour les produits de thé.
 * Retourne des vues Thymeleaf (pas de @RestController).
 */
@Controller
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    /**
     * Affiche la liste des produits avec options de recherche, filtre et tri
     * 
     * @param search Paramètre optionnel pour rechercher par nom
     * @param typeThe Paramètre optionnel pour filtrer par type de thé
     * @param sort Paramètre optionnel pour trier (nom, prix, quantiteStock, dateReception)
     * @param model Modèle Spring pour passer des données à la vue
     * @return Le nom de la vue "index"
     */
    @GetMapping("/")
    public String listeProduits(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String typeThe,
            @RequestParam(required = false) String sort,
            Model model) {

        List<Produit> produits;
        Sort sortCriteria = null;

        if (sort != null && !sort.isEmpty()) {
            switch (sort) {
                case "prix":
                    sortCriteria = Sort.by(Sort.Direction.ASC, "prix");
                    break;
                case "prixDesc":
                    sortCriteria = Sort.by(Sort.Direction.DESC, "prix");
                    break;
                case "quantiteStock":
                    sortCriteria = Sort.by(Sort.Direction.ASC, "quantiteStock");
                    break;
                case "dateReception":
                    sortCriteria = Sort.by(Sort.Direction.DESC, "dateReception");
                    break;
                case "nom":
                default:
                    sortCriteria = Sort.by(Sort.Direction.ASC, "nom");
                    break;
            }
        }

        boolean hasSearch = search != null && !search.trim().isEmpty();
        boolean hasTypeFilter = typeThe != null && !typeThe.trim().isEmpty();

        if (hasSearch && hasTypeFilter) {
            if (sortCriteria != null) {
                produits = produitService.searchByNomAndType(search.trim(), typeThe, sortCriteria);
            } else {
                produits = produitService.searchByNomAndType(search.trim(), typeThe);
            }
        } else if (hasSearch) {
            if (sortCriteria != null) {
                produits = produitService.searchByNom(search.trim(), sortCriteria);
            } else {
                produits = produitService.searchByNom(search.trim());
            }
        } else if (hasTypeFilter) {
            if (sortCriteria != null) {
                produits = produitService.findByTypeThe(typeThe, sortCriteria);
            } else {
                produits = produitService.findByTypeThe(typeThe);
            }
        } else {
            if (sortCriteria != null) {
                produits = produitService.getAllProduits(sortCriteria);
            } else {
                produits = produitService.getAllProduits();
            }
        }
        model.addAttribute("produits", produits);
        model.addAttribute("search", search);
        model.addAttribute("typeThe", typeThe);
        model.addAttribute("currentSort", sort);

        return "index";
    }

    /**
     * Affiche le formulaire pour ajouter un nouveau produit
     * 
     * @param model Modèle Spring
     * @return Le nom de la vue "formulaire-produit"
     */
    @GetMapping("/nouveau")
    public String nouveauProduit(Model model) {
        model.addAttribute("produit", new Produit());
        return "formulaire-produit";
    }

    /**
     * Enregistre un nouveau produit ou met à jour un existant
     * 
     * @param produit Le produit à enregistrer (binding automatique des champs du formulaire)
     * @param bindingResult Résultat de la validation
     * @param model Modèle Spring
     * @return Redirection vers "/" si succès, sinon retour au formulaire avec erreurs
     */
    @PostMapping("/enregistrer")
    public String enregistrerProduit(
            @Valid @ModelAttribute("produit") Produit produit,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "formulaire-produit";
        }

        produitService.saveProduit(produit);

        return "redirect:/";
    }

    /**
     * Affiche le formulaire de modification pour un produit existant
     * 
     * @param id L'ID du produit à modifier
     * @param model Modèle Spring
     * @return Le nom de la vue "formulaire-produit" ou redirection si produit non trouvé
     */
    @GetMapping("/modifier/{id}")
    public String modifierProduit(@PathVariable Long id, Model model) {
        Produit produit = produitService.findById(id).orElse(null);

        if (produit == null) {
            return "redirect:/";
        }

        model.addAttribute("produit", produit);
        return "formulaire-produit";
    }

    /**
     * Met à jour un produit existant
     * 
     * @param id L'ID du produit à mettre à jour
     * @param produit Le produit avec les nouvelles données
     * @param bindingResult Résultat de la validation
     * @param model Modèle Spring
     * @return Redirection vers "/" si succès, sinon retour au formulaire
     */
    @PostMapping("/modifier/{id}")
    public String updateProduit(
            @PathVariable Long id,
            @Valid @ModelAttribute("produit") Produit produit,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            produit.setId(id);
            return "formulaire-produit";
        }

        produit.setId(id);

        produitService.saveProduit(produit);

        return "redirect:/";
    }

    /**
     * Supprime un produit
     * 
     * @param id L'ID du produit à supprimer
     * @param redirectAttributes Attributs pour messages flash
     * @return Redirection vers "/"
     */
    @PostMapping("/supprimer/{id}")
    public String supprimerProduit(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes
    ) {
        try {
            Produit produit = produitService.findById(id).orElse(null);
            if (produit == null) {
                redirectAttributes.addFlashAttribute("error", "Produit non trouvé avec l'ID : " + id);
                return "redirect:/";
            }
            
            String nomProduit = produit.getNom();
            produitService.deleteProduit(id);
            
            redirectAttributes.addFlashAttribute(
                "success", 
                "Le produit \"" + nomProduit + "\" a été supprimé avec succès"
            );
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression : " + e.getMessage());
        }
        
        return "redirect:/";
    }

}
