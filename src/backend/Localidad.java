package backend;

import java.util.ArrayList;

public class Localidad {
	
	private String nombre;
	private String provincia;
	private double latitud;
	private double longitud;
	private ArrayList<Tramo>tramos;
	private boolean marcada;

	public Localidad (String nombre, String provincia, double latitud, double longitud) {
		
		this.nombre = nombre;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
		tramos = new ArrayList<>();
		this.marcada = false;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	
	public ArrayList<Tramo> getTramos() {
		return this.tramos;
	}

	public void agregarTramo (Localidad localidad) {
		if (!existeTramo(localidad)) {
			this.tramos.add(new Tramo (this, localidad));
		}
	}
	
	public boolean existeTramo (Localidad localidad) {
		boolean ret = false;
		for (Tramo tramo : this.tramos) {
			if (tramo.contieneLocalidades(this, localidad))
				ret = true;
		}
		return ret;
	}
	
	public boolean tieneTramos() {
		return !this.tramos.isEmpty();
	}
	
	//auxiliar cantidad de tramos
	public int cantTramos() {
		return this.tramos.size();
	}
	
	public Tramo menorTramo() {
		Tramo ret = this.tramos.get(0);
		double menor = this.tramos.get(0).getCostoTramo();
		for (Tramo tramo : this.tramos) {
			if (tramo.getCostoTramo()<menor) {
				menor = tramo.getCostoTramo();
				ret = tramo;
			}
		}
		return ret;
	}
	
	public Tramo getTramo(Localidad loc) {
		Tramo ret = null;
		for (Tramo t : this.tramos) {
			if ((t.getLocalidadDestino().equals(loc) && t.getLocalidadOrigen().equals(this)) || (t.getLocalidadOrigen().equals(loc) && t.getLocalidadDestino().equals(this))) {
				ret = t;
			}
		}
		return ret;
	}
	
	public void setMarcada(boolean bol) {
		this.marcada = bol;
	}
	public boolean marcada() {
		return this.marcada;
	}
}
