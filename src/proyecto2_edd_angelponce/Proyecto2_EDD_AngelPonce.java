package proyecto2_edd_angelponce;

import java.util.ArrayList;
import java.util.Scanner;

public class Proyecto2_EDD_AngelPonce {

    public static void main(String[] args) throws InterruptedException {
        int datos[] = Banco.menuInicio();
        Banco bank = new Banco(datos[0],datos[1]);
        bank.funcionamiento();    
    }

}

