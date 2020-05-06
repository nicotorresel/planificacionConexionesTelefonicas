package backend;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TramoTest {
	
	Localidad sm;
	Localidad bv;
	Localidad otra;
	
	@Before
	public void initialize() {
		sm = new Localidad("San Miguel","buenos aires",-34.5429,-58.7121);
		bv = new Localidad("bv", "buenos aires", -34.5611,-58.6846);
		otra = new Localidad("otra", "Salta", -30.4455, -70.4344);
		
		sm.agregarTramo(bv);
		sm.agregarTramo(otra);
	}

	@Test
	public void contieneLocalidadesTest() {
		
		initialize();
		
		assertTrue(sm.getTramo(bv).contieneLocalidades(bv, sm));
	}
	
	@Test
	public void mismaProvinciaTest() {
		
		initialize();
		assertFalse(sm.getTramo(otra).mismaProvincia());
	}
	

}
