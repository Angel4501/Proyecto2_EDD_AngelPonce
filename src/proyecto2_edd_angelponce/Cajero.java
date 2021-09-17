package proyecto2_edd_angelponce;

public class Cajero{
    private Pila transacciones = new Pila(); //de strings
    private Cola personas = new Cola(); //utilizada en el modo 2, ya que cada cajero tendrá una cola de personas
    private Persona p = new Persona(); //cada cajero puede atender a UNA PERSONA a la vez, entnonces Cajero tiene una Persona
    
    public Pila getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Pila transacciones) {
        this.transacciones = transacciones;
    }

    public Cola getPersonas() {
        return personas;
    }

    public void setPersonas(Cola personas) {
        this.personas = personas;
    }

    public Persona getP() {
        return p;
    }

    public void setP(Persona p) {
        this.p = p;
    }
    
    
    
}
