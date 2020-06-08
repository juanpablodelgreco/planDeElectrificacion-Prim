package planDeElectrificacion;

public class MatrizAdyacencia {
	private int[][] matriz;
	private int grado;

	public MatrizAdyacencia(int grado) {
		this.grado = grado;
		matriz = new int[grado][grado];
		for (int i = 0; i < grado; i++) {
			for (int j = 0; j < grado; j++)
				matriz[i][j] = -1;
		}
	}

	public int getGrado() {
		return grado;
	}

	public void setCosto(int fila, int columna, int costo) {
		matriz[fila][columna] = costo;
	}

	public int getCosto(int fila, int columna) {
		return matriz[fila][columna];
	}

	public void mostrarMatriz() {
		for (int i = 0; i < grado; i++) {
			for (int j = 0; j < grado; j++)
				System.out.print(matriz[i][j]+" ");
			System.out.println();
		}
	}
}
