package proyecto2_edd_angelponce;

public class Cola extends Lista{
    //ANULA(C)
    //Uso el método anula de la clase padre: Lista
    
    //FRENTE(C)
    public Object frente(Cola c){
        return recupera(primero(c),c);
    }
    
    public void pone(Object x, Cola c){
        inserta(x,c.getLista().size(),c);
    }
    
    public void quita(Cola c){
        suprime(primero(c),c);
    }
    
    //VACIA(C): Devuelve true si y solo si C es una Cola vacía
    boolean vacia(Cola c){
        return c.getLista().isEmpty();
    }
}
