import java.util.Random;

public class Guerrier extends Personnage{

    public Guerrier(String nom, int HP, int HPmax, int mana, int manaMax, int force) {
        super(nom, HP, HPmax, mana, manaMax, force);
    }

    @Override
    public void attaquer(Personnage adversaire) {
        Random random = new Random();
        int range = random.nextInt(10) + 1; //Entier entre 1 et 10 (inclus)
        int degats = force + range;
        System.out.println(nom + "frappe avec son epee ! degats : "+ degats);
        adversaire.subirDegats(degats);
        System.out.println(nom + " fait " + degats + " degats ! a " + adversaire.getNom());
    }

    @Override
    public void attaqueSpe(Personnage adversaire) throws ManaDispoException {
        int manaSpe = 30;
        if (mana < manaSpe){
            throw new ManaDispoException(this.nom, manaSpe, this.mana);
        } else {
            mana -= 30;
            System.out.println(this.nom + " est en rage ! \uD83D\uDD25 ");
            int degats = force *2;
            System.out.println(this.nom + "frappe avec Rage \uD83D\uDD25 ! degats : " + degats);
            adversaire.subirDegats(degats);
            System.out.println(this.nom +" fait "+ degats + " degats ! a" + adversaire.nom);
        }

    }
}
