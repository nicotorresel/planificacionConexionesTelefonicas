package backend;

import java.util.ArrayList;
import java.util.Collections;

public class ArbolGeneradorMinimo {
	
	//private ArrayList<Tramo> tramos;
	private ArrayList<Tramo> result;
	
	public ArbolGeneradorMinimo(Red red) {
		
		if (red.getLocalidades().size()==1)
			throw new RuntimeException("Para poder generar un Arbol Generador Minimo debe haber mas de una localidad");
		// en este arrayList traigo todo los tramos del grafo completo y 
		// en la clase tramo modifique el equals para que un tramo que tiene
		// dos provincias iguales sin importar origen destino cuente como un tramo solo
		ArrayList <Tramo >tramos = new ArrayList<>();
		for (Localidad loc : red.getLocalidades()) {
			for (Tramo t : loc.getTramos()) {
				if (!tramos.contains(t))
					tramos.add(t);
			}
		}
		Collections.sort(tramos); // ordeno el array de menor tramo que significa menor costo.
		//para ello overridee el compare en la clase Tramo.
		
		// ahora hago los tramos resultado del arbol generador minimo
		result = new ArrayList<>();
		Tramo t = tramos.get(0);
		
		for (int i = 0; i<tramos.size(); i++ ) {
			
			t = tramos.get(i);
			
			if (!result.contains(t) && (!t.getLocalidadOrigen().marcada() || !t.getLocalidadDestino().marcada())){	
				result.add(t);
				tramos.get(i).getLocalidadOrigen().setMarcada(true);
				tramos.get(i).getLocalidadDestino().setMarcada(true);
			}	
		}
	}
	
	public ArrayList<Tramo> getTramos(){
		return this.result;
	}

	public double costoTotal() {
		double ret = 0;
		for (Tramo t : this.result) {
			ret = ret + t.getCostoTramo();
		}
		return ret;
	}
	
	public double totalKm() {
		double ret = 0;
		for (Tramo t : this.result) {
			ret = ret + t.getTramoEnKm();
		}
		return ret;
	}
	public ArrayList<Localidad> getLocalidades(){
		
		ArrayList<Localidad> ret = new ArrayList<>();
		for (Tramo t : this.result) {
			if (!ret.contains(t.getLocalidadOrigen()))
				ret.add(t.getLocalidadOrigen());
			if (!ret.contains(t.getLocalidadDestino()))
				ret.add(t.getLocalidadDestino());
		}
		return ret;
	}
	
}
