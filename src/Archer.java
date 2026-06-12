import java.util.Random;

public class Archer extends Personnage{
    Archer(String nom, int HP, int HPmax, int mana, int manaMax, int force) {
        super(nom, HP, HPmax, mana, manaMax, force);
    }

    @Override
    public void attaquer(Personnage adversaire) {
        Random random = new Random();
        int range = random.nextInt(100) +1;
        if (range <= 20){
            int degats = force *2;
            System.out.println(this.nom + " lance une fleche de base ");
            System.out.println(" degats critiques ");
            adversaire.subirDegats(degats);
            System.out.println(adversaire.nom + " subit " + degats);
        } else {
            int degats = force;
            adversaire.subirDegats(force);
            System.out.println(nom +" fait "+ degats + " degats ! a" + adversaire.nom);
        }
    }

    @Override
    public void attaqueSpe(Personnage adversaire) throws ManaDispoException{
        int manaSpe = 25;
        if (mana < manaSpe){
            throw new ManaDispoException(this.nom, manaSpe, this.mana);
        } else {
            mana -= 25;
            System.out.println(this.nom + " lance 3 fleches ");
            int degats = force*3;
            System.out.println(nom +" fait "+ degats + " degats ! a" + adversaire.nom);
            adversaire.subirDegats(degats);
        }
    }
}
