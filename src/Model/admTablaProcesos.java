package Model;

import java.util.List;

public class admTablaProcesos {

	private List<Proceso> lstProcesos;
	private int cantidadFilas;
	private int cantidaColumnas;

	public admTablaProcesos(admProcesamiento admP) {
		this.lstProcesos = admP.getLstProcesos();
		this.cantidadFilas = admP.getCantidadFilas();
		this.cantidaColumnas = admP.getCantidaColumnas();
	}

	public int getCantidadFilas() {
		return cantidadFilas;
	}

	public void setCantidadFilas(int cantidadFilas) {
		this.cantidadFilas = cantidadFilas;
	}

	public int getCantidaColumnas() {
		return cantidaColumnas;
	}

	public void setCantidaColumnas(int cantidaColumnas) {
		this.cantidaColumnas = cantidaColumnas;
	}

	public List<Proceso> getLstProcesos() {
		return lstProcesos;
	}

	public void setLstProcesos(List<Proceso> lstProcesos) {
		this.lstProcesos = lstProcesos;
	}

	// Métodos
	public Proceso traerProceso(int idProceso) {
		return lstProcesos.get(idProceso - 1);
	}

	public String mostrarResultados(Tabla[][] tabla) {
		String string = "";
		string += "\n" + toString(tabla);
		return string;
	}

	// Formulas
	public int tiempoLLegada(int idProceso) {
		return traerProceso(idProceso).getComienzaTiempo();
	}

	public int tiempototal(int idProceso) {
		return traerProceso(idProceso).tiempoTotal();
	}

	public int tiempoFinalizacion(int idProceso, Tabla[][] tabla) {
		int t = 0;
		for (int i = 0; i < getCantidaColumnas(); i++) {
			if (tabla[idProceso - 1][i].equals("T")) {
				t = i + 1;
			}
		}
		return t;
	}

	public int tiempoRespuesta(int idProceso, Tabla[][] tabla) {
		return tiempoFinalizacion(idProceso, tabla) - tiempoLLegada(idProceso);
	}

	public int tiempoDesperdiciado(int idProceso, Tabla[][] tabla) {
		return tiempoRespuesta(idProceso, tabla) - tiempototal(idProceso);
	}

	public float tiempoPenalización(int idProceso, Tabla[][] tabla) {
		return (float) tiempoRespuesta(idProceso, tabla) / tiempototal(idProceso);
	}

	public float promedio() {

		return 0;
	}

	// @Override ->
	/*------------------------------------------------------*/
	public String toString(Tabla[][] tabla) {
		String string = "";
		float T = 0;
		float W = 0;
		float P = 0;

		String texto[] = { "Proceso", "Tiempo llegada", "Tiempo Total", "Tiempo Finalización", "Tiempo de Repuesta(T)",
				"Tiempo Desperdiciado(W)", "Tiempo de Penalización(P)  " };
		string += "Resultados->\n";
		for (int i = 0; i <= 72; i++) {
			string += "--";
		}
		string += "\n" + "|";
		for (int i = 0; i < 7; i++) {

			string += " " + texto[i] + " |";
		}
		// string += "\n" + "|";
		for (int i = 0; i < lstProcesos.size(); i++) {
			string += "\n| " + traerProceso(i + 1).getProceso() + "\t  |";
			string += " " + tiempoLLegada(i + 1) + "\t\t   |";
			string += " " + tiempototal(i + 1) + "\t\t  |";
			string += " " + tiempoFinalizacion(i + 1, tabla) + "\t\t\t|";
			// Promedios
			T += tiempoRespuesta(i + 1, tabla);
			string += " " + tiempoRespuesta(i + 1, tabla) + "\t\t\t|";
			W += tiempoDesperdiciado(i + 1, tabla);
			string += " " + tiempoDesperdiciado(i + 1, tabla) + "\t\t\t  |";
			P += tiempoPenalización(i + 1, tabla);
			string += " " + tiempoPenalización(i + 1, tabla) + "         \t\t|";
		}

		string += "\n";
		for (int i = 0; i <= 72; i++) {
			string += "--";
		}
		string += "\n";
		string += "|\tPromedio: \t\t\t\t\t\t" + "T=" + T / lstProcesos.size() + "  \t\t" + "W=" + W / lstProcesos.size()
				+ "  \t\t  " + "P=" + P / lstProcesos.size() + "\t";

		string += "\n";
		for (int i = 0; i <= 72; i++) {
			string += "--";
		}
		return string;
	}
}
