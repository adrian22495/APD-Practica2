package arbol_sufijos;

import java.util.ArrayList;

/**
 * Created by ALEX on 23/01/2017.
 */
public class ArbolSufijos {
    private String sufijo=null;
    private int posicion=-1;
    private ArrayList<ArbolSufijos> hijos = new ArrayList<ArbolSufijos>();

    // Constructores
    public ArbolSufijos(){}
    public ArbolSufijos(String s){ sufijo=s; }
    public ArbolSufijos(String s, int p){ sufijo=s; posicion=p; }
    public ArbolSufijos(String s, ArrayList<ArbolSufijos> as){ sufijo=s; hijos=as; }
    public ArbolSufijos(String s, int p, ArrayList<ArbolSufijos> as){ sufijo=s; posicion=p; hijos=as; }

    //Devuelve el sufijo de la raiz del árbol
    public String getSufijo(){
        return sufijo;
    }

    //Devuelve le numero de hijos
    public int getNumHijos(){
        return hijos.size();
    }

    //Devuelve una lista con los arboles hijo
    public ArrayList<ArbolSufijos> getHijos(){
        return hijos;
    }

    //Devuelve el hijo i
    public ArbolSufijos getHijo(int i){
        return hijos.get(i);
    }

    public boolean añadirSufijos(String cadena){
        boolean todos_nuevos = true;
        String s = cadena + "$";
        for(int i=0 ; i<s.length() ; i++){
            todos_nuevos = todos_nuevos && añadirSufijo(s.substring(i),i);
        }
        return todos_nuevos;
    }

    private boolean añadirSufijo(String s, int indice){
        if(s.equals("$")){
            for(int i=0 ; i<hijos.size() ; i++){
                if(hijos.get(i).getSufijo().equals(s)) {
                    return false;
                }
            }
            hijos.add(new ArbolSufijos(s,indice));
            return true;
        }
        else{
            for(int i=0 ; i<hijos.size() ; i++){
                if(hijos.get(i).getSufijo().equals(s.substring(0,1))) {
                    return hijos.get(i).añadirSufijo(s.substring(1),indice);
                }
            }

            hijos.add(new ArbolSufijos(s.substring(0,1),-1));
            return hijos.get(hijos.size()-1).añadirSufijo(s.substring(1),indice);
        }
    }

    public String toString(){
        String salida = sufijo+"("+posicion+")";
        for( int i=0 ; i<hijos.size() ; i++){
            salida = salida + "(" + hijos.get(i).toString() + ")";
        }
        return salida;
    }
}
