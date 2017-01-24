package arbol_sufijos;

/**
 * Created by adrian on 22/01/2017.
 */
public class main {
    public static void main(String[] args){
        RaizArbolSufijos a = new RaizArbolSufijos();
        a.añadirSufijos("ATCGATCGA");
        System.out.println("ARBOL SIN COMPACTAR:\n"+a.toString()+"\n\n");

        a.compactarArbol();
        System.out.println("ARBOL COMPACTADO:\n"+a.toString()+"\n\n");

        String repeticion = a.repeticionMasLarga();
        System.out.println("REPETICION MAS LARGA:\n"+repeticion+"\n\n");
    }
}
