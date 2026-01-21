# ⚡ Quick Start - Boutique de Thés

Guide de démarrage rapide en 5 minutes !

## 🎯 Prérequis

Avant de commencer, assurez-vous d'avoir :
- ✅ **JDK 17+** installé ([Télécharger](https://adoptium.net/))
- ✅ **Maven 3.6+** installé ([Télécharger](https://maven.apache.org/download.cgi))
- ✅ **MySQL 8+** installé et démarré ([Télécharger](https://dev.mysql.com/downloads/mysql/))
- ✅ Un navigateur web moderne

### Vérifier les installations

```bash
# Java
java -version
# Doit afficher : openjdk version "17..." ou supérieur

# Maven
mvn -version
# Doit afficher : Apache Maven 3.6...

# MySQL
mysql --version
# Doit afficher : mysql Ver 8...
```

## 🚀 Installation en 4 étapes

### Étape 1 : Créer la base de données

```bash
# Se connecter à MySQL
mysql -u root -p

# Créer la base de données
CREATE DATABASE boutique_thes;

# Vérifier
SHOW DATABASES;

# Quitter
exit;
```

### Étape 2 : Configurer l'application

**Option A : Utiliser les valeurs par défaut (recommandé pour test)**

Si votre MySQL utilise :
- Hôte : `localhost`
- Port : `3306`
- Utilisateur : `root`
- Mot de passe : (vide)

→ **Rien à faire !** Les valeurs par défaut sont déjà configurées.

**Option B : Personnaliser la configuration**

Créer un fichier `.env` à la racine du projet :

```bash
# Copier le template
cp .env.example .env

# Éditer avec vos valeurs
nano .env  # ou notepad .env sur Windows
```

Exemple de `.env` :
```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=boutique_thes
DB_USERNAME=root
DB_PASSWORD=votre_mot_de_passe
SHOW_SQL=true
FORMAT_SQL=true
```

### Étape 3 : Compiler et démarrer

```bash
# Se placer dans le répertoire du projet
cd jpa

# Compiler (télécharge les dépendances)
./mvnw clean install

# Démarrer l'application
./mvnw spring-boot:run
```

**Sur Windows :**
```cmd
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

**Attendre le message :**
```
Started BoutiqueThesApplication in X.XXX seconds
```

### Étape 4 : Accéder à l'application

Ouvrir votre navigateur à l'adresse :

👉 **http://localhost:8080**

Vous devriez voir la page d'accueil de la boutique de thés !

## 📝 Ajouter des données de test (optionnel)

Pour avoir des produits de démonstration :

```bash
# Ouvrir un nouveau terminal (laisser l'app tourner)
mysql -u root -p boutique_thes < src/main/resources/data-demo.sql
```

Rafraîchir la page dans le navigateur → 15 produits apparaissent !

## ✅ Vérification rapide

### Test 1 : Ajouter un produit
1. Cliquer sur **"Ajouter un thé"**
2. Remplir :
   - Nom : Test Sencha
   - Type : Vert
   - Origine : Japon
   - Prix : 25.00
   - Quantité : 10
   - Date : Aujourd'hui
3. Enregistrer
4. ✅ Le produit apparaît dans la liste

### Test 2 : Rechercher
1. Taper "sencha" dans la barre de recherche
2. Cliquer "Appliquer"
3. ✅ Seuls les produits contenant "sencha" s'affichent

### Test 3 : Trier
1. Cliquer sur l'en-tête "Prix"
2. ✅ Les produits se trient par prix croissant

### Test 4 : Modifier
1. Cliquer sur ✏️ à côté d'un produit
2. Changer la quantité
3. Enregistrer
4. ✅ La quantité est mise à jour

### Test 5 : Supprimer
1. Cliquer sur 🗑️
2. Confirmer
3. ✅ Le produit disparaît

## 🎉 Félicitations !

Votre application fonctionne correctement.

## 🛑 Arrêter l'application

Dans le terminal où l'app tourne :
- **Mac/Linux** : `Ctrl + C`
- **Windows** : `Ctrl + C`

## 📚 Suite

Maintenant que l'app fonctionne, explorez :

1. **README.md** : Présentation générale
2. **GUIDE_UTILISATEUR.md** : Guide complet des fonctionnalités
3. **DOCUMENTATION_TECHNIQUE.md** : Architecture et code source

## ❓ Problèmes courants

### L'application ne démarre pas

**Erreur : "Failed to configure a DataSource"**
```
Solution : Vérifier que MySQL est démarré
> sudo systemctl start mysql (Linux)
> brew services start mysql (Mac)
> Démarrer MySQL dans les Services (Windows)
```

**Erreur : "Access denied for user 'root'@'localhost'"**
```
Solution : Vérifier le mot de passe MySQL dans application.properties
ou définir DB_PASSWORD dans .env
```

**Erreur : "Unknown database 'boutique_thes'"**
```
Solution : Créer la base de données
> mysql -u root -p
> CREATE DATABASE boutique_thes;
```

**Erreur : Port 8080 déjà utilisé**
```
Solution : Changer le port dans application.properties
> server.port=8081
```

**Erreur : "No compiler is provided"**
```
Solution : Installer un JDK (pas seulement JRE)
Télécharger : https://adoptium.net/
```

### L'application démarre mais page blanche

**Vérifier la console :**
- Rechercher des erreurs en rouge
- Vérifier que Thymeleaf trouve les templates

**Vérifier l'URL :**
- Doit être exactement `http://localhost:8080`
- Pas `https://`

### Les données ne s'enregistrent pas

**Vérifier :**
1. La connexion MySQL (logs au démarrage)
2. Les erreurs de validation dans le formulaire
3. Les logs de la console

### CSS/Bootstrap ne charge pas

**Vérifier :**
- Connexion internet (Bootstrap chargé via CDN)
- Console du navigateur (F12) pour erreurs réseau

## 📞 Besoin d'aide ?

1. Lire la **DOCUMENTATION_TECHNIQUE.md**
2. Vérifier les logs dans la console
3. Rechercher l'erreur sur Google/Stack Overflow
4. Demander de l'aide à l'équipe

---

**Temps estimé de setup : 5-10 minutes**

Bon développement ! 🚀
