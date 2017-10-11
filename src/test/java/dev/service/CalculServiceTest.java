package dev.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import static org.assertj.core.api.Assertions.*;

/**
 * Test unitaire de la classe dev.service.CalculService.
 */
public class CalculServiceTest {
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	/**
	 * Test de la méthode additioner
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAdditionner() throws Exception {
		LOG.info("Etant donné, une instance de la classe CalculService");
		CalculService cS = new CalculService();
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = cS.additionner("1+3+4");
		LOG.info("Alors j'obtiens le résultat 8");
		assert somme == 8;
	}

	/**
	 * Test non passant de la méthode additionner
	 */
	@Test(expected = CalculException.class)
	public void testAdditionnerNonPassant() {
		LOG.info("Etant donné, une instance de la classe CalculService");
		CalculService cS = new CalculService();
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = cS.additionner("1+3-4");
		LOG.info("Alors j'obtiens le résultat 8");
		assert somme == 8;
	}

	/**
	 * Test de la méthode additionner avec JAssert
	 */
	public void testAdditionnerJAssert() {
		LOG.info("Etant donné, une instance de la classe CalculService");
		CalculService cS = new CalculService();
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = cS.additionner("1+3-4");
		LOG.info("Alors j'obtiens le résultat 8");
		assertThat(somme).isEqualTo(8);
	}
}