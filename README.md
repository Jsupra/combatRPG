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
- **Magicien** : attaque faible, mais peut lancer un sort coûteux (beaucoup de dégâts)
- **Archer** : attaque moyenne avec probabilité de coup critique
- **Chevalier** : peut bloquer les attaques (réduit les dégâts reçus)

Tous les personnages ont :
- Une santé (HP)
- Un mana/énergie pour les attaques spéciales
- Une capacité à attaquer et subir des dégâts

---

## 🏗️ Structure demandée

### 1. Classe abstraite `Personnage`
C'est le **contrat de base** pour tous les combattants.

```java
public abstract class Personnage {
    // Attributs communs à tous les personnages
    protected String nom;
    protected int sante;
    protected int santeMaX;
    protected int mana;
    protected int manaMaX;
    protected int force; // dégâts de base
    
    // Constructeur
    public Personnage(String nom, int sante, int mana, int force) {
        // À implémenter
    }
    
    // Méthodes abstraites - CHAQUE sous-classe DOIT les implémenter
    public abstract void attaquer(Personnage adversaire);
    public abstract void attaqueSpeciale(Personnage adversaire);
    
    // Méthode concrète commune à tous
    public void subirDegats(int degats) {
        // À implémenter
    }
    
    // Méthodes utilitaires
    public boolean estEnVie() {
        return sante > 0;
    }
    
    public void afficherEtat() {
        System.out.printf("%s - HP: %d/%d | Mana: %d/%d%n", 
            nom, sante, santeMaX, mana, manaMaX);
    }
}
```

### 2. Sous-classes concrètes

#### `Guerrier.java`
- Attaque normale : dégâts = force + random(1-10)
- Coup spécial "Rage" : dépense 30 mana, dégâts = force × 2

#### `Magicien.java`
- Attaque normale : dégâts = force / 2 (faible)
- Sort "Boule de feu" : dépense 40 mana, dégâts = force × 3

#### `Archer.java`
- Attaque normale : dégâts = force avec 20% de chance de coup critique (×2)
- Attaque spéciale "Tir groupé" : dépense 25 mana, 3 flèches (3 attaques normales)

#### `Chevalier.java`
- Attaque normale : dégâts = force
- Compétence "Bouclier" : dépense 20 mana, réduit tous les dégâts reçus de 50% pendant le prochain tour

---

## ✅ Exigences d'implémentation

1. **Encapsulation** : tous les attributs `protected` ou `private` avec getters/setters si nécessaire

2. **Exception personnalisée** : créer `ManaNonDisponibleException` 
   - Levée quand on essaie d'utiliser une attaque spéciale sans assez de mana

3. **Polymorphisme** : une méthode `simulerCombat(Personnage p1, Personnage p2)` 
   - Qui appelle `attaquer()` sur chaque combattant à tour de rôle
   - Sans connaître leur type exact (utiliser le type `Personnage`)

4. **Méthode `toString()`** : affichage clair du personnage

---

## 🎮 Classe principale : `SimulateurCombat.java`

```java
public class SimulateurCombat {
    public static void main(String[] args) {
        // Créer des personnages
        Guerrier guerrier = new Guerrier("Aragorn", 100, 50, 15);
        Magicien magicien = new Magicien("Gandalf", 70, 80, 10);
        Archer archer = new Archer("Legolas", 80, 40, 12);
        Chevalier chevalier = new Chevalier("Boromir", 110, 60, 14);
        
        // Combat : Guerrier vs Magicien
        System.out.println("=== COMBAT : Guerrier vs Magicien ===\n");
        simulerCombat(guerrier, magicien);
    }
    
    public static void simulerCombat(Personnage p1, Personnage p2) {
        int tour = 1;
        
        while (p1.estEnVie() && p2.estEnVie()) {
            System.out.printf("\n--- Tour %d ---\n", tour);
            
            // Attaque de p1
            System.out.printf("%s attaque !\n", p1.getNom());
            p1.attaquer(p2);
            p2.afficherEtat();
            
            if (!p2.estEnVie()) break;
            
            // Attaque de p2
            System.out.printf("%s contre-attaque !\n", p2.getNom());
            p2.attaquer(p1);
            p1.afficherEtat();
            
            tour++;
        }
        
        System.out.printf("\n🏆 %s a gagné !\n", 
            p1.estEnVie() ? p1.getNom() : p2.getNom());
    }
}
```

---

## 💡 Challenges supplémentaires (une fois les bases faites)

1. **Interface `Defenseur`** : certains personnages peuvent bloquer
   ```java
   public interface Defenseur {
       int bloquer();
       void mettreEnGarde();
   }
   ```

2. **Système de niveaux** : augmenter les stats après chaque victoire

3. **Inventaire d'équipement** : armes qui augmentent la force, armures qui réduisent les dégâts

4. **Tours de combat plus réalistes** : alternance qui n'attend pas que quelqu'un meure

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
