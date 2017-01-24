package arbol_sufijos;

/**
 * Created by adrian on 22/01/2017.
 */
public class main {
    public static void main(String[] args){
        Arbol a = new Arbol();
        a.anadirSufijo("aabcaab");

        ArbolSufijos a = new ArbolSufijos();
        a.añadirSufijos("aaabbbc");
        a.compactarArbol();
        System.out.println(a.toString());
    
    }
}
