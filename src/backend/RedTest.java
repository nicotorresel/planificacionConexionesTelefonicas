package backend;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RedTest {
	
	private Red red;
	private Localidad sm;
	private Localidad bv;
	private Localidad jcp;
	
	@Before
	public void initialize() {
		sm = new Localidad("San Miguel","buenos aires",-34.5429,-58.7121);
		bv = new Localidad("bv", "buenos aires", -34.5611,-58.6846);
		red = new Red();
	}

	@Test
	public void existeLocalidadTest() {
		initialize();
		
		red.agregarLocalidad(sm);
		
		assertTrue(red.existeLocalidad(sm));
	}
	
	@Test
	public void noExisteLocalidadTest() {
		initialize();
		
		red.agregarLocalidad(sm);
		
		assertFalse(red.existeLocalidad(bv));
	}
	
	@Test
	public void agregarTramoTest() {
		initialize();
		
		red.agregarLocalidad(sm);
		red.agregarLocalidad(bv);
		
		assertTrue(red.existeTramo(bv,sm));
	}
	
	@Test
	public void tramoInexistenteTest() {
		initialize();
		jcp = new Localidad("jcp", "buenos aires", -35.3411,-59.9044);
		
		red.agregarLocalidad(sm);
		red.agregarLocalidad(bv);
		
		assertFalse(red.existeTramo(sm, jcp));
	}
	@Test
	public void existeTramoTest() {
		jcp = new Localidad("jcp", "buenos aires", -35.3411,-59.9044);
		
		red.agregarLocalidad(bv);
		red.agregarLocalidad(sm);
		red.agregarLocalidad(jcp);
		
		assertTrue(red.existeTramo(jcp, sm));
	}
	
}
