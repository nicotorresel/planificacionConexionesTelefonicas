package backend;

import static org.junit.Assert.*;
import org.junit.Test;

public class LocalidadTest {
	
	// testeo que agregue la localidad
	@Test
	public void agregarVecinoTest() {
		
		Localidad sm = new Localidad("San Miguel","buenos aires",-34.5429,-58.7121);
		Localidad bv = new Localidad("bv", "buenos aires", -34.5611,-58.6846);
		
		sm.agregarTramo(bv);
		
		assertTrue(sm.existeTramo(bv));
	}
	
	@Test
	public void agregarVecinoDoble() {
		Localidad sm = new Localidad("San Miguel","buenos aires",-34.5429,-58.7121);
		Localidad bv = new Localidad("bv", "buenos aires", -34.5611,-58.6846);
		Localidad otra = new Localidad("otra", "buenos aires", -30.4455, -70.4344);
		
		sm.agregarTramo(bv);
		sm.agregarTramo(otra);
		sm.agregarTramo(bv);
		
		assertEquals(2,sm.cantTramos());
	}
	
	@Test
	public void tieneTramoTest() {
		
		Localidad sm = new Localidad("San Miguel","buenos aires",-34.5429,-58.7121);
		
		assertFalse(sm.tieneTramos());
	}	
	
	@Test
	public void menorTramoTest() {
		Localidad sm = new Localidad("San Miguel","buenos aires",-34.5429,-58.7121);
		Localidad bv = new Localidad("bv", "buenos aires", -34.5611,-58.6846);
		Localidad otra = new Localidad("otra", "Salta", -30.4455, -70.4344);
		
		sm.agregarTramo(bv);
		sm.agregarTramo(otra);
		
		assertEquals(sm.getTramo(bv), sm.menorTramo());
	}
		
}
