package backend;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArbolGeneradorMinimoTest {
	
	Red red;
	Localidad sm;
	Localidad bv;
	Localidad otra;
	ArbolGeneradorMinimo arbol;
	
	

	@Before
	public void initialize() {

		sm = new Localidad("San Miguel","buenos aires",-34.5429,-58.7121);
		bv = new Localidad("bv", "buenos aires", -34.5611,-58.6846);
		otra = new Localidad("otra", "buenos aires", -30.4455, -70.4344);
		
		Red red = new Red();
		
		red.agregarLocalidad(sm);
		red.agregarLocalidad(bv);
		red.agregarLocalidad(otra);
		
		arbol = new ArbolGeneradorMinimo(red);
				
	}
	
	@Test
	public void cantidadLocalidadesTest() {
		initialize();
		
		assertEquals(3, arbol.getLocalidades().size());
	}

}
