
package proyecto2_edd_angelponce;

public class Persona extends Thread{
    private String nombre, apellido;
    private int edad, tiempo, numeroDeCajero;
    private String black="\033[30m"; 
    private String red="\033[31m"; 
    private String green="\033[32m"; 
    private String yellow="\033[33m"; 
    private String blue="\033[34m"; 
    private String purple="\033[35m"; 
    private String cyan="\033[36m"; 
    private String white="\033[37m"; 
    private String reset="\u001B[0m";

    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    
    public Persona(){
        
    }
    
    @Override
    public void run(){
        try {
            String m1 = red+"En Cajero #"+(numeroDeCajero+1)+" se está atendiendo a ";
            //System.out.println("En Cajero #"+(numeroDeCajero+1)+" se está atendiendo a "
                    //+getNombre()+" "+getApellido() +  "... ["+getTiempo()+" segundos]");
            String lol = reset+"... ["+getTiempo()+" segundos]";
            System.out.println(m1+purple+getNombre()+" "+getApellido() + lol);
            Thread.sleep(getTiempo()*1000);
            String m2 = "\n"+green+"Cajero #"+(numeroDeCajero+1)+" está libre!";
            //System.out.println("\nCajero #"+(numeroDeCajero+1)+" está libre!");
            System.out.println(m2);
        } catch (Exception e) {
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo, int numeroDeCajero) {
        this.tiempo = tiempo;
        this.numeroDeCajero = numeroDeCajero;
    }

    @Override
    public String toString() {
        return nombre+" "+apellido + ", edad: " + edad;
    }

}
