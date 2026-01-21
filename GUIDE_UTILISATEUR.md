# 📘 Guide Utilisateur - Boutique de Thés

## 🎯 Vue d'ensemble

Cette application permet de gérer un inventaire de produits de thé avec des fonctionnalités complètes de CRUD (Create, Read, Update, Delete), de recherche, de filtrage et de tri.

## 📋 Fonctionnalités détaillées

### 1. Page d'accueil (Liste des produits)

#### Affichage
- **Tableau responsive** affichant tous les produits
- **Colonnes** : ID, Nom, Type, Origine, Prix, Quantité, Description, Date de réception, Actions
- **Badges colorés** pour les types de thé :
  - 🟢 Vert (badge vert)
  - 🔴 Noir (badge rouge)
  - 🟡 Oolong (badge jaune)
  - ⚪ Blanc (badge blanc)
  - ⚫ Pu-erh (badge noir)
- **Formatage** :
  - Prix : 2 décimales + symbole €
  - Date : Format dd/MM/yyyy
  - Description : Tronquée à 50 caractères

#### Recherche
1. **Champ de recherche** : Tapez un nom ou partie du nom
2. **Insensible à la casse** : "sencha" trouvera "Sencha Premium"
3. Cliquez sur **"Appliquer"**

#### Filtrage par type
1. Sélectionnez un type dans le menu déroulant
2. Laissez vide pour "Tous les types"
3. Cliquez sur **"Appliquer"**

#### Recherche + Filtre combinés
- Vous pouvez combiner recherche ET filtre
- Exemple : Rechercher "premium" dans les thés "Vert"

#### Tri des colonnes
Cliquez sur les en-têtes de colonnes pour trier :
- **Nom** : Tri alphabétique A→Z
- **Prix** : Tri croissant (du moins cher au plus cher)
- **Quantité** : Tri croissant
- **Date de réception** : Tri décroissant (plus récent en premier)

Le tri est conservé même avec recherche/filtre actifs.

### 2. Ajouter un thé

1. Cliquez sur **"Ajouter un thé"** (bouton vert)
2. Remplissez le formulaire :

#### Champs obligatoires (*)
- **Nom** : Max 100 caractères
  - Exemple : "Sencha Premium", "Dragon Well"
- **Type de thé** : Menu déroulant
  - Options : Vert, Noir, Oolong, Blanc, Pu-erh
- **Origine** : Menu déroulant
  - Options : Chine, Japon, Inde, Sri Lanka, Taiwan
- **Prix** : Entre 5,00€ et 100,00€
  - Format : 2 décimales (ex: 25.50)
- **Quantité en stock** : Minimum 0
  - Nombre entier (ex: 50)
- **Date de réception** : Ne peut pas être dans le futur
  - Format : dd/MM/yyyy

#### Champ optionnel
- **Description** : Max 500 caractères
  - Décrivez les caractéristiques du thé

3. Cliquez sur **"Enregistrer"**
4. En cas d'erreur, les messages s'affichent sous chaque champ

### 3. Modifier un produit

1. Dans la colonne **Actions**, cliquez sur l'icône **✏️ (crayon)**
2. Le formulaire se pré-remplit avec les données existantes
3. Modifiez les champs souhaités
4. Cliquez sur **"Enregistrer"**
5. Cliquez sur **"Annuler"** pour revenir sans sauvegarder

### 4. Supprimer un produit

1. Dans la colonne **Actions**, cliquez sur l'icône **🗑️ (poubelle)**
2. Une fenêtre de confirmation apparaît :
   ```
   Êtes-vous sûr de vouloir supprimer [Nom du produit] ?
   ```
3. Cliquez sur **"OK"** pour confirmer, ou **"Annuler"**
4. Le produit est supprimé définitivement

⚠️ **Attention** : Cette action est irréversible !

## ✅ Validations

### Côté serveur (Java)
Toutes les données sont validées avant enregistrement :

| Champ          | Validation                                      |
|----------------|-------------------------------------------------|
| Nom            | Obligatoire, max 100 caractères                 |
| Type           | Obligatoire, max 50 caractères                  |
| Origine        | Obligatoire, max 100 caractères                 |
| Prix           | Obligatoire, entre 5.00 et 100.00               |
| Quantité       | Obligatoire, minimum 0                          |
| Description    | Optionnel, max 500 caractères                   |
| Date réception | Obligatoire, ne peut pas être dans le futur     |

### Messages d'erreur
Si une validation échoue :
- Le formulaire est ré-affiché
- Les erreurs apparaissent en **rouge** sous chaque champ
- Les champs en erreur sont **bordés de rouge**
- Les valeurs saisies sont conservées

## 🎨 Interface utilisateur

### Design
- **Bootstrap 5** : Interface moderne et responsive
- **Icônes Bootstrap Icons** : Visuels clairs
- **Dégradé violet** dans l'en-tête
- **Cartes avec ombres** pour les sections

### Responsive
L'application s'adapte à toutes les tailles d'écran :
- 📱 **Mobile** : Tableau scrollable horizontalement
- 💻 **Desktop** : Affichage optimal

### Accessibilité
- Labels explicites sur tous les champs
- Boutons avec icônes et texte
- Messages d'aide sous les champs

## 📊 Exemples d'utilisation

### Scénario 1 : Ajouter un nouveau thé reçu
1. Accédez à la page d'accueil
2. Cliquez sur "Ajouter un thé"
3. Remplissez :
   - Nom : "Matcha Bio"
   - Type : Vert
   - Origine : Japon
   - Prix : 45.00
   - Quantité : 20
   - Description : "Thé vert en poudre de qualité cérémoniale"
   - Date : Aujourd'hui
4. Enregistrez

### Scénario 2 : Mettre à jour le stock après une vente
1. Recherchez le produit (ex: "Matcha")
2. Cliquez sur ✏️
3. Modifiez la quantité (ex: 15 au lieu de 20)
4. Enregistrez

### Scénario 3 : Trouver tous les thés chinois en stock
1. Dans le filtre "Type", sélectionnez la valeur souhaitée
2. OU laissez vide et recherchez "Chine" dans le champ de recherche
3. Appliquez

### Scénario 4 : Identifier les produits les plus chers
1. Cliquez sur l'en-tête de colonne "Prix"
2. Le tri s'applique automatiquement
3. Les produits les moins chers apparaissent en premier

## ❓ FAQ

**Q : Puis-je supprimer plusieurs produits à la fois ?**  
R : Non, la suppression se fait un par un avec confirmation.

**Q : Les recherches sont-elles sensibles aux accents ?**  
R : Non, la recherche est insensible à la casse.

**Q : Que se passe-t-il si je mets un prix négatif ?**  
R : Le formulaire affiche une erreur. Le prix doit être entre 5€ et 100€.

**Q : Puis-je ajouter une date de réception future ?**  
R : Non, la validation refuse les dates futures.

**Q : Comment réinitialiser les filtres ?**  
R : Videz les champs et cliquez sur "Appliquer", ou retournez à la page d'accueil.

## 🐛 En cas de problème

Si vous rencontrez une erreur :
1. Vérifiez que tous les champs obligatoires sont remplis
2. Vérifiez les formats (prix avec 2 décimales, quantité entière)
3. Assurez-vous que la date n'est pas dans le futur
4. Contactez l'administrateur si le problème persiste

## 📞 Support

Pour toute question ou suggestion d'amélioration, contactez l'équipe de développement.
