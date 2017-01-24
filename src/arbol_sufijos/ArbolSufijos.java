package arbol_sufijos;

import java.util.ArrayList;

/**
 * Created by ALEX on 23/01/2017.
 */
public class ArbolSufijos {
    private String sufijo=null;
    private int inicio=-1;
    private ArrayList<ArbolSufijos> hijos = new ArrayList<ArbolSufijos>();

    // Constructores
    public ArbolSufijos(){}
    public ArbolSufijos(String s){ sufijo=s; }
    public ArbolSufijos(String s, int p){ sufijo=s; inicio=p; }
    public ArbolSufijos(String s, ArrayList<ArbolSufijos> as){ sufijo=s; hijos=as; }
    public ArbolSufijos(String s, int p, ArrayList<ArbolSufijos> as){ sufijo=s; inicio=p; hijos=as; }

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

    //Devuelve el indice donde se inicio la cadena
    public int getInicio(){ return inicio; }

    //Añade el sufijo s
    public boolean añadirSufijo(String s, int indice){
        if(s.equals("$")){
            for(int i=0 ; i<hijos.size() ; i++){
                //Se ha acabado s y ya estaba el nodo en el arbol
                if(hijos.get(i).getSufijo().equals(s)) {
                    return false;
                }
            }

            //Se añade el final de cadena
            hijos.add(new ArbolSufijos(s,indice));
            return true;
        }
        else{
            for(int i=0 ; i<hijos.size() ; i++){
                if(hijos.get(i).getSufijo().equals(s.substring(0,1))) {
                    return hijos.get(i).añadirSufijo(s.substring(1),indice);
                }
            }

            //Se añade un nuevo hijo si no ha coincidido con los ya existentes
            hijos.add(new ArbolSufijos(s.substring(0,1)));
            return hijos.get(hijos.size()-1).añadirSufijo(s.substring(1),indice);
        }
    }

    //Compacta el arbol actual
    public void compactar(int compactos, int recorridos){
        if(hijos.size()==0){
            //Usamos indices como etiqueta si tiene longitud suficiente
            if(sufijo.length()>2) {
                sufijo = (recorridos + inicio - compactos) + ":" + (recorridos + inicio - 1);
            }
        }
        else if(hijos.size()==1){
            if(hijos.get(0).getSufijo().equals("$")){
                //Usamos indices como etiqueta si tiene longitud suficiente
                if(sufijo.length()>2) {
                    sufijo = (recorridos + hijos.get(0).getInicio() - compactos) + ":" + (recorridos + hijos.get(0).getInicio());
                }
            }
            else {
                //Compactamos el nodo actual con su hijo
                sufijo = sufijo + hijos.get(0).getSufijo();
                inicio = hijos.get(0).getInicio();
                hijos = hijos.get(0).getHijos();

                //Compactamos con los nuevos valores
                compactar(compactos + 1, recorridos + 1);
            }
        }
        else{
            for(int i=0; i<hijos.size() ; i++){
                //Se comprimen los hijos
                hijos.get(i).compactar(0, recorridos+1);
            }
        }
    }

    //Devuelve el arbol en formato string
    public String toString(){
        String salida = sufijo+"["+inicio+"]";
        for( int i=0 ; i<hijos.size() ; i++){
            salida = salida + "(" + hijos.get(i).toString() + ")";
        }
        return salida;
    }
}
