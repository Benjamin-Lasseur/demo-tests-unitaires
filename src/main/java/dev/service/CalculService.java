package dev.service;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

/**
 * @author ETY5
 *
 */
public class CalculService {
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);

	/**
	 * Parse les string en entier puis réalise l'addition et return la somme
	 * 
	 * @param expression
	 * @return
	 * @throws CalculException
	 */
	public int additionner(String expression) throws CalculException {
		LOG.info("Evaluation de l'expression " + expression);
		Optional<Integer> somme = Optional.of(0);
		if (expression.contains("-") || expression.contains("/") || expression.contains("*")) {
			throw new CalculException(expression);
		}
		String[] nombres = expression.split("\\+");
		somme = Arrays.stream(nombres).map(str -> Integer.parseInt(str)).reduce((n1, n2) -> n1 + n2);

		return somme.get();
	}

}
