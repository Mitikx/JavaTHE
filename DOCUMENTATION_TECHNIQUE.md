# 🔧 Documentation Technique - Boutique de Thés

## 📐 Architecture

### Pattern MVC (Model-View-Controller)

```
┌─────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                    │
│  ┌───────────────────────────────────────────────────┐  │
│  │   Thymeleaf Templates (Views)                     │  │
│  │   - index.html                                    │  │
│  │   - formulaire-produit.html                       │  │
│  └───────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
                          ↕
┌─────────────────────────────────────────────────────────┐
│                   CONTROLLER LAYER                       │
│  ┌───────────────────────────────────────────────────┐  │
│  │   ProduitController                               │  │
│  │   - Gère les requêtes HTTP                        │  │
│  │   - Validation des formulaires                    │  │
│  │   - Mapping des endpoints                         │  │
│  └───────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
                          ↕
┌─────────────────────────────────────────────────────────┐
│                     SERVICE LAYER                        │
│  ┌───────────────────────────────────────────────────┐  │
│  │   ProduitService                                  │  │
│  │   - Logique métier                                │  │
│  │   - Orchestration des opérations                  │  │
│  └───────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
                          ↕
┌─────────────────────────────────────────────────────────┐
│                   REPOSITORY LAYER                       │
│  ┌───────────────────────────────────────────────────┐  │
│  │   ProduitRepository (Spring Data JPA)             │  │
│  │   - Accès aux données                             │  │
│  │   - Méthodes de requête dérivées                  │  │
│  └───────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
                          ↕
┌─────────────────────────────────────────────────────────┐
│                      DATA LAYER                          │
│  ┌───────────────────────────────────────────────────┐  │
│  │   Produit Entity (JPA)                            │  │
│  │   - Mapping ORM                                   │  │
│  │   - Validations Jakarta                           │  │
│  └───────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
                          ↕
                    MySQL Database
```

## 🗂️ Structure des packages

```
com.boutique.thes
│
├── BoutiqueThesApplication.java    # Point d'entrée Spring Boot
│
├── model/                          # Entités JPA
│   └── Produit.java                # Entité produit avec validations
│
├── repository/                     # Couche d'accès aux données
│   └── ProduitRepository.java      # Interface Spring Data JPA
│
├── service/                        # Logique métier
│   └── ProduitService.java         # Service de gestion des produits
│
└── controller/                     # Contrôleurs MVC
    └── ProduitController.java      # Controller REST/MVC
```

## 🔍 Détail des composants

### 1. Entité Produit (model/Produit.java)

**Annotations JPA utilisées :**
- `@Entity` : Marque la classe comme entité JPA
- `@Table(name = "produit")` : Définit le nom de la table
- `@Id` : Clé primaire
- `@GeneratedValue(strategy = IDENTITY)` : Auto-incrémentation
- `@Column` : Configuration des colonnes (nullable, length, precision, scale)

**Annotations de validation :**
- `@NotBlank` : Champ non vide (String)
- `@NotNull` : Champ obligatoire
- `@Size` : Contrainte de longueur
- `@DecimalMin` / `@DecimalMax` : Limites pour BigDecimal
- `@Min` : Valeur minimale pour Integer
- `@PastOrPresent` : Date passée ou présente

**Types utilisés :**
```java
- Long id                    // Clé primaire auto-générée
- String nom                 // VARCHAR(100)
- String typeThe             // VARCHAR(50)
- String origine             // VARCHAR(100)
- BigDecimal prix            // DECIMAL(10,2) - IMPORTANT: pas float!
- Integer quantiteStock      // INT - IMPORTANT: pas int primitif!
- String description         // VARCHAR(500)
- LocalDate dateReception    // DATE
```

### 2. Repository (repository/ProduitRepository.java)

**Héritage :**
```java
public interface ProduitRepository extends JpaRepository<Produit, Long>
```

**Méthodes personnalisées (Query Methods) :**
```java
// Recherche insensible à la casse
List<Produit> findByNomContainingIgnoreCase(String nom);
List<Produit> findByNomContainingIgnoreCase(String nom, Sort sort);

// Filtre par type
List<Produit> findByTypeThe(String typeThe);
List<Produit> findByTypeThe(String typeThe, Sort sort);

// Recherche combinée
List<Produit> findByNomContainingIgnoreCaseAndTypeThe(String nom, String typeThe);
List<Produit> findByNomContainingIgnoreCaseAndTypeThe(String nom, String typeThe, Sort sort);
```

**Méthodes héritées de JpaRepository :**
- `findAll()` : Tous les produits
- `findAll(Sort)` : Tous avec tri
- `findById(Long)` : Par ID
- `save(Produit)` : Création/MAJ
- `deleteById(Long)` : Suppression

### 3. Service (service/ProduitService.java)

**Responsabilités :**
- Encapsule la logique métier
- Fait le lien entre controller et repository
- Gère les opérations complexes

**Méthodes principales :**
```java
// CRUD de base
List<Produit> getAllProduits()
Produit saveProduit(Produit produit)
Produit findById(Long id)
void deleteProduit(Long id)

// Recherche et filtrage
List<Produit> searchByNom(String nom)
List<Produit> findByTypeThe(String typeThe)
List<Produit> searchByNomAndType(String nom, String typeThe)

// Avec tri
List<Produit> getAllProduits(Sort sort)
List<Produit> searchByNom(String nom, Sort sort)
// etc.
```

**Injection de dépendances :**
```java
@Autowired
private ProduitRepository produitRepository;
```

### 4. Controller (controller/ProduitController.java)

**Annotations :**
- `@Controller` : Marque comme contrôleur MVC (pas REST)
- `@GetMapping` : Route GET
- `@PostMapping` : Route POST
- `@PathVariable` : Variable dans l'URL
- `@RequestParam` : Paramètre de requête (query string)
- `@Valid` : Active la validation
- `@ModelAttribute` : Binding de formulaire

**Endpoints détaillés :**

#### GET /
```java
@GetMapping("/")
public String listeProduits(
    @RequestParam(required = false) String search,
    @RequestParam(required = false) String typeThe,
    @RequestParam(required = false) String sort,
    Model model)
```
- Paramètres optionnels pour recherche/filtre/tri
- Logique conditionnelle pour combiner les critères
- Retourne la vue "index"

#### GET /nouveau
```java
@GetMapping("/nouveau")
public String nouveauProduit(Model model)
```
- Ajoute un Produit vide au modèle
- Retourne la vue "formulaire-produit"

#### POST /enregistrer
```java
@PostMapping("/enregistrer")
public String enregistrerProduit(
    @Valid @ModelAttribute("produit") Produit produit,
    BindingResult bindingResult,
    Model model)
```
- `@Valid` déclenche la validation
- `BindingResult` contient les erreurs
- Si erreurs : retour au formulaire
- Sinon : sauvegarde et redirection

#### GET /modifier/{id}
```java
@GetMapping("/modifier/{id}")
public String modifierProduit(@PathVariable Long id, Model model)
```
- Charge le produit existant
- Retourne le formulaire pré-rempli

#### POST /modifier/{id}
```java
@PostMapping("/modifier/{id}")
public String updateProduit(
    @PathVariable Long id,
    @Valid @ModelAttribute("produit") Produit produit,
    BindingResult bindingResult)
```
- Même logique que /enregistrer
- Force l'ID pour la mise à jour

#### GET /supprimer/{id}
```java
@GetMapping("/supprimer/{id}")
public String supprimerProduit(@PathVariable Long id)
```
- Suppression directe
- Redirection vers liste

## 🎨 Templates Thymeleaf

### Syntaxe importante

**Namespace :**
```html
<html xmlns:th="http://www.thymeleaf.org">
```

**Expressions :**
```html
${variable}              <!-- Variable -->
*{champ}                 <!-- Champ de l'objet du formulaire -->
@{/url}                  <!-- URL -->
#{message}               <!-- Message i18n -->
```

**Directives utilisées :**
```html
th:text="${var}"         <!-- Texte -->
th:value="${var}"        <!-- Valeur d'input -->
th:field="*{nom}"        <!-- Binding formulaire -->
th:object="${produit}"   <!-- Objet du formulaire -->
th:each="p : ${list}"    <!-- Boucle -->
th:if="${condition}"     <!-- Condition -->
th:unless="${condition}" <!-- Condition inverse -->
th:href="@{/url}"        <!-- Lien -->
th:action="@{/action}"   <!-- Action formulaire -->
th:errors="*{champ}"     <!-- Erreurs de validation -->
```

**Utilitaires Thymeleaf :**
```html
${#lists.isEmpty(list)}                    <!-- Liste vide ? -->
${#lists.size(list)}                       <!-- Taille de la liste -->
${#numbers.formatDecimal(num, 1, 2)}       <!-- Format nombre -->
${#temporals.format(date, 'dd/MM/yyyy')}   <!-- Format date -->
${#strings.abbreviate(text, 50)}           <!-- Tronquer texte -->
${#fields.hasErrors('nom')}                <!-- Erreur sur champ ? -->
```

### index.html - Points clés

**Formulaire de recherche :**
```html
<form method="get" action="/">
    <input name="search" th:value="${search}">
    <select name="typeThe">
        <option th:selected="${typeThe == 'Vert'}">Vert</option>
    </select>
</form>
```

**Tableau avec boucle :**
```html
<tr th:each="produit : ${produits}">
    <td th:text="${produit.nom}">Nom</td>
    <td th:text="${#numbers.formatDecimal(produit.prix, 1, 2)} + ' €'">Prix</td>
</tr>
```

**Badges conditionnels :**
```html
<span th:if="${produit.typeThe == 'Vert'}" class="badge bg-success">Vert</span>
```

**Lien avec paramètre :**
```html
<a th:href="@{/modifier/{id}(id=${produit.id})}">Modifier</a>
```

### formulaire-produit.html - Points clés

**Binding de formulaire :**
```html
<form th:action="@{/enregistrer}" th:object="${produit}">
    <input th:field="*{nom}">
</form>
```

**Affichage des erreurs :**
```html
<input th:field="*{nom}" 
       th:classappend="${#fields.hasErrors('nom')} ? 'is-invalid' : ''">
<div th:if="${#fields.hasErrors('nom')}" th:errors="*{nom}"></div>
```

**Titre conditionnel :**
```html
<h1 th:text="${produit.id != null ? 'Modifier' : 'Ajouter'}">Titre</h1>
```

**Action conditionnelle :**
```html
<form th:action="@{${produit.id != null ? '/modifier/' + produit.id : '/enregistrer'}}">
```

## 🔐 Configuration

### application.properties

**Variables d'environnement avec valeurs par défaut :**
```properties
${VAR_NAME:defaultValue}
```

**Exemples :**
```properties
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:boutique_thes}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
```

**Configuration JPA :**
```properties
spring.jpa.hibernate.ddl-auto=update     # Crée/met à jour le schéma auto
spring.jpa.show-sql=${SHOW_SQL:false}    # Affiche les requêtes SQL
spring.jpa.properties.hibernate.format_sql=${FORMAT_SQL:false}
```

## 🧪 Bonnes pratiques implémentées

### 1. Types de données
✅ **BigDecimal pour les prix** (jamais float/double pour l'argent)
✅ **Integer au lieu de int** (permet null, meilleur pour JPA)
✅ **LocalDate** pour les dates (API moderne)

### 2. Validations
✅ **Validation côté serveur** obligatoire (Jakarta Bean Validation)
✅ **Messages d'erreur personnalisés** en français
✅ **Contraintes de taille** sur tous les champs String

### 3. Sécurité
✅ **Pas de credentials hardcodés** (variables d'environnement)
✅ **Validation des inputs** (protection contre injections)
✅ **Confirmation de suppression** (JavaScript)

### 4. Architecture
✅ **Séparation en couches** (Controller → Service → Repository)
✅ **Injection de dépendances** (@Autowired)
✅ **Spring Data JPA** (pas de SQL manuel pour le CRUD)

### 5. Code
✅ **Commentaires JavaDoc** sur toutes les méthodes publiques
✅ **Noms explicites** (pas d'abréviations)
✅ **Gestion des cas d'erreur** (produit non trouvé, etc.)

## 📊 Schéma de base de données

```sql
CREATE TABLE produit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    type_the VARCHAR(50) NOT NULL,
    origine VARCHAR(100) NOT NULL,
    prix DECIMAL(10,2) NOT NULL,
    quantite_stock INT NOT NULL,
    description VARCHAR(500),
    date_reception DATE NOT NULL
);
```

**Indexes suggérés (pour performance) :**
```sql
CREATE INDEX idx_nom ON produit(nom);
CREATE INDEX idx_type_the ON produit(type_the);
CREATE INDEX idx_date_reception ON produit(date_reception);
```

## 🚀 Déploiement

### Profils Spring
Créer des fichiers pour chaque environnement :
- `application-dev.properties` (développement)
- `application-prod.properties` (production)

Activer avec :
```bash
java -jar app.jar --spring.profiles.active=prod
```

### Variables d'environnement en production
```bash
export DB_HOST=prod-mysql-server
export DB_USERNAME=prod_user
export DB_PASSWORD=secure_password
export SHOW_SQL=false
```

## 📚 Dépendances Maven

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- Database -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## 🔧 Extensions possibles

### Fonctionnalités à ajouter
1. **Pagination** : Afficher 10 produits par page
2. **Export CSV/Excel** : Télécharger la liste
3. **Images** : Upload d'images de produits
4. **Stock alerts** : Email quand quantité < seuil
5. **Authentification** : Spring Security
6. **API REST** : Endpoints JSON pour mobile
7. **Multi-langue** : i18n avec messages.properties

### Améliorations techniques
1. **Tests unitaires** : JUnit + Mockito
2. **Tests d'intégration** : Spring Boot Test
3. **Cache** : Redis pour performances
4. **Logging** : SLF4J + Logback
5. **Métriques** : Spring Boot Actuator
6. **Documentation API** : Swagger/OpenAPI
7. **CI/CD** : GitHub Actions / Jenkins

---

**Auteur** : Projet pédagogique Spring Boot  
**Version** : 1.0.0  
**Date** : Janvier 2026
