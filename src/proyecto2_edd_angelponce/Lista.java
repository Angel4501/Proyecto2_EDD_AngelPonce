package proyecto2_edd_angelponce;

import java.util.ArrayList;

public class Lista {
    protected ArrayList lista = new ArrayList();
    
    public void printLista(Lista L){
        for (int i = 0; i < L.getLista().size(); i++) {
            System.out.println(L.getLista().get(i));
        }
        //System.out.println();
    }

    public ArrayList getLista() {
        return lista;
    }

    public void setLista(ArrayList lista) {
        this.lista = lista;
    }
    
    public void inserta(Object x, int p, Lista L){
        L.getLista().add(p,x);
    }
    
    int primero(Lista L){
        if(L.getLista().isEmpty()){
            return 0;
        }
        return L.getLista().indexOf(L.getLista().get(0));
    }
    
    public Object recupera(int p, Lista L){
        return L.getLista().get(p);
    }
    
    public void suprime(int p, Lista L){
        if(L.getLista().isEmpty()){
            System.out.println("No hay nada que eliminar...");
        }
        else{
            L.getLista().remove(p);    
        }
        
    }
    
    public void anula(Lista L){
        L.getLista().clear();
    }
    
}