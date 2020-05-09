package backend;

import java.util.ArrayList;
import java.util.List;

public class Red {
	
	private List <Localidad> localidades;
	
	public Red () {
		localidades = new ArrayList<>();
		
	}
	
	public void agregarLocalidad (Localidad localidad) {	
		if (!existeLocalidad(localidad)) {			
			for (Localidad existente : this.localidades) {
				agregarTramo(existente, localidad);
			}
			this.localidades.add(localidad);
		}
	}

	//agrega tramo a las localidades pasadas por parametro
	private void agregarTramo (Localidad localidad1, Localidad localidad2) {
		localidad1.agregarTramo(localidad2);
		localidad2.agregarTramo(localidad1);
	}
	
	//valida si existe tramo en la red (arista)
	public boolean existeTramo(Localidad loc1, Localidad loc2) {
		return (loc1.existeTramo(loc2) || loc2.existeTramo(loc1));
	}
	
	//valida si la localidad existe en la red (nodo o vertice)
	public boolean existeLocalidad (Localidad loc) {
		return (this.localidades.contains(loc));
	}
	
	//auxiliar para calcular la distancia entre dos localidades sabiendo su latitud y longitud
	public static double distanciaEnKm (Localidad loc1, Localidad loc2) {
		
		double lat1 = loc1.getLatitud();
		double lon1 = loc1.getLongitud();
		double lat2 = loc2.getLatitud();
		double lon2 = loc2.getLongitud();
		
	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c; // convert to meters

	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);  
	}
		
	public List<Localidad> getLocalidades(){
		return this.localidades;
	}
	

}
