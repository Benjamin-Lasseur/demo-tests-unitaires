package dev.console;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class AppTest {
	/** systemOutRule : SystemOutRule */
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

	/** systemInMock : TextFromStandardInputStream */
	@Rule
	public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	/** app : App */
	private App app;
	/** calculService : CalculService */
	private CalculService calculService;
	/** scanner : Scanner */
	private Scanner scanner;

	/**
	 * Initialisation des tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		scanner = new Scanner(System.in);
		this.calculService = new CalculService();
		this.app = new App(scanner, calculService);
	}

	/**
	 * Test de l'affichage du titre
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");
	}

	/**
	 * Test de l'évaluation en utilisant un mock
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEvaluer() throws Exception {
		CalculService localCalculService = mock(CalculService.class);
		App appLocal = new App(new Scanner(System.in), localCalculService);
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(localCalculService.additionner(expression)).thenReturn(35);
		LOG.info("Lorsque la méthode evaluer est invoquée");
		appLocal.evaluer(expression);
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(localCalculService).additionner(expression);
		LOG.info("Alors dans la console, s'affiche 1+34=35");
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}

	/**
	 * Test non passant de évaluer
	 */
	@Test
	public void testEvaluerNonPassant() {
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1-34";
		LOG.info("Lorsque la méthode evaluer est invoquée");
		try {
			this.app.evaluer(expression);
		} catch (CalculException e) {
			LOG.info(e.getMessage());
		}
		assertThat(systemOutRule.getLog()).contains("Expression " + expression + " non valide!");
	}

	/**
	 * Test de démarrer avec "fin"
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDemarrerFin() throws Exception {
		systemInMock.provideLines("fin");
		app.demarrer();
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}

	/**
	 * Test de démarrer avec "1+2" puis "fin"
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDemarrerUnPlusDeux() throws Exception {
		systemInMock.provideLines("1+2", "fin");
		app.demarrer();
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}

	/**
	 * Test de démarrer avec "AAAAA" puis "fin"
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDemarrerAAAAA() throws Exception {
		systemInMock.provideLines("AAAAA", "fin");
		app.demarrer();
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
}