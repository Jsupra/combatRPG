public class Magicien extends Personnage {
    Magicien(String nom, int HP, int HPmax, int mana, int manaMax, int force) {
        super(nom, HP, HPmax, mana, manaMax, force);
    }

    @Override
    public void attaquer(Personnage adversaire) {
        System.out.println(this.nom + " lance un sort de base ");
        int degats = force / 2;
        adversaire.subirDegats(degats);
        System.out.println(this.nom +" fait "+ degats + " degats ! a "+ adversaire.nom);
    }

    @Override
    public void attaqueSpe(Personnage adversaire) throws ManaDispoException {
        int manaSpe = 40;
        if (mana < manaSpe){
            throw new ManaDispoException(this.nom, manaSpe, this.mana);
        } else {
            mana -= 40;
            int degats = force * 3;
            System.out.println(this.nom + " lance une boule de feu \uD83D\uDD25 ");
            adversaire.subirDegats(degats);
            System.out.println(nom +" fait "+ degats + " degats ! a" + adversaire.nom);
        }

    }
}
