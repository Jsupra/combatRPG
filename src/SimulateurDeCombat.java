public class SimulateurDeCombat {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║    BIENVENUE DANS LE SIMULATEUR DE COMBAT RPG  ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        // Créer les personnages
        Guerrier guerrier = new Guerrier("Aragorn", 100, 100, 50, 50, 15);
        Magicien magicien = new Magicien("Gandalf", 70, 70, 80, 80, 10);
        Archer archer = new Archer("Legolas", 80, 80, 40, 40, 12);
        Chevalier chevalier = new Chevalier("Boromir", 110, 110, 60, 60, 14, false, 0);

        // Afficher les personnages créés
        System.out.println("════ PERSONNAGES CRÉÉS ════");
        System.out.println(guerrier);
        System.out.println(magicien);
        System.out.println(archer);
        System.out.println(chevalier);
        System.out.println();

        // Combat 1 : Guerrier vs Magicien
        System.out.println("\n🔥 ════════════════════════════════════════════ 🔥");
        System.out.println("           COMBAT 1 : Guerrier vs Magicien");
        System.out.println("🔥 ════════════════════════════════════════════ 🔥\n");
        simulerCombat(guerrier, magicien);

        // Réinitialiser les personnages
        guerrier = new Guerrier("Aragorn", 100, 100, 50, 50, 15);
        magicien = new Magicien("Gandalf", 70, 70, 80, 80, 10);
        archer = new Archer("Legolas", 80, 80, 40, 40, 12);
        chevalier = new Chevalier("Boromir", 110, 110, 60, 60, 14, false, 0);

        // Combat 2 : Archer vs Chevalier
        System.out.println("\n🏹 ════════════════════════════════════════════ 🛡️");
        System.out.println("          COMBAT 2 : Archer vs Chevalier");
        System.out.println("🏹 ════════════════════════════════════════════ 🛡️\n");
        simulerCombat(archer, chevalier);

        // Réinitialiser
        guerrier = new Guerrier("Aragorn", 100, 100, 50, 50, 15);
        magicien = new Magicien("Gandalf", 70, 70, 80, 80, 10);

        // Combat 3 : Guerrier vs Magicien (avec attaques spéciales)
        System.out.println("\n⚡ ════════════════════════════════════════════ ⚡");
        System.out.println("      COMBAT 3 : Guerrier vs Magicien (SPÉCIAL)");
        System.out.println("⚡ ════════════════════════════════════════════ ⚡\n");
        simulerCombatAvecSpeciales(guerrier, magicien);
    }

    /**
     * Simule un combat simple entre deux personnages (attaques normales uniquement)
     */
    public static void simulerCombat(Personnage p1, Personnage p2) {
        int tour = 1;

        while (p1.estEnVie() && p2.estEnVie()) {
            System.out.printf("\n━━━ TOUR %d ━━━\n", tour);
            System.out.println(p1.getNom() + " attaque !");
            p1.attaquer(p2);
            p1.afficherEtat();
            p2.afficherEtat();

            if (!p2.estEnVie()) break;

            System.out.println();
            System.out.println(p2.getNom() + " contre-attaque !");
            p2.attaquer(p1);
            p1.afficherEtat();
            p2.afficherEtat();

            tour++;
            if (tour > 50) break;  // Limite pour éviter les boucles infinies
        }

        System.out.printf("\n🏆 %s a gagné !\n", p1.estEnVie() ? p1.getNom() : p2.getNom());
    }

    /**
     * Simule un combat avec attaques spéciales
     */
    public static void simulerCombatAvecSpeciales(Personnage p1, Personnage p2) {
        int tour = 1;

        while (p1.estEnVie() && p2.estEnVie()) {
            System.out.printf("\n━━━ TOUR %d ━━━\n", tour);

            // P1 attaque
            System.out.println(p1.getNom() + " choisit une attaque !");
            if (tour % 3 == 0 && p1.getMana() >= 30) {  // Chaque 3 tours, attaque spéciale
                try {
                    System.out.println("[ATTAQUE SPÉCIALE]");
                    p1.attaqueSpe(p2);
                } catch (ManaDispoException e) {
                    System.out.println("❌ " + e.getMessage());
                    System.out.println("[Attaque normale à la place]");
                    p1.attaquer(p2);
                }
            } else {
                p1.attaquer(p2);
            }
            p1.afficherEtat();
            p2.afficherEtat();

            if (!p2.estEnVie()) break;

            System.out.println();

            // P2 attaque
            System.out.println(p2.getNom() + " choisit une attaque !");
            if (tour % 3 == 0 && p2.getMana() >= 30) {
                try {
                    System.out.println("[ATTAQUE SPÉCIALE]");
                    p2.attaqueSpe(p1);
                } catch (ManaDispoException e) {
                    System.out.println("❌ " + e.getMessage());
                    System.out.println("[Attaque normale à la place]");
                    p2.attaquer(p1);
                }
            } else {
                p2.attaquer(p1);
            }
            p1.afficherEtat();
            p2.afficherEtat();

            tour++;
            if (tour > 50) break;
        }

        System.out.printf("\n🏆 %s a gagné !\n", p1.estEnVie() ? p1.getNom() : p2.getNom());
    }
}