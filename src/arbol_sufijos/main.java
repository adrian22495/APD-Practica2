package arbol_sufijos;

import java.util.ArrayList;

/**
 * Created by adrian on 22/01/2017.
 */
public class main {
    public static void main(String[] args){
        RaizArbolSufijos a = new RaizArbolSufijos();
        a.anadirSufijos("abananaybananaechoetecnicolortecnianat");
        System.out.println("ARBOL SIN COMPACTAR:\n"+a.toString()+"\n\n");

        a.compactarArbol();
        System.out.println("ARBOL COMPACTADO:\n"+a.toString()+"\n\n");

        String repeticion = a.repeticionMasLarga();
        System.out.println("REPETICION MAS LARGA:\n"+repeticion+"\n\n");

        System.out.println("MAXIMALES:");
        ArrayList<String> maximales = a.repeticionesMaximales();
        for(int i=0; i<maximales.size(); i++){
            System.out.println(maximales.get(i));
        }
    }
}
