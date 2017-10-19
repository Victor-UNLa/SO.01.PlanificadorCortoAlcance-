package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Proceso.Prioridad;

public class Listo {

	private int idListo;
	private List<Proceso> lstProcesos;

	public Listo(int idListo) {
		this.idListo = idListo;
		this.lstProcesos = new ArrayList<Proceso>();
	}

	public int getIdListo() {
		return idListo;
	}

	public void setIdListo(int idListo) {
		this.idListo = idListo;
	}

	public List<Proceso> getLstProcesos() {
		return lstProcesos;
	}

	public void setLstProcesos(List<Proceso> lstProcesos) {
		this.lstProcesos = lstProcesos;
	}

	/*------------------------------------------------------*/
	// Metodos
	public Proceso traerProceso(int idProceso) {
		Proceso procesoAux = null;
		int i = 0;
		int lenD = 0;
		if (!getLstProcesos().isEmpty()) {
			lenD = getLstProcesos().size();
			while (i < lenD) {
				if (getLstProcesos().get(i).equal(idProceso)) {
					procesoAux = getLstProcesos().get(i);
					i = lenD;
				}
				i++;
			}
		}
		return procesoAux;
	}

	public boolean listarProceso(Proceso proceso) {
		boolean agregar = false;
		agregar = getLstProcesos().add(proceso);
		return agregar;
	}

	public Proceso deslistarProceso(int idProceso) {
		Proceso procesoAux = null;
		if (!traerProceso(idProceso).equal(null)) {
			procesoAux = traerProceso(idProceso);
			int i = 0;
			int lenD = getLstProcesos().size();
			while (i < lenD) {
				if (traerProceso(i + 1).equals(idProceso)) {
					getLstProcesos().remove(i);
					i = lenD;
				}
				i++;
			}
		}
		return procesoAux;
	}

	public boolean ordenarPrioridad() {
		boolean orden = false;
		List<Proceso> lstProcesosAux = new ArrayList<Proceso>();
		int i = 0;
		int contAlta = 0;
		int contMedia = 0;
		int lenD = getLstProcesos().size();
		// Ordeno lista
		while (i < lenD) {
			if (getLstProcesos().get(i).getPrioridad().equals(Prioridad.Alta)) {
				if (lstProcesosAux.isEmpty()) {
					lstProcesosAux.add(getLstProcesos().get(i));
				} else {
					lstProcesosAux.add(contAlta, getLstProcesos().get(i));
				}
				contAlta++;
			}
			if (getLstProcesos().get(i).getPrioridad().equals(Prioridad.Media)) {
				if (lstProcesosAux.isEmpty()) {
					lstProcesosAux.add(getLstProcesos().get(i));
				} else {
					lstProcesosAux.add(contAlta+contMedia, getLstProcesos().get(i));
				}
				contMedia++;
			}
			if (getLstProcesos().get(i).getPrioridad().equals(Prioridad.Baja)) {
				lstProcesosAux.add(getLstProcesos().get(i));	
			}
			// Siguiente
			i++;
		}// Termino y guardo lista ordenada por prioridad 
		getLstProcesos().removeAll(lstProcesos);
		setLstProcesos(lstProcesosAux);
		return orden;
	}

	public boolean ordenarTiempoTotal() {
		boolean ordenado=false;
		// Ordeno lista por InsertionSort
		ordenado=InsertionSort();
		return ordenado;
	}
	
	public boolean ordenarTiempoRestante() {
		boolean ordenado=false;
		// Se calcula tiempo restante
		int lenD = getLstProcesos().size();
		Proceso procesoAux = new Proceso();
		int j=0;
		int iCPU=0;
		int fCPU=0;
		while (j<lenD) {
			iCPU=0;
			fCPU=0;
			procesoAux=getLstProcesos().get(j);
			if (procesoAux.getDuracion().getiCPU()>0) {
				iCPU=procesoAux.getDuracion().getiCPU();
			}
			if (procesoAux.getDuracion().getfCPU()>0) {
				fCPU=procesoAux.getDuracion().getfCPU();
			}
			procesoAux.getDuracion().setTiempoTotal(iCPU+fCPU);
			j++;
		}
		// Se Ordena lista con: InsertionSort
		ordenado=InsertionSort();
		return ordenado;
	}
	
	private boolean InsertionSort(){
		int lenD = getLstProcesos().size();
		Proceso procesoAux = new Proceso();int k;
		boolean ordenado=false;
		for(int i=1;i<lenD;i++){
			procesoAux=getLstProcesos().get(i);
			k=i-1;
			ordenado=false;
			while(!ordenado && k>=0){
				if(procesoAux.getDuracion().getTiempoTotal()<getLstProcesos().get(k).getDuracion().getTiempoTotal()){	
					getLstProcesos().set(k+1, getLstProcesos().get(k));
					k=k-1;
				}else{
					ordenado=true;
				}
			}
			getLstProcesos().set(k+1, procesoAux);
		}
		return true;
	}
	
	public boolean tasaRespuesta(){
		int prioridad=0;
		if (!getLstProcesos().isEmpty()) {
			for (Proceso proceso :  getLstProcesos()) {
				proceso.tiempoEspera();
				prioridad=proceso.tasaRespuesta();
				if (prioridad>=0 && prioridad<=3 ) {
					proceso.setPrioridad(Prioridad.Baja);
				}
				if (prioridad>=4 && prioridad<=6) {
					proceso.setPrioridad(Prioridad.Media);
				}
				if (prioridad>=7) {
					proceso.setPrioridad(Prioridad.Alta);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean expulsionFIFO(){
		boolean end=false;
		Proceso p=getLstProcesos().get(getLstProcesos().size()-1);
		if (!end && p.getPrioridad().equals(Prioridad.Alta)) {
			p.setPrioridad(Prioridad.Media);
			end=true;
		}
		if (!end && p.getPrioridad().equals(Prioridad.Media)) {
			p.setPrioridad(Prioridad.Baja);
			end=true;
		}
		// un proceso no puede desender m�s
		return end;
	}
	
	public boolean promoverProcesoFIFO(){
		boolean promoted=false;
		if (!getLstProcesos().isEmpty()) {
			Proceso p=getLstProcesos().get(getLstProcesos().size()-1);
			p.setPrioridad(Prioridad.Alta);
			promoted=true;
		}
		return promoted;
	}
	//public boolean 
	/*------------------------------------------------------*/
	// @Override ->
	@Override
	public String toString() {
		String string = "IdListo=" + getIdListo();
		if (!getLstProcesos().isEmpty()) {
			string += ", LstProcesos=[";
			for (Proceso proceso : getLstProcesos()) {
				string += "\n\t" + proceso;
			}
			string += "]";
		} else {
			string += ", LstProcesos=[" + null + "]";
		}
		return string;
	}

}
