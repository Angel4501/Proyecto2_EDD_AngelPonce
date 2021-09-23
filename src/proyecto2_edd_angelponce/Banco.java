package proyecto2_edd_angelponce;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    private int modo, cantCajeros;
    private ArrayList<Cajero> cajeros = new ArrayList();
    private Cola personas = new Cola();
    private Time t = new Time(); 
    
    public Banco(int modo, int cantCajeros) {
        this.modo = modo;
        this.cantCajeros = cantCajeros;
    }

    public void funcionamiento() throws InterruptedException {
        if (modo == 1) {
            modo1(); 
        } 
        else if (modo == 2) {
            modo2();
        }
    }

    //MODO 1 DEL BANCO
    private void modo1() throws InterruptedException {
        //1. Llenar la cola de personas
        llenadoDePersonas(personas);
        System.out.println("Total de personas en la cola: "+personas.getLista().size());
        personas.printLista(personas);
        //2. Hacer que la persona pase a uno de los n cajeros, de forma aleatoria
        crearCajeros();
        int random = (int) (Math.random() * (cantCajeros));
        cajeros.get(random).setP((Persona) personas.frente(personas));
        cajeros.get(random).getP().setNumeroDeCajero(random);
        cajeros.get(random).getP().start();
        cajeros.get(random).getTransacciones().mete(transaccionRandom(), cajeros.get(random).getTransacciones());
        personas.quita(personas);
        boolean b = false;
        //t.start();
        t.start();
        while (t.isAlive()) {
            //personas.pone(new Persona(randomName(),randomLastName(),(int)(Math.random()*(80-18+1)+18)), personas);
            for (int i = 0; i < cajeros.size(); i++) {
              
                if(cajeros.get(i).getP().isAlive()){//si el cajero está ocupado, no hago nada

                }
                else{ //si no está ocupado, hago pasar al que está de primero en la cola
                    if(personas.vacia(personas)==false){
                        cajeros.get(i).setP((Persona) personas.frente(personas));
                        //cajeros.get(i).getP().setTiempo((int) (Math.random() * (3) + 1),i);
                        cajeros.get(i).getP().setNumeroDeCajero(i);
                        cajeros.get(i).getP().start();
                        cajeros.get(i).getTransacciones().mete(transaccionRandom(), cajeros.get(i).getTransacciones());
                        personas.quita(personas);
                    }
                    
                }
            }
        }
        
        //para verificar si ya se puede hacer el print de las Pilas de transacciones
        while(b==false){
            for(int i=0; i<cajeros.size(); i++){
                if(cajeros.get(i).getP().isAlive()){
                    b = false;
                    break;
                }
                else{
                    b = true;
                }
                
            }
        }
        printPilas();
        System.out.println("Size de la cola de personas: "+personas.getLista().size());
        personas.printLista(personas);
    }
    
    private void llenadoDePersonas(Cola C) {
        if(modo==1){
            float acum = 0;
            while((acum/cantCajeros)<120){
                Persona person = new Persona(randomName(), randomLastName(), (int) (Math.random() * (80 - 18 + 1) + 18));
                person.setTiempo((int) (Math.random() * (3) + 1));
                C.pone(person, C);
                acum+=person.getTiempo();
            }
            
        }
        else if(modo == 2){
            int random;
            random = (int) (Math.random() * (10 - 5 + 1) + 5);
            for (int i = 0; i < random; i++) {
                Persona person = new Persona(randomName(), randomLastName(), (int) (Math.random() * (80 - 18 + 1) + 18));
                person.setTiempo((int) (Math.random() * (3) + 1));
                C.pone(person, C);
            }
        }
    }

    //Generar un nombre aleatorio
    private String randomName() {
        String[] nombres = {"Angel", "Pedro", "Lucas", "Jose", "Carlos", "Francisco", "Edgar", "Claudio", "Daniel",
             "Marcos", "Antonia", "Maria", "Lucia", "Ana", "Rebeca", "Alejandra", "Valeria", "Cristina",
             "Yessica", "Josefina", "Javier", "Leonel", "Pablo", "Juan", "Alexander", "Gloria", "Angelica"};

        int numero = (int) (Math.random() * (27));
        return nombres[numero];
    }

    //Generar un apellido aleatorio
    private String randomLastName() {
        String[] apellidos = {"Martinez", "Gomez", "Perez", "Gonzales", "Ponce", "Medina", "Guzman", "Castellón", "Pineda",
             "Flores", "Jimenez", "Sagastume", "Hernandez", "Baide", "Mendoza", "Diaz", "Lopez", "Sanchez",
             "Ruiz", "Maradiaga", "Ortega", "Espinoza", "Romero", "Morales", "Nuñez", "Andino", "Henriquez"};

        int numero = (int) (Math.random() * (27));
        return apellidos[numero];
    }

    //Crear la cantidad de cajeros solcitada por el usuario
    private void crearCajeros() {
        for (int i = 0; i < cantCajeros; i++) {
            cajeros.add(new Cajero());
        }
    }

    //Crear una transacción random, de entre 3 opciones
    private String transaccionRandom() {
        int r = (int) (Math.random() * (3)); //entre 0 y 2 [incluyéndolos]
        if (r == 0) {
            return "Depósito";
        } else if (r == 1) {
            return "Retiro";
        } else if (r == 2) {
            return "Pago";
        }
        return "ERROR";
    }

    //Imprimir las pilas de transacciones de cada cajero
    private void printPilas() throws InterruptedException {
        System.out.println("\n|Pilas de Transacciones|");
        /*for (int i = 0; i < cajeros.size(); i++) {
            System.out.println("Pila #" + (i + 1) + " [Cajero "+(i+1)+"]" + ": ");
            if (cajeros.get(i).getTransacciones().vacia(cajeros.get(i).getTransacciones()) == false) {
                cajeros.get(i).getTransacciones().printLista(cajeros.get(i).getTransacciones());
            }
        }*/
        for(int i=0; i<cajeros.size(); i++){
            System.out.println("\nPILA #" + (i + 1) + " [Cajero "+(i+1)+", hubieron "
                    +(cajeros.get(i).getTransacciones().getLista().size()) + " transacciones]: ");
            if (cajeros.get(i).getTransacciones().vacia(cajeros.get(i).getTransacciones()) == false){
                for(int j=0; j<cajeros.get(i).getTransacciones().getLista().size(); j++){
                    System.out.println(cajeros.get(i).getTransacciones().getLista().get(j));
                }
            }
        }
    }
    
    //MODO 2 DEL BANCO
    private void modo2() throws InterruptedException{
        t.start();
        createAllColas(); //crea primero los cajeros, luego llena las colas de personas de cada cajero
        printPeople();
        boolean b = false;
        
        dynamicPutPersona();
        while(t.isAlive()){ //while(areColasEmpty()==false
            for(int i=0; i<cajeros.size(); i++){
                if(cajeros.get(i).getP().isAlive()){
                    
                }
                else{
                    if(cajeros.get(i).getPersonas().vacia(cajeros.get(i).getPersonas())==false){
                        cajeros.get(i).setP((Persona) (cajeros.get(i).getPersonas().frente(cajeros.get(i).getPersonas())) );
                        //cajeros.get(i).getP().setTiempo((int)(Math.random()*(3)+1), i);
                        cajeros.get(i).getP().setNumeroDeCajero(i);
                        cajeros.get(i).getP().start();
                        cajeros.get(i).getTransacciones().mete(transaccionRandom(), cajeros.get(i).getTransacciones());
                        cajeros.get(i).getPersonas().quita(cajeros.get(i).getPersonas());
                    }
                }
            }
        }
        while(b==false){
            for(int i=0; i<cajeros.size(); i++){
                if(cajeros.get(i).getP().isAlive()){
                    b = false;
                    break;
                }
                else{
                    b = true;
                }
                
            }
        }
        printPilas();
        System.out.println("Cola de personas, actualizado: ");
        for(int i=0; i<cajeros.size(); i++){
            //cajeros.get(i).getPersonas().printLista(cajeros.get(i).getPersonas());
            System.out.println( cajeros.get(i).getPersonas().getLista().size());
        }
    }
    
    //LLenar cada cola de Personas de cada cajero, EXCLUSIVO del modo 2
    private void createAllColas(){
        crearCajeros();
        for(int i=0; i<cajeros.size(); i++){
            llenadoDePersonas(cajeros.get(i).getPersonas());
        }
    }
    
    //Imprime las colas de personas de todos los cajeros
    private void printPeople(){
        for(int i=0;i <cajeros.size(); i++){
            System.out.println("Cola #"+(i+1));
            System.out.println("TOTAL DE PERSONAS: "+cajeros.get(i).getPersonas().getLista().size());
            cajeros.get(i).getPersonas().printLista(cajeros.get(i).getPersonas());
            System.out.println();
        }
    }
    
    //pone a una persona en la cola con menos personas
    private void dynamicPutPersona(){
        while(tiempoTotal()<110){
            int menor = 999999999, pos = 0;
            for(int i=0; i<cajeros.size(); i++){
                if(cajeros.get(i).getPersonas().getLista().size()<menor){
                    menor = cajeros.get(i).getPersonas().getLista().size();
                    pos = i;
                }
            }
            Persona p = new Persona(randomName(), randomLastName(), (int) (Math.random() * (80 - 18 + 1) + 18));
            p.setTiempo((int) (Math.random() * (3) + 1));
            cajeros.get(pos).getPersonas().pone(p, cajeros.get(pos).getPersonas());
        }
        
    }
    
    private float tiempoTotal(){
        float acum = 0;
        //ArrayList list = new ArrayList(); 
        for(int i=0; i<cajeros.size(); i++){
            for(int j=0; j<cajeros.get(i).getPersonas().getLista().size(); j++){
                Persona p = (Persona)cajeros.get(i).getPersonas().getLista().get(j);
                acum+=p.getTiempo();
            }
        }
        return acum/cantCajeros;
    }
    
    /*private void finish(){
        for(int i=0; i<cajeros.size(); i++){
            cajeros.get(i).getPersonas().anula(cajeros.get(i).getPersonas());
        }
    }*/
    //Método para el modo 2, me dice si ya están vacías todas las colas de personas o no
    /*private boolean areColasEmpty(){ //¿?
        boolean b = false;
        for(int i=0; i<cajeros.size(); i++){
            if(cajeros.get(i).getPersonas().vacia(cajeros.get(i).getPersonas())){
                b = true;
            }
            else{
                b = false;
                break;
            }
        }
        return b;
    }*/
    
    //Entrada, inicio del programa
    public static int[] menuInicio() {
        Scanner sc = new Scanner(System.in);
        System.out.println("**********************");
        System.out.println("* B A N C O NACIONAL *");
        System.out.println("**********************");
        System.out.println("Bienvenido al Banco, ingrese el modo de su elección");
        System.out.println("1. Modo 1");
        System.out.println("2. Modo 2");
        System.out.print("Opcion: ");
        int opc = sc.nextInt();
        while (opc < 1 || opc > 2) {
            System.out.println("OPCION INVÁLIDA, ingrésela nuevamente...");
            System.out.println("1. Modo 1");
            System.out.println("2. Modo 2");
            System.out.print("Opcion: ");
            opc = sc.nextInt();
        }
        System.out.print("Ingrese la cantidad de cajeros que tendrá el banco: ");
        int opc2 = sc.nextInt();
        while (opc2 <= 0) {
            System.out.print("Ingrese una cantidad de cajeros válida: ");
            opc2 = sc.nextInt();
        }
        int array[] = {opc, opc2};
        return array;
    }
}
