package Test;

import Model.admProcesamiento;
import Model.Prioridad;
import Model.Tabla;

public class TestSRT {

	public static void main(String[] args) {

		admProcesamiento admP1 = new admProcesamiento(10, 30);
		System.out.println("-----------Creando-----------");
		admP1.agregarProceso("P1", 1, 3, 2, 4, Prioridad.Media);
		admP1.agregarProceso("P2", 2, 2, 3, 2, Prioridad.Baja);
		admP1.agregarProceso("P3", 4, 4, 2, 1, Prioridad.Alta);
		admP1.agregarProceso("P4", 6, 1, 1, 1, Prioridad.Media);
		System.out.println(mostrarAlgoritmoSRT(admP1));

		admProcesamiento admP2 = new admProcesamiento(10, 33);
		System.out.println("-----------Creando-----------");
		admP2.agregarProceso("P1", 1, 4, 3, 3, Prioridad.Alta);
		admP2.agregarProceso("P2", 1, 2, 4, 3, Prioridad.Baja);
		admP2.agregarProceso("P3", 3, 2, 5, 1, Prioridad.Media);
		admP2.agregarProceso("P4", 3, 1, 4, 5, Prioridad.Media);
		admP2.agregarProceso("P5", 5, 3, 2, 3, Prioridad.Alta);
		System.out.println(mostrarAlgoritmoSRT(admP2));

		admProcesamiento admP3 = new admProcesamiento(10, 33);
		System.out.println("-----------Creando-----------");
		admP3.agregarProceso("P1", 1, 4, 2, 1, Prioridad.Baja);
		admP3.agregarProceso("P2", 1, 2, 4, 3, Prioridad.Media);
		admP3.agregarProceso("P3", 2, 1, 2, 1, Prioridad.Alta);
		admP3.agregarProceso("P4", 2, 3, 1, 2, Prioridad.Media);
		admP3.agregarProceso("P5", 3, 3, 2, 4, Prioridad.Alta);
		System.out.println(mostrarAlgoritmoSRT(admP3));

		admProcesamiento admP4 = new admProcesamiento(10, 33);
		System.out.println("-----------Creando-----------");
		admP4.agregarProceso("P1", 1, 4, 2, 2, Prioridad.Media);
		admP4.agregarProceso("P2", 1, 5, 3, 2, Prioridad.Alta);
		admP4.agregarProceso("P3", 2, 2, 2, 3, Prioridad.Baja);
		admP4.agregarProceso("P4", 3, 1, 3, 3, Prioridad.Baja);
		admP4.agregarProceso("P5", 4, 3, 3, 4, Prioridad.Alta);
		System.out.println(mostrarAlgoritmoSRT(admP4));

	}

	public static String mostrarAlgoritmoSRT(admProcesamiento admP) {
		String string = "";
		Tabla[][] tabla = admP.planificarSRT();
		string += "Algoritmo SRT (Apropiativos)";
		string += "\n" + admP.toString(tabla);
		string += "\n" + admP.mostrarProcesos();
		string += "\n-> hay 1 procesador";
		string += "\n-> E/S Se realiza en paralelo\n";
		return string;
	}
	
}
