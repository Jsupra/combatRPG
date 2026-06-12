public class ManaDispoException extends Exception{
    public ManaDispoException(){
        super("Pas assez de mana pour cette attaque");
    }

    public ManaDispoException(String message){
        super(message);
    }
    public ManaDispoException(String nomPersonnage, int manaNecessaire, int manaActuel) {
        super(String.format(
                "%s n'a pas assez de mana ! (nécessaire: %d, disponible: %d)",
                nomPersonnage, manaNecessaire, manaActuel
        ));
    }
}
