package arbol_sufijos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args){

        int entrada=0; //0:cadena; 1:fichero; 2:fichero FASTA
        boolean verbose=false; //false:no se muestran resultados intermedios; true: se muestran resultados intermedios
        boolean incorrectos=false; //false:los parametros de entrada son correctos; true: los parametros son incorrectos
        String cadena="";
        File fichero=null;

        ///////////////////////////////////////////////
        //   COMPROBACION DE PARAMETROS DE ENTRADA   //
        ///////////////////////////////////////////////
        if(args.length==2){
            if(args[0].equals("-c")){
                entrada=0;
                cadena=args[1];
            }
            else if(args[0].equals("-f")){
                entrada=1;
                fichero= new File(args[1]);
            }
            else if(args[0].equals("-F")){
                entrada=2;
                fichero= new File(args[1]);
            }
            else{
                incorrectos=true;
            }
        }
        else if(args.length==3){
            if(args[0].equals("-c")){
                entrada=0;
                cadena=args[1];
                if(args[2].equals("-v")){
                    verbose=true;
                }
                else{
                    incorrectos=true;
                }
            }
            else if(args[0].equals("-f")){
                entrada=1;
                fichero= new File(args[1]);
                if(args[2].equals("-v")){
                    verbose=true;
                }
                else{
                    incorrectos=true;
                }
            }
            else if(args[0].equals("-F")){
                entrada=2;
                fichero= new File(args[1]);
                if(args[2].equals("-v")){
                    verbose=true;
                }
                else{
                    incorrectos=true;
                }
            }
            else {
                incorrectos=true;
            }
        }
        if(incorrectos){
            System.out.println("Parametros de entrada incorrectos.");
            System.out.println("Uso para cadenas: java Repeticiones -c cadena [-v]");
            System.out.println("Uso para ficheros: java Repeticiones -f fichero [-v]");
            System.out.println("Uso para ficheros FASTA: java Repeticiones -F fichero_fasta [-v]");
            System.exit(-1);
        }



        /////////////////////////////////////////////////////////
        //   SEGUN LA ENTRADA SE PROCEDE DE UNA FORMA U OTRA   //
        /////////////////////////////////////////////////////////
        switch(entrada){
            case 0: //Cadena en la entrada
                System.out.println("Creando árbol de sufijos...");
                RaizArbolSufijos a0 = new RaizArbolSufijos();

                System.out.println("Añadiendo cadena...");
                a0.anadirSufijos(cadena);
                if(verbose) {
                    System.out.println("ARBOL SIN COMPACTAR:\n" + a0.toString());
                }

                System.out.println("Compactando árbol de sufijos...");
                a0.compactarArbol();
                if(verbose) {
                    System.out.println("ARBOL COMPACTADO:\n" + a0.toString());
                }

                System.out.println("Buscando la repeticion mas larga...");
                String repeticion0 = a0.repeticionMasLarga();
                System.out.println("REPETICION MAS LARGA:\n\t\"" + repeticion0 + "\n\"");

                System.out.println("Buscando las repeticiones maximales...");
                System.out.println("MAXIMALES:");
                ArrayList<String> maximales0 = a0.repeticionesMaximales();
                for(int i=0; i<maximales0.size(); i++){
                    System.out.println("\t\"" + maximales0.get(i) + "\"");
                }
            break;
            case 1: //Fichero en la entrada
                Scanner lector1 = null;
                try{
                    lector1 = new Scanner(fichero);
                } catch(FileNotFoundException e){
                    System.out.println("No se ha encontrado el fichero: "+fichero.getAbsolutePath());
                    System.exit(-1);
                }

                System.out.println("Creando árbol de sufijos...");
                RaizArbolSufijos a1 = new RaizArbolSufijos();

                int i1=1;
                while(lector1.hasNextLine()){
                    System.out.println("Leyendo la palabra " + i1 + "...");
                    cadena += lector1.nextLine();
                    i1++;
                }

                System.out.println("Añadiendo cadena \"" + cadena + "\"...");
                a1.anadirSufijos(cadena);
                if(verbose) {
                    System.out.println("ARBOL SIN COMPACTAR:\n" + a1.toString());
                }

                System.out.println("Compactando árbol de sufijos...");
                a1.compactarArbol();
                if(verbose) {
                    System.out.println("ARBOL COMPACTADO:\n" + a1.toString());
                }

                System.out.println("Buscando la repeticion mas larga...");
                String repeticion1 = a1.repeticionMasLarga();
                System.out.println("REPETICION MAS LARGA:\n\t\"" + repeticion1 + "\"\n");

                System.out.println("Buscando las repeticiones maximales...");
                System.out.println("MAXIMALES:");
                ArrayList<String> maximales1 = a1.repeticionesMaximales();
                for(int i=0; i<maximales1.size(); i++){
                    System.out.println("\t\"" + maximales1.get(i) + "\"");
                }
            break;
            case 2: // Fichero FASTA en la entrada
                Scanner lector2 = null;
                try{
                    lector2 = new Scanner(fichero).useDelimiter("\n");
                } catch(FileNotFoundException e){
                    System.out.println("No se ha encontrado el fichero: "+fichero.getAbsolutePath());
                    System.exit(-1);
                }

                System.out.println("Creando árbol de sufijos...");
                RaizArbolSufijos a2 = new RaizArbolSufijos();

                int i2=1;
                lector2.next();
                while(lector2.hasNext()){
                    System.out.println("Leyendo la linea " + i2 + "...");
                    String linea = lector2.next();
                    i2++;
                    if(linea.substring(0,1).equals(">")){
                        System.out.println("Añadiendo cadena...");
                        a2.anadirSufijos(cadena);
                        if(verbose) {
                            System.out.println("ARBOL SIN COMPACTAR:\n" + a2.toString());
                        }

                        System.out.println("Compactando árbol de sufijos...");
                        a2.compactarArbol();
                        if(verbose) {
                            System.out.println("ARBOL COMPACTADO:\n" + a2.toString());
                        }

                        System.out.println("Buscando la repeticion mas larga...");
                        String repeticion2 = a2.repeticionMasLarga();
                        System.out.println("REPETICION MAS LARGA:\n\t\"" + repeticion2 + "\"\n");

                        System.out.println("Buscando las repeticiones maximales...");
                        System.out.println("MAXIMALES:");
                        ArrayList<String> maximales2 = a2.repeticionesMaximales();
                        for(int i=0; i<maximales2.size(); i++){
                            System.out.println("\t\"" + maximales2.get(i) + "\"");
                        }
                        cadena = "";
                        a2 = new RaizArbolSufijos();
                    }
                    else{
                        cadena= cadena + linea.substring(0,linea.length()-1);
                    }
                }
                System.out.println("Añadiendo cadena...");
                a2.anadirSufijos(cadena);
                if(verbose) {
                    System.out.println("ARBOL SIN COMPACTAR:\n" + a2.toString());
                }

                System.out.println("Compactando árbol de sufijos...");
                a2.compactarArbol();
                if(verbose) {
                    System.out.println("ARBOL COMPACTADO:\n" + a2.toString());
                }

                System.out.println("Buscando la repeticion mas larga...");
                String repeticion2 = a2.repeticionMasLarga();
                System.out.println("REPETICION MAS LARGA:\n\t\"" + repeticion2 + "\"\n");

                System.out.println("Buscando las repeticiones maximales...");
                System.out.println("MAXIMALES:");
                ArrayList<String> maximales2 = a2.repeticionesMaximales();
                for(int i=0; i<maximales2.size(); i++){
                    System.out.println("\t\"" + maximales2.get(i)+"\"");
                }
            break;
        }
    }
}
