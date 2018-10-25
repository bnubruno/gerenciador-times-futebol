package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class TimeTest {

    @Test
    public void setIdTest() {
        Time time = new Time(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

        time.setId(2l);
        if (time.getId() != 2l)
            Assert.fail();
    }

    @Test
    public void setCorUniformePrincipalTest() {
        Time time = new Time(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

        time.setCorUniformePrincipal("Verde");
        if (time.getCorUniformePrincipal() != "Verde")
            Assert.fail();
    }

    @Test
    public void setCorUniformeSecundarioTest() {
        Time time = new Time(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");

        time.setCorUniformeSecundario("Verde");
        if (time.getCorUniformeSecundario() != "Verde")
            Assert.fail();
    }
}
