package br.com.codenation;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import br.com.codenation.DesafioMeuTimeApplication.Time;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;

public class DesafioMeuTimeApplicationTest {

	@Test
	public void metodo01() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

		
		
	}

	@Test
	public void metodo() {
		DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

		desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

		try {
			desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
			Assert.assertTrue("Deveria dar exceção", false);
		} catch (Exception e) {
			if (e.getCause() instanceof IdentificadorUtilizadoException) {
				Assert.assertTrue(false);
			}
		}
	}

}
