# 🍵 Boutique de Thés - Application Spring Boot

Application web de gestion d'une boutique de thés développée avec **Spring Boot 3.2.1**, **Thymeleaf** et **MySQL**.

## 📋 Fonctionnalités

### Gestion des produits
- ✅ **Création** de nouveaux produits de thé
- ✅ **Modification** des produits existants
- ✅ **Suppression** avec confirmation JavaScript
- ✅ **Affichage** de tous les produits dans un tableau responsive

### Fonctionnalités avancées
- 🔍 **Recherche** par nom (insensible à la casse)
- 🏷️ **Filtre** par type de thé (Vert, Noir, Oolong, Blanc, Pu-erh)
- 📊 **Tri** sur les colonnes : Nom, Prix, Quantité, Date de réception
- ✔️ **Validation** complète des formulaires (côté serveur)
- 🎨 **Interface moderne** avec Bootstrap 5

## 🛠️ Technologies utilisées

- **Java 17**
- **Spring Boot 3.2.1**
  - Spring Data JPA
  - Spring Web MVC
  - Spring Validation
- **Thymeleaf** (moteur de templates)
- **MySQL 8** (base de données)
- **Bootstrap 5** (design responsive)
- **Maven** (gestion des dépendances)

## 📦 Structure du projet

```
src/main/java/com/boutique/thes/
├── BoutiqueThesApplication.java    # Classe principale
├── model/
│   └── Produit.java                # Entité JPA avec validations
├── repository/
│   └── ProduitRepository.java      # Interface Spring Data JPA
├── service/
│   └── ProduitService.java         # Logique métier
└── controller/
    └── ProduitController.java      # Controller MVC

src/main/resources/
├── application.properties          # Configuration (avec variables d'env)
└── templates/
    ├── index.html                  # Liste des produits
    └── formulaire-produit.html     # Formulaire d'ajout/modification
```

## 🚀 Installation et démarrage

### Prérequis
- **JDK 17** ou supérieur
- **Maven 3.6+**
- **MySQL 8** installé et en cours d'exécution

### Étapes

1. **Cloner le projet**
   ```bash
   git clone <url-du-repo>
   cd jpa
   ```

2. **Créer la base de données MySQL**
   ```sql
   CREATE DATABASE boutique_thes;
   ```

3. **Configurer les variables d'environnement** (optionnel)
   
   Copier `.env.example` vers `.env` et ajuster :
   ```env
   DB_HOST=localhost
   DB_PORT=3306
   DB_NAME=boutique_thes
   DB_USERNAME=root
   DB_PASSWORD=votre_mot_de_passe
   ```

   Ou utiliser les valeurs par défaut dans `application.properties`.

4. **Compiler et démarrer l'application**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Accéder à l'application**
   
   Ouvrir le navigateur : [http://localhost:8080](http://localhost:8080)

## 📊 Modèle de données

### Entité Produit

| Champ           | Type        | Contraintes                                    |
|-----------------|-------------|------------------------------------------------|
| id              | Long        | Auto-généré, Clé primaire                      |
| nom             | String      | Obligatoire, Max 100 caractères                |
| typeThe         | String      | Obligatoire, Max 50 (Vert, Noir, Oolong, etc.) |
| origine         | String      | Obligatoire, Max 100 (Chine, Japon, etc.)      |
| prix            | BigDecimal  | Obligatoire, Entre 5.00 et 100.00              |
| quantiteStock   | Integer     | Obligatoire, Minimum 0                         |
| description     | String      | Optionnel, Max 500 caractères                  |
| dateReception   | LocalDate   | Obligatoire, Ne peut pas être dans le futur    |

## 🎯 Endpoints de l'application

| Méthode | URL              | Description                              |
|---------|------------------|------------------------------------------|
| GET     | `/`              | Liste des produits (avec recherche/tri)  |
| GET     | `/nouveau`       | Formulaire d'ajout                       |
| POST    | `/enregistrer`   | Enregistre un nouveau produit            |
| GET     | `/modifier/{id}` | Formulaire de modification               |
| POST    | `/modifier/{id}` | Met à jour le produit                    |
| GET     | `/supprimer/{id}`| Supprime le produit                      |

## 🔍 Utilisation

### Ajouter un thé
1. Cliquer sur "Ajouter un thé"
2. Remplir le formulaire
3. Cliquer sur "Enregistrer"

### Rechercher / Filtrer
- Utiliser la barre de recherche pour trouver par nom
- Sélectionner un type dans le menu déroulant
- Cliquer sur "Appliquer"

### Trier
- Cliquer sur les en-têtes de colonnes (Nom, Prix, Quantité, Date)

### Modifier un produit
- Cliquer sur l'icône ✏️ dans la colonne Actions
- Modifier les champs
- Enregistrer

### Supprimer un produit
- Cliquer sur l'icône 🗑️
- Confirmer la suppression

## ⚙️ Configuration

### Variables d'environnement disponibles

| Variable      | Défaut         | Description                    |
|---------------|----------------|--------------------------------|
| DB_HOST       | localhost      | Hôte MySQL                     |
| DB_PORT       | 3306           | Port MySQL                     |
| DB_NAME       | boutique_thes  | Nom de la base de données      |
| DB_USERNAME   | root           | Utilisateur MySQL              |
| DB_PASSWORD   | (vide)         | Mot de passe MySQL             |
| SHOW_SQL      | false          | Afficher les requêtes SQL      |
| FORMAT_SQL    | false          | Formater les requêtes SQL      |

## 🧪 Bonnes pratiques implémentées

- ✅ **BigDecimal** pour les montants monétaires (pas de float)
- ✅ **Integer** au lieu de int pour les quantités (nullable)
- ✅ **Validations Jakarta Bean Validation** complètes
- ✅ **Variables d'environnement** pour la configuration sensible
- ✅ **Architecture en couches** (Controller → Service → Repository)
- ✅ **Spring Data JPA** avec méthodes de requête dérivées
- ✅ **Templates Thymeleaf** avec gestion des erreurs
- ✅ **Design responsive** avec Bootstrap 5

## 📝 Auteur

Projet développé dans le cadre de l'apprentissage de Spring Boot et JPA.

## 📄 Licence

Ce projet est libre d'utilisation à des fins éducatives.
