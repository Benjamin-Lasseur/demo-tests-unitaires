package dev.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.service.CalculService;

public class App {
	/** scanner : Scanner */
	private Scanner scanner;
	/** calculatrice : CalculService */
	private CalculService calculatrice;
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	/** interdits : String[] */
	private String[] interdits = { "a", "b", "c", "d", "A", "B", "C", "D", "-", "r" };

	/**
	 * Constructeur
	 * 
	 * @param scanner
	 * @param calculatrice
	 */
	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}

	/**
	 * Affiche le nom de l'application
	 */
	protected void afficherTitre() {
		LOG.debug("**** Application Calculatrice ****");
	}

	/**
	 * Prend une entrée clavier, test si "fin", puis les caractères interdits et
	 * si bon appelle la méthode évaluer
	 */
	public void demarrer() {
		afficherTitre();
		while (true) {
			LOG.info("Veuillez saisir une expression :");
			String choix = scanner.next();
			if (choix.equals("fin")) {
				LOG.info("Aurevoir :-(");
				break;
			} else if (Arrays.asList(interdits).stream().anyMatch(p -> choix.contains(p))) {
				LOG.info("L'expression {} est invalide", choix);
			} else {
				evaluer(choix);
			}
		}
	}

	/**
	 * Méthode permettant d'évaluer l'expression
	 * 
	 * @param expression
	 */
	protected void evaluer(String expression) {
		LOG.debug("{}={}", expression, calculatrice.additionner(expression));
	}
}