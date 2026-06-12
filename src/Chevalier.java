public class Chevalier extends Personnage{

    private boolean bouclier;
    private int TourBouclier;
    public Chevalier(String nom, int HP, int HPmax, int mana, int manaMax, int force, boolean bouclier, int TourBouclier) {
        super(nom, HP, HPmax, mana, manaMax, force);
        this.bouclier = bouclier;
        this.TourBouclier = TourBouclier;
    }

    @Override
    public void attaquer(Personnage adversaire) {
        int degats = force;
        System.out.println(this.nom + " fait une frappe de base avec son epee");
        adversaire.subirDegats(degats);
        System.out.println(this.nom +" fait "+ degats + " degats ! a " + adversaire.nom);
    }

    @Override
    public void attaqueSpe(Personnage adversaire) throws ManaDispoException {
        int manaSpe = 20;
        if (mana < manaSpe){
            throw new ManaDispoException(this.nom, manaSpe, this.mana);
        } else {
            mana -=20;
            this.TourBouclier = 1;
            System.out.println(this.nom + " active sont bouclier, degats reduit de 50% jusqu'au prochain tour coute " + manaSpe);
            this.bouclier = true;
        }
    }


    public boolean getBouclier(){
        return this.bouclier;
        //pour verifier si il est active et activer le bonus
    }

    public int getTourBouclier(){
        return this.TourBouclier;
    }

    public void setBouclier(boolean bouclier) {
        this.bouclier = bouclier;
    }
}
