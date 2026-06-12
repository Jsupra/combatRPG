public class Chevalier extends Personnage{
    Chevalier(String nom, int HP, int HPmax, int mana, int manaMax, int force) {
        super(nom, HP, HPmax, mana, manaMax, force);
    }

    @Override
    public void attaquer(Personnage adversaire) {
        int degats = force;
        System.out.println(this.nom + " fait une frappe de base avec son epee");
    }

    @Override
    public void attaqueSpe(Personnage adversaire) {

    }
}
