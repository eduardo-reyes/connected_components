package classes.src;

import java.util.*;
import java.io.*;

/*
 * @author: Eduardo Alfonso Reyes López.
 */

/* 
 * Clase Arista: representa una arista de
 * una gráfica.
 */
class Arista {
    
    int origen, destino;

    /*Constructor Arista */
    Arista(int origen, int destino) {
        this.origen = origen;
        this.destino = destino;
    }

    /*
     * Método que imprime en consola la representación de una arista.
     */
    public void imprimirArista() {
        System.out.println("Arista: (" + origen + "," + destino + ")");
    }
}

/*
 * Clase Gráfica.
 */
class Grafica {

    private static List<Integer> vertices = new ArrayList<>();
    private static List<Arista> aristas = new ArrayList<>();
    private static int numVertices;
    private static List<List<Vertice>> adyacencias = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> componentes = new ArrayList<>();
    
    /*
     * Clase Vértice:
     * Vértice de una gráfica.
     */
    static class Vertice {
        int valor;

        /* Constructor Vertice */
        Vertice(int valor) {
            this.valor = valor; // El valor de un vértice es su etiqueta.
        }

        public int getValor() {
            return valor;
        }
    };

    /* Constructor Grafica */
    @SuppressWarnings("unchecked")
    public Grafica(List<Integer> vertices, List<Arista> aristas) {
        this.vertices = vertices;
        this.aristas = aristas;
        this.numVertices = vertices.size();
        for(int i = 0; i <= numVertices; i++) {
            adyacencias.add(i, new ArrayList<>());
        } 
        for(Arista a : aristas) {
            adyacencias.get(a.origen).add(new Vertice(a.destino));
            adyacencias.get(a.destino).add(new Vertice(a.origen));
        }
    }

    /*
     * Método que imprime en consola la representación de una gráfica.
     * @param Grafica graf una gráfica.
     */
    public static void imprimirGrafica(Grafica graf) {

        System.out.println("===== GRÁFICA =====");
        System.out.print("Vértices: ");
        System.out.println(Arrays.deepToString(vertices.toArray()));

        System.out.println("Aristas: ");
        for(Arista a : aristas) {
            a.imprimirArista();
        }
        System.out.println();
    }

    /*
     * Método que hace DFS sobre el vértice ingresado.
     * Utiliza un arreglo de booleanos para llevar el
     * registro de los vértices ya visitados. Va guardando
     * el recorrido DFS sobre los vértices en una lista.
     * 
     * @param int vertice es el vértice sobre el cual se hará DFS.
     * @param boolean[] visitados es el registro de vértices visitados.
     * @param ArrayList<Integer> listaDFS es el recorrido DFS.
     */
    private static void DFS(int vertice, boolean[] visitados, ArrayList<Integer> listaDFS) {
        visitados[vertice] = true;
        listaDFS.add(vertice);
        System.out.print(vertice + " ");

        for(Vertice v : adyacencias.get(vertice)) {
            int n = v.getValor();
            if(!visitados[n])
                DFS(n, visitados, listaDFS);
        }
    }

    /*
     * Determina las componentes conexas de una gráfica
     * usando DFS. Guarda la lista de vértices que
     * pertenecen a cada componente en la lista componentes.
     */
    public static void componentes() {
        boolean[] visitados = new boolean[numVertices+1];
 
        for(int i = 1; i <= numVertices; i++) {
            ArrayList<Integer> listaDFS = new ArrayList<>();
            if(!visitados[i]) {
                System.out.println("Recorrido DFS componente");
                DFS(i, visitados, listaDFS);
                componentes.add(listaDFS);
                System.out.println();
            }
        }
    }

    /*
     * Muestra los resultados de encontrar las componentes
     * conexas con DFS. Imprime en consola el número de
     * componentes halladas, así como los vértices y aristas
     * que pertenecen a cada una respectivamente.
     */
    public static void numComponentes() {
        System.out.println("\nNúmero de componentes conexas: " + componentes.size());
        int contador = 1;
        for(ArrayList<Integer> componente : componentes) {
            System.out.println("\nComponente: " + contador++);
            System.out.println("Vértices: " + Arrays.deepToString(componente.toArray()));
            for(Integer i : componente) {
                List<Vertice> aristas = adyacencias.get(i);
                for(Vertice v : aristas) {
                    System.out.println("Arista: (" + i + "," + v.getValor() + ")");
                }
            }
        }
    }

}

class ComponentesConexas {
    
    private static String path = "src/Grafica.txt";

    /*
     * Método que lee el archivo de entrada y lo transforma en una
     * lista de enteros que contendrá a los vértices y a las aristas.
     * @return List<List<Integer>> la lista con los vértices y aristas.
     */
    private static List<List<Integer>> readFile(String file) {
        String ruta = null;
        if(file == "" || file == null) {
            ruta = path;
            System.out.println("Ejecutando demo.");
        }
        else {
            ruta = "src/" + file;
        }
        BufferedReader lector = null;
        String[] datos = null;
        List<List<Integer>> datosLimpios = new ArrayList<>();

        try {
            lector = new BufferedReader(new FileReader(ruta));
            String renglon = "";
            while((renglon = lector.readLine()) != null) {
                List<Integer> listaRenglon = new ArrayList<>();
                datos = renglon.split(",");
                for(String valor : datos) {
                    listaRenglon.add(Integer.parseInt(valor));
                }
                datosLimpios.add(listaRenglon);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingrese un archivo válido. Leer README.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (lector != null) {
            try {
                lector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return datosLimpios;
    }

    /*
     * Método que recibe el archivo de entrada del programa
     * por línea de comando, el cual contendrá la 
     * información sobre los vértices y las aristas
     * de la gráfica.
     * @return String archivo con la gráfica.
     */
    private static String archivoEntrada() {
        String archivo = null;
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el nombre del archivo: ");
            archivo = sc.nextLine();
            if (sc != null) {
                try {
                    sc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(archivo.isEmpty()) {
                System.out.println("No se ingresó ningún archivo.");
                archivo = "";
            }
        }catch(Exception e) {
            e.getMessage();
        }
        System.out.println();
        return archivo;
    }

    public static void main(String[] args) {
        String archivo = archivoEntrada();
        List<List<Integer>> listaGraf = readFile(archivo); 
        List<Integer> vertices = listaGraf.get(0);
        List<Arista> aristas = new ArrayList<>();
        for(int i = 1; i < listaGraf.size(); i++) {
            List<Integer> arista = listaGraf.get(i);
            Arista aristaNueva = new Arista(arista.get(0),arista.get(1));
            aristas.add(aristaNueva); 
        }
        Grafica graf = new Grafica(vertices, aristas);
        Grafica.imprimirGrafica(graf);
        graf.componentes();
        graf.numComponentes();
    }
}