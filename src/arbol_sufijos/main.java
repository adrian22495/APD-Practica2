package arbol_sufijos;

/**
 * Created by adrian on 22/01/2017.
 */
public class main {
    public static void main(String[] args){
        RaizArbolSufijos a = new RaizArbolSufijos();
        a.añadirSufijos("banana");
        a.compactarArbol();
        System.out.println(a.toString());
    }
}
