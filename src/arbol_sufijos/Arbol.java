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

    public void anadirSufijo(String T){
        for(int i=0; i<T.length();i++){
            String subT = T.substring(i);
            Nodo aux = raiz;
            int j, k;
            //Se busca el camino maximo ya incluido en el arbol
            for(j=0; j<subT.length(); j++){
                //Se compara la letra con las aristas del nodo en el que nos encontramos
                ArrayList<String> aristas = aux.getAristas();
                boolean acierto=false;
                for(k=0; k<aristas.size() && !acierto;k++){
                    //Si la letra coincide con alguna arista se movera al nodo de  esa arista
                    if(aristas.get(k).equals(subT.substring(j,j+1))){
                        aux = aux.getNodos().get(k);
                        acierto = true;
                    }
                }
                //Si no se coincide hay que a�adir los nodos correspondientes
                if(!acierto){
                    break;
                }
            }
            //Se a�ade el subsufijo que no estuviese ya incluido
            for(;j<subT.length(); j++){
                Nodo n = new Nodo();
                aux.anadirHijo(n,subT.substring(j,j+1));
                aux = n;
            }
            //A�adir hoja
            Nodo n = new Nodo();
            n.setHoja(true);
            n.setIndiceInicio(i);
            aux.anadirHoja(n,"$",i);
        }

    }
    public void compactarArbol(){
        //Compactar nodos de grado 2
        ArrayList<String> aristas = raiz.getAristas();
        ArrayList<Nodo> nodos = raiz.getNodos();
        for(int i=0; i<aristas.size(); i++){
            String s = compactarR(raiz.getNodos().get(i));
            if(s!=null){
                s = aristas.get(i) + s;
            }
        }

    }

    public String compactarR(Nodo n){

        ArrayList<String> aristas = n.getAristas();
        ArrayList<Nodo> nodos = n.getNodos();
        //Si solo tiene un hijo se compacta
        if(aristas.size()==1){
            Nodo hijo = n.getNodos().get(0);
            n.setArrayAristas(hijo.getAristas());
            n.setArrayNodos(hijo.getNodos());
            return n.getAristas().get(0);
        }

        else if(n.aristas.size()>1){
            for(int i = 0; i<aristas.size(); i++){
                String s = compactarR(n.getNodos().get(i));
                if(s!=null){
                    s = aristas.get(i) + s;
                }
            }
        }
    }
}
