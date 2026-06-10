# Exercice Pratique : Système de Combat RPG en Java

## 🎯 Objectifs d'apprentissage
- Maîtriser **l'héritage** avec une hiérarchie de classes cohérente
- Utiliser le **polymorphisme** pour des comportements différents avec la même interface
- Implémenter des **méthodes abstraites** pour forcer les sous-classes à respecter un contrat
- Comprendre quand utiliser l'héritage vs les interfaces
- Gérer les **exceptions personnalisées**

---

## 📋 Contexte du Projet

Tu dois créer un système de combat pour un RPG simplifié. Les différents personnages ont des attaques différentes, mais tous suivent la même logique de combat.

### Comportements attendus :
- **Guerrier** : attaque normale (dégâts fixes) + coup spécial (rage) qui augmente les dégâts
- **Magicien** : attaque faible mais peut lancer un sort coûteux (beaucoup de dégâts)
- **Archer** : attaque moyenne avec probabilité de coup critique
- **Chevalier** : peut bloquer les attaques (réduit les dégâts reçus)

Tous les personnages ont :
- Une santé (HP)
- Une mana/énergie pour les attaques spéciales
- Une capacité à attaquer et subir des dégâts

---

## 🏗️ Structure demandée

### 1. Classe abstraite `Personnage`
C'est le **contrat de base** pour tous les combattants.

**Attributs communs** :
- `nom`, `sante`, `santeMaX`, `mana`, `manaMaX`, `force`

**Méthodes abstraites** (à implémenter dans chaque sous-classe) :
- `attaquer(Personnage adversaire)` : attaque normale
- `attaqueSpeciale(Personnage adversaire)` : attaque spéciale qui coûte du mana

**Méthodes concrètes** (communes à tous) :
- `subirDegats(int degats)` : réduit la santé
- `estEnVie()` : retourne true si santé > 0
- `afficherEtat()` : affiche HP et Mana actuels

### 2. Sous-classes concrètes

#### `Guerrier`
- **Attaque normale** : dégâts = force + aléatoire(1-10)
- **Coup spécial "Rage"** : coûte 30 mana, dégâts = force × 2

#### `Magicien`
- **Attaque normale** : dégâts = force / 2 (faible)
- **Sort "Boule de feu"** : coûte 40 mana, dégâts = force × 3

#### `Archer`
- **Attaque normale** : dégâts = force avec 20% de chance de coup critique (×2)
- **Attaque spéciale "Tir groupé"** : coûte 25 mana, lance 3 flèches (3 attaques normales)

#### `Chevalier`
- **Attaque normale** : dégâts = force
- **Compétence "Bouclier"** : coûte 20 mana, réduit les dégâts reçus de 50% le tour suivant

---

## ✅ Exigences d'implémentation

1. **Encapsulation** : tous les attributs `protected` ou `private` avec getters si nécessaire

2. **Exception personnalisée `ManaNonDisponibleException`** :
   - Levée quand on essaie d'utiliser une attaque spéciale sans assez de mana
   - Le constructeur peut afficher le nom du personnage et combien de mana il manque

3. **Polymorphisme en action** : une méthode `simulerCombat(Personnage p1, Personnage p2)`
   - Qui appelle `attaquer()` sur chaque combattant à tour de rôle
   - Sans connaître leur type exact (utiliser le type `Personnage`)
   - Les deux combattants s'attaquent à tour de rôle jusqu'à ce que l'un meure

4. **Méthode `toString()`** : affichage clair du personnage (nom, stats actuelles)

---

## 💡 Challenges supplémentaires (une fois les bases faites)

1. **Interface `Defenseur`** : certains personnages (Chevalier) peuvent implémenter une interface avec la méthode `bloquer()` pour réduire les dégâts

2. **Système de niveaux** : augmenter les stats après chaque victoire

3. **Inventaire d'équipement** : armes qui augmentent la force, armures qui réduisent les dégâts

4. **Tours de combat plus réalistes** : le joueur peut choisir entre `attaquer()` et `attaqueSpeciale()` chaque tour

5. **Statistiques** : tracker les dégâts totaux, attaques totales, taux de coup critique réel

---

## 🚀 Comment commencer

1. Crée d'abord la classe abstraite `Personnage` avec les méthodes abstraites
2. Implémente `Guerrier` en premier (le plus simple)
3. Teste avec un petit `main()` simple
4. Puis ajoute les autres classes une par une
5. Finalement, crée la classe `SimulateurCombat` avec la logique d'alternance

---

## 📚 Concepts testés

✅ Héritage simple (toutes les classes héritent de `Personnage`)  
✅ Polymorphisme (chaque classe a sa propre `attaquer()`)  
✅ Classes abstraites et méthodes abstraites  
✅ Encapsulation (`protected`, accès contrôlé)  
✅ Exceptions personnalisées  
✅ Réutilisation de code (méthodes communes)  
✅ `super` (appeler le constructeur parent)  
✅ Surcharge vs Redéfinition (override)

---

## 💾 Structure des fichiers

```
src/
├── Personnage.java           (classe abstraite)
├── Guerrier.java
├── Magicien.java
├── Archer.java
├── Chevalier.java
├── ManaNonDisponibleException.java
└── SimulateurCombat.java     (main + simulerCombat)
```