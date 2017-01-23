package arbol_sufijos;

import java.util.ArrayList;

/**
 * Created by adrian on 02/01/2017.
 */
public class Nodo {
    ArrayList<String> aristas;
    ArrayList<Nodo> nodos;
    boolean hoja;
    int indiceInicio, indiceFin;

    public Nodo(){
        aristas = new ArrayList<String>();
        nodos = new ArrayList<Nodo>();
    }

    public void anadirHijo(Nodo n, String arista){
        aristas.add(arista);
        nodos.add(n);
    }
    public void anadirHoja(Nodo n, String arista, int inicio){
        nodos.add(n);
        hoja = true;
        indiceInicio = inicio;
    }
    public ArrayList<String> getAristas(){
        return aristas;
    }
    public ArrayList<Nodo> getNodos(){
        return nodos;
    }
    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public void setIndiceFin(int indiceFin) {
        this.indiceFin = indiceFin;
    }

    public void setIndiceInicio(int indiceInicio) {
        this.indiceInicio = indiceInicio;
    }

    public int getIndiceFin() {
        return indiceFin;
    }

    public int getIndiceInicio() {
        return indiceInicio;
    }
}
