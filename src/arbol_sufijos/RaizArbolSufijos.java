package arbol_sufijos;

import java.util.ArrayList;

/**
 * Created by ALEX on 23/01/2017.
 */
public class RaizArbolSufijos {
    //Cadena original con la que se crea el arbol
    private String original = "";
    private ArrayList<ArbolSufijos> hijos = new ArrayList<ArbolSufijos>();

    //Constructor
    public RaizArbolSufijos(){}

    //Añade el string cadena al arbol
    public void anadirSufijos(String cadena){
        original += cadena + "$";
        for(int i=0 ; i<original.length()-1 ; i++){
            anadirSufijo(original.substring(i),i);
        }
    }

    //Inmersion de añadirSufijos
    private void anadirSufijo(String s, int indice){
        //Si se ha alcanzado el fin de la cadena
        if(s.equals("$")){
            for(int i=0 ; i<hijos.size() ; i++){
                if(hijos.get(i).getSufijo().equals(s)) {
                    return; //Si ya existe el camino, no se hace nada
                }
            }
            hijos.add(new ArbolSufijos(s,indice));
        }
        else{
            for(int i=0 ; i<hijos.size() ; i++){
                if(hijos.get(i).getSufijo().equals(s.substring(0,1))) {
                    //Si ya existe, se pasa al siguiente caracter
                    hijos.get(i).añadirSufijo(s.substring(1),indice);
                    return;
                }
            }

            //Se añade un nuevo hijo con el caracter y se pasa al siguiente caracter
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
    //Pre: el arbol debe estar compactado
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

    //Busca las repeticiones maximales de la cadena original
    //Pre: el arbol debe estar compactado
    public ArrayList<String> repeticionesMaximales(){
        ArrayList<String> maximales = new ArrayList<>();
        for(int i=0; i<hijos.size(); i++){
            ArrayList<Integer> indices = new ArrayList<>();
            maximalesR(hijos.get(i), "", indices, maximales);
        }
        return maximales;
    }

    //Metodo recursivo para buscar las repeticiones maximales
    private void maximalesR(ArbolSufijos as, String anterior, ArrayList<Integer> indices, ArrayList<String> maximales){
        //Si es un nodo hoja
        if(as.getInicio()!=-1){
            indices.add(as.getInicio());
        }
        //Si no es nodo hoja se llamara recursivamente a maximalesR para saber los indices que hay por debajo
        //En listaDeIndices se guardaran los indices que hay por debajo de as
        ArrayList<Integer> listaDeIndices = new ArrayList<>();
        for(int i = 0; i<as.getNumHijos(); i++){
            maximalesR(as.getHijo(i), anterior+as.getSufijo(), listaDeIndices,maximales);
        }
        //Se comparan los caracteres anteriores a los indices de la lista, si hay dos diferentes ya sera maximal
        boolean exito = false;
        for(int i=0; i<listaDeIndices.size(); i++){
            for(int j=0; j<listaDeIndices.size() && !exito; j++){
                //Si no se esta comparando el mismo numero, si alguno de los dos es primer caracter se considera
                //verdadero directamente, sino se comprueba que los caracteres sean distintos.
                int indice1=listaDeIndices.get(i);
                int indice2=listaDeIndices.get(j);
                if(i!=j && (indice1==0 || indice2==0 || original.charAt(indice1-1)!=original.charAt(indice2-1) )){
                    exito=true;
                    maximales.add(anterior+as.getSufijo());
                }
            }
            //Se meten en indices los indices de listaDeIndices para que la función superior reciba los indices inferiores
            indices.add(listaDeIndices.get(i));
        }


    }
    //Devuelve el arbol en formato string
    public String toString(){
        String salida = "Cadena original: \"" + original + "\"\n";
        for(int i=0 ; i<hijos.size() ; i++){
            salida = salida + "\t("+hijos.get(i).toString() + ")\n";
        }
        return salida;
    }
}
