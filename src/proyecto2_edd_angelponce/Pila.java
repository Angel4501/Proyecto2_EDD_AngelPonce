package proyecto2_edd_angelponce;

public class Pila extends Lista {

    //Método METE
    public void mete(Object x, Pila p) {
        inserta(x,primero(p),p); //Llamando al método INSERTA(x,p,L), de la clase Padre: Lista.
    }
    
    public Object tope(Pila p){
        return recupera(primero(p),p);
    }
    
    boolean vacia(Pila p){
        return p.getLista().isEmpty();
    }
    
    //Método ANULA(P) reutilizo el código de la Clase Padre, Lista. Lista tiene el metodo ANULA(L)

}
