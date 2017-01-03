package arbol_sufijos;

import java.util.ArrayList;

/**
 * Created by adrian on 02/01/2017.
 */
public class Arbol {
    Nodo raiz;

    public Arbol(){
        raiz = new Nodo();
    }

    public void añadirSufijo(String T){
        for(int i=0; i<T.length();i++){
            String subT = T.substring(i);
            Nodo aux = raiz;
            int j, k;
            //Se busca el camino maximo ya incluido en el arbol
            for(j=0; i<subT.length(); j++){
                //Se compara la letra con las aristas del nodo en el que nos encontramos
                ArrayList<String> aristas = aux.getAristas();
                boolean acierto=false;
                for(k=0; 0<aristas.size() && !acierto;k++){
                    //Si la letra coincide con alguna arista se movera al nodo de  esa arista
                    if(aristas.get(k).equals(subT.substring(j,j+1))){
                        aux = aux.getNodos().get(k);
                        acierto = true;
                    }
                }
                //Si no se coincide hay que añadir los nodos correspondientes
                if(!acierto){
                    break;
                }
            }
            //Se añade el subsufijo que no estuviese ya incluido
            for(;j<subT.length(); j++){
                Nodo n = new Nodo();
                aux.añadirHijo(n,subT.substring(j,j+1));
                aux = n;
            }
            //Añadir hoja
            Nodo n = new Nodo();
            n.setHoja(true);
            n.setIndiceInicio(i);
            aux.añadirHoja(n,"$");
        }

    }
    public void compactarArbol(){


    }
}
