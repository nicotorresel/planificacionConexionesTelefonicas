package backend;

public class Tramo implements Comparable <Tramo> {
	
	private double tramoEnKm;
	private Localidad localidadOrigen;
	private Localidad localidadDestino;
	private double costoTramo;
	static double costoPorKm;
	static double aumento;
	static double costoFijo;
	
	public Tramo(Localidad origen, Localidad destino) {
		
		this.localidadOrigen = origen;
		this.localidadDestino = destino;
		this.tramoEnKm = Red.distanciaEnKm(origen, destino);	

		this.costoTramo = this.tramoEnKm * costoPorKm;
		if (this.tramoEnKm > 300)
			this.costoTramo = this.costoTramo + (aumento * this.costoTramo / 100);
		if (!mismaProvincia())
			this.costoTramo = this.costoTramo + costoFijo;	

	}
	
	public static void setCostoPorKm (double CostoKm) {
		costoPorKm = CostoKm;
	}
	public static void setAumento (double aumentoPorcentual) {
		aumento = aumentoPorcentual;
	}
	public static void setCostoFijo (double costoF) {
		costoFijo = costoF;
	}
	
	public void setLocalidadOrigen (Localidad origen) {
		this.localidadOrigen = origen;
		this.tramoEnKm = Red.distanciaEnKm(origen, this.localidadDestino);
	}
	
	public void setLocalidadDestino (Localidad destino) {
		this.localidadDestino = destino;
		this.tramoEnKm = Red.distanciaEnKm(this.localidadOrigen, destino);
	}
	
	public double getTramoEnKm() {
		return this.tramoEnKm;
	}
	public double getCostoTramo() {
		return this.costoTramo;
	}
	
	public Localidad getLocalidadOrigen() {
		return this.localidadOrigen;
	}
	public Localidad getLocalidadDestino() {
		return this.localidadDestino;
	}
	
	// devuelve si el tramo contiene a las localides sin importar el orden destino origen
	public boolean contieneLocalidades(Localidad loc1, Localidad loc2) {
		if ( (this.getLocalidadOrigen().equals(loc1) && this.getLocalidadDestino().equals(loc2))  || 
				(this.getLocalidadDestino().equals(loc1) && this.getLocalidadOrigen().equals(loc2))) {
			return true;
		}
		return false;
	}
	
	public boolean mismaProvincia() {
		return (this.localidadOrigen.getProvincia().equals(this.localidadDestino.getProvincia()));
	}
		
	@Override
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tramo other = (Tramo) obj;
		Localidad loc1 = this.localidadOrigen;
		Localidad loc2 = this.localidadDestino;
		if (!other.contieneLocalidades(loc1, loc2))
			return false;
		return true;
	}

   @Override
    public int compareTo(Tramo o) {
        if (this.getCostoTramo() < o.getCostoTramo())
            return -1;
        else if (this.getCostoTramo() == o.getCostoTramo())
            return 0;
        else
            return 1;
    }
}
