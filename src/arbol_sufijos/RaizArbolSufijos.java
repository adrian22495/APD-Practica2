package arbol_sufijos;

import java.util.ArrayList;

/**
 * Created by ALEX on 23/01/2017.
 */
public class RaizArbolSufijos {
    private static String original;
    private ArrayList<ArbolSufijos> hijos = new ArrayList<ArbolSufijos>();

    //Constructor
    public RaizArbolSufijos(){}

    //Añade el string cadena al arbol
    public void añadirSufijos(String cadena){
        original = cadena;
        String s = cadena + "$";
        for(int i=0 ; i<s.length()-1 ; i++){
            añadirSufijo(s.substring(i),i);
        }
    }

    //Inmersion de añadirSufijos
    private void añadirSufijo(String s, int indice){
        if(s.equals("$")){
            for(int i=0 ; i<hijos.size() ; i++){
                if(hijos.get(i).getSufijo().equals(s)) {
                    return;
                }
            }
            hijos.add(new ArbolSufijos(s,indice));
        }
        else{
            for(int i=0 ; i<hijos.size() ; i++){
                if(hijos.get(i).getSufijo().equals(s.substring(0,1))) {
                    hijos.get(i).añadirSufijo(s.substring(1),indice);
                    return;
                }
            }

            hijos.add(new ArbolSufijos(s.substring(0,1)));
            hijos.get(hijos.size()-1).añadirSufijo(s.substring(1),indice);
        }
    }

    //Compacta los nodos con solo un hijo del arbol y reduce las etiquetas
    public void compactarArbol(){
        for(int i=0 ; i<hijos.size() ; i++){
            hijos.get(i).compactar(0, 0);
        }
    }

    //Devuelve la subcadena mas larga repetida
    public String repeticionMasLarga(){
        String mejor="";
        for(int i=0; i<hijos.size(); i++){
            String s = hijos.get(i).repeticionMasLarga("");
            if(s!=null && s.length()>mejor.length()){
                mejor=s;
            }
        }
        return mejor;
    }

    //Devuelve el arbol en formato string
    public String toString(){
        String salida = "Cadena original: " + original + "\n";
        for(int i=0 ; i<hijos.size() ; i++){
            salida = salida + "\t("+hijos.get(i).toString() + ")\n";
        }
        return salida;
    }
}
