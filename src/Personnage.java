public abstract class Personnage {
    protected String nom;
    protected int HP;
    protected int HPmax;
    protected int mana;
    protected int manaMax;
    protected int force;

    Personnage(String nom, int HP, int HPmax, int mana, int manaMax, int force){
        this.nom = nom;
        this.HP = HP;
        this.HPmax = HPmax;
        this.mana = mana;
        this.manaMax = manaMax;
        this.force = force;
    }


    public abstract void attaquer(Personnage adversaire);

    public abstract void attaqueSpe (Personnage adversaire) throws ManaDispoException;

    public void subirDegats(int degats) {
        if (this instanceof Chevalier chevalier){
            if (chevalier.getBouclier()){
                degats = degats/2;
                chevalier.setBouclier(false);
                System.out.println(nom + " bloque avec son bouclier ");
            }
        }
        HP -= degats;
    }

    public void recupererMana(int montant){
        mana = mana+montant;
    }

    public boolean estEnVie(){
        return HP > 0;
    }

    public void afficherEtat(){
        System.out.printf("%s - HP: %d/%d | Mana: %d/%d%n",
                nom, HP, HPmax, mana, manaMax);
    }


    public String getNom(){
        return nom;
    }

    public int getHP(){
        return HP;
    }

    public  int getMana(){
        return mana;
    }

    public int getForce(){
        return force;
    }

    @Override
    public String toString() {
        return String.format("%s [HP: %d/%d, Mana: %d/%d, Force: %d]",
                nom, HP, HPmax, mana, manaMax, force);
    }


}
