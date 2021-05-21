package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ClasePersistenciaTest {

	@Test @Transactional @Rollback
	public void test() {
		fail("Not yet implemented");
	}

}
