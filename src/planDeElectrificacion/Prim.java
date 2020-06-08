package planDeElectrificacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prim {
	private MatrizAdyacencia matriz;
	private int cantCiudades;
	private int cantCentrales;
	private int[] centrales;
	private boolean[] visitados;
	private List<Nodo> listaNodos = new ArrayList<Nodo>();
	private int costo;
	private String path;
	
	public Prim(String path) {
		this.path = path;
	}
	
	
	public void resolucion() {
		cargarDatos();
		algoritmo();
		escribirResultados();
	}
	
	
	
	public void cargarDatos() {
		try {
			Scanner sc = new Scanner(new File(path+".in"));
			cantCiudades = sc.nextInt();
			cantCentrales = sc.nextInt();
			matriz = new MatrizAdyacencia(cantCiudades);
			centrales = new int[cantCentrales];
			for(int i=0; i<cantCentrales; i++)
				centrales[i] = sc.nextInt();
			for(int i=0; i<cantCiudades; i++) {
				for(int j=0; j<cantCiudades; j++)
					matriz.setCosto(i, j, sc.nextInt());
			}
			inicializarVectorVisitados();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void algoritmo() {
		int minActual=-1, minAnterior=-1,filaMinima=0, columnaMinima=0;
		while(!todosVisitados()) {
			for(int fila=0; fila<cantCiudades; fila++) {
				if(!visitados[fila]) {
					minActual=-1;
					minAnterior=-1;
					for(int columna=0; columna<cantCiudades; columna++) {
						if(fila != columna && matriz.getCosto(fila, columna) != -1) {
							if(minActual == -1)
								minActual = matriz.getCosto(fila, columna);
							else 
								minActual = Math.min(minActual, matriz.getCosto(fila, columna));
							
							if(minActual != minAnterior) {
								minAnterior = minActual;
								filaMinima = fila+1;
								columnaMinima = columna+1;
							}
						}
					}
					if(minActual != -1) {
						listaNodos.add(new Nodo(filaMinima, columnaMinima));
						costo+=minActual;
					}
				}
				visitados[fila] = true;
			}
		}
		
	}
	
	public void escribirResultados() {
		try {
			PrintWriter pw = new PrintWriter(new File(path+".out"));
			pw.println(costo);
			for(Nodo nd:listaNodos)
			pw.println(nd.getNodo1()+" "+nd.getNodo2());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private boolean todosVisitados() {
		for(boolean b:visitados) {
			if(!b)
				return false;
		}
		return true;
	}
	
	private void inicializarVectorVisitados() {
		visitados = new boolean[cantCiudades];
		for(int i=0; i<cantCiudades; i++)
				visitados[i] = false;
		for(int c:centrales)
			visitados[c-1] = true;
	}
	
	
}
