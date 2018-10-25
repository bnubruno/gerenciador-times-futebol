import br.com.codenation.DesafioMeuTimeApplication;
import br.com.codenation.Jogador;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DesafioMeuTimeApplicationTest{

    public DesafioMeuTimeApplication addTeamAnd3Players(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));
        desafio.incluirJogador(3l, 1l, "Fred Henning", LocalDate.of(2008, 2,10),
                12, BigDecimal.valueOf(50000l));
        desafio.incluirJogador(2l, 1l, "Yan Henning", LocalDate.of(1992, 10,31),
                6, BigDecimal.valueOf(3000l));
        return desafio;
    }

    @Test
    public void givenSameTeamReturnException() {
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");

        try {
            desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1), "Branco", "Azul");
        } catch (Exception e) {
            if (e instanceof IdentificadorUtilizadoException) {
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void givenJogadorThenTeamDontExistsReturnException(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try{
            desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                    10, BigDecimal.valueOf(10000l));
        }catch (Exception e){
            if(e instanceof TimeNaoEncontradoException){
                Assert.assertTrue(true);
            }

        }
        }

    @Test
    public void givenJogadorThenAddSameJogador(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));

        try {
            desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                    10, BigDecimal.valueOf(10000l));
        } catch (Exception e){
            if (e instanceof IdentificadorUtilizadoException){
                Assert.assertTrue(true);
            }
        }
        }


    @Test
    public void givenJogadorWhenDefinirCapitaoThenNãoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");

        try{
            desafio.definirCapitao(1l);
        }catch (Exception e){
            if ( e instanceof JogadorNaoEncontradoException){
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void givenCapitaoWhenBuscarCapitaoThenNãoInformado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        try{
            desafio.buscarCapitaoDoTime(1l);
        } catch (Exception e){
            if (e instanceof CapitaoNaoInformadoException){
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void givenCapitaoWhenBuscarCapitaoThenTimeNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try{
            desafio.buscarCapitaoDoTime(1l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException) Assert.assertTrue(true);
        }

    }

    @Test
    public void givenCapitaoWhenBsucarCapitaoThenRetornarCapitao(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));
        desafio.incluirJogador(2l, 1l, "Yan Henning", LocalDate.of(1992, 10,31),
                6, BigDecimal.valueOf(3000l));
        desafio.incluirJogador(3l, 1l, "Fred Henning", LocalDate.of(2008, 2,10),
                12, BigDecimal.valueOf(50000l));

        desafio.definirCapitao(1l);

        Long capitao = desafio.buscarCapitaoDoTime(1l);

        Assert.assertEquals(1l, capitao, 0);
    }

    @Test
    public void givenJogadorWhenBuscarNomeThenJogadorNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        try {
            desafio.buscarNomeJogador(2l);
        }catch (Exception e){
            if (e instanceof JogadorNaoEncontradoException){
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void givenJogadorWhenBuscarNomeThenJogadorFredEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));
        desafio.incluirJogador(2l, 1l, "Yan Henning", LocalDate.of(1992, 10,31),
                6, BigDecimal.valueOf(3000l));
        desafio.incluirJogador(3l, 1l, "Fred Henning", LocalDate.of(2008, 2,10),
                12, BigDecimal.valueOf(50000l));
        try {
            desafio.buscarNomeJogador(3l);
        }catch (Exception e){
            if (e instanceof JogadorNaoEncontradoException){
                Assert.assertTrue( false);
            }
        }

        String nomeJogador = desafio.buscarNomeJogador(3l);
        Assert.assertEquals("Fred Henning", nomeJogador);
    }

    @Test
    public void givenIncluirTimeWhenBuscarNomeTimeThenTimeNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try {
            desafio.buscarNomeTime(1l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException){
                Assert.assertTrue(true);
            }
        }
    }

    @Test
    public void givenIncluirTimeWhenBuscarNomeTimeThenTimeEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");

        String nomeTime = desafio.buscarNomeTime(1l);

        Assert.assertEquals("Real Madrid", nomeTime);
    }

    @Test
    public void givenJogadoresWhenBuscarJogadoresThenTimeNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try {
            desafio.buscarJogadoresDoTime(1l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException) Assert.assertTrue(true);
        }
    }

    @Test
    public void givenJogadoresWhenBuscarJogadoresThenJogadoresEncontrados(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));
        desafio.incluirJogador(3l, 1l, "Fred Henning", LocalDate.of(2008, 2,10),
                12, BigDecimal.valueOf(50000l));
        desafio.incluirJogador(2l, 1l, "Yan Henning", LocalDate.of(1992, 10,31),
                6, BigDecimal.valueOf(3000l));


        ArrayList<Long> list = new ArrayList<>();
        list.add(1l);
        list.add(2l);
        list.add(3l);

        ArrayList<Long> jogadoresList = (ArrayList<Long>) desafio.buscarJogadoresDoTime(1l);

        Assert.assertEquals(list, jogadoresList);
    }

    @Test
    public void givenJogadorWhenBuscarMelhorJogadorThenJogadorNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");

        try {
            desafio.buscarMelhorJogadorDoTime(1l);
        } catch (Exception e){
            if (e instanceof JogadorNaoEncontradoException)Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }
    }

    @Test
    public void givenJogadorWhenBuscarMelhorJogadorThenTimeNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try{
            desafio.buscarMelhorJogadorDoTime(1l);
        }catch (Exception e){
            if(e instanceof TimeNaoEncontradoException) Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }
    }

    @Test
    public void givenJogadorWhenBuscarMelhorJogadorThenJogadorEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));
        desafio.incluirJogador(3l, 1l, "Fred Henning", LocalDate.of(2008, 2,10),
                12, BigDecimal.valueOf(50000l));
        desafio.incluirJogador(2l, 1l, "Yan Henning", LocalDate.of(1992, 10,31),
                6, BigDecimal.valueOf(3000l));
        long nivelHabilidade = -100;
        try {
            nivelHabilidade = desafio.buscarMelhorJogadorDoTime(1l);
        }catch (Exception e){
            if (e instanceof JogadorNaoEncontradoException) Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }
        nivelHabilidade = desafio.buscarMelhorJogadorDoTime(1l);
        Assert.assertEquals(3, nivelHabilidade, 0);

    }

    @Test
    public void givenJogadorWhenBuscarJogadorMaisVelhoThenTimeNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();
        try{
            desafio.buscarJogadorMaisVelho(1l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException) Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }
    }

    @Test
    public void givenJogadorWhenBuscarJogadorMaisVelhoThenJogadorEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));
        desafio.incluirJogador(3l, 1l, "Fred Henning", LocalDate.of(2008, 2,10),
                12, BigDecimal.valueOf(50000l));
        desafio.incluirJogador(2l, 1l, "Yan Henning", LocalDate.of(1992, 10,31),
                6, BigDecimal.valueOf(3000l));

        Long idJogadorMaisVelho = -1l;
        try{
            idJogadorMaisVelho = desafio.buscarJogadorMaisVelho(1l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException)Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }

        Assert.assertEquals(2, idJogadorMaisVelho, 0);
    }

    @Test
    public void givenTimesWhenBuscarTimesThenNaoEncontrados(){
        List<Long> timesTest = null;

        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        List<Long> timesResp = null;

        try{
            timesResp = desafio.buscarTimes();
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException){
                Assert.assertTrue(false);
            }
        }

        Assert.assertEquals(timesTest, timesResp);
    }

    @Test
    public void givenTimesWhenBuscarTimesThenRetornarTimes(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirJogador(1l, 1l, "Karll Henning", LocalDate.of(1994, 12,5),
                10, BigDecimal.valueOf(10000l));
        desafio.incluirJogador(3l, 1l, "Fred Henning", LocalDate.of(2008, 2,10),
                12, BigDecimal.valueOf(50000l));
        desafio.incluirJogador(2l, 1l, "Yan Henning", LocalDate.of(1992, 10,31),
                6, BigDecimal.valueOf(3000l));


        ArrayList<Long> list = new ArrayList<>();
        list.add(1l);
        list.add(2l);

        ArrayList<Long> timesList = (ArrayList<Long>) desafio.buscarTimes();

        Assert.assertEquals(list, timesList);
    }

    @Test
    public void givenJogadorWhenBuscarJogadorMaiorSalarioThenTimeNaoEncontrado(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        try{
            desafio.buscarJogadorMaiorSalario(1l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException)Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }

    }

    @Test
    public void givenJogadorWhenBuscarJogadorMaiorSalarioThenJogadorRetornado(){
        DesafioMeuTimeApplication desafio = addTeamAnd3Players();

        Long idMaiorSalario = -1l;
        try{
            idMaiorSalario = desafio.buscarJogadorMaiorSalario(1l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException) Assert.assertTrue(false);
        }

        Assert.assertEquals(3l, idMaiorSalario, 0);
    }

    @Test
    public void givenJogadorWhenBuscarSalarioJogadorThenJogadorNaoEncontrado(){
        DesafioMeuTimeApplication desafio = addTeamAnd3Players();
        try {
            desafio.buscarSalarioDoJogador(4l);
        }catch (Exception e){
            if (e instanceof JogadorNaoEncontradoException)Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }
    }
    @Test
    public void givenJogadorWhenBuscarSalarioJogadorThenRetornaSalario(){
        DesafioMeuTimeApplication desafio = addTeamAnd3Players();
        BigDecimal salarioJogador = BigDecimal.valueOf(0);

        try{
            salarioJogador = desafio.buscarSalarioDoJogador(1l);
        }catch (Exception e){
            if (e instanceof JogadorNaoEncontradoException)Assert.assertTrue(false);
        }

        Assert.assertEquals(BigDecimal.valueOf(10000), salarioJogador);
    }

    @Test
    public void givenTimeWhenBuscarTopJogadoresThenRetornaJogador(){
        DesafioMeuTimeApplication desafio = addTeamAnd3Players();

        ArrayList<Long> list = new ArrayList<>();
        list.add(3l);
        list.add(1l);

        ArrayList<Long> topJogadores = (ArrayList<Long>) desafio.buscarTopJogadores(2);

        Assert.assertEquals(list, topJogadores);
    }

    @Test

    public void given2TimesWhenBuscarCorCamisaTimeDeForaThenTimeNaoEncontrado(){
        DesafioMeuTimeApplication desafio = addTeamAnd3Players();
        String camisaTimeFora = "";

        try{
            camisaTimeFora = desafio.buscarCorCamisaTimeDeFora(1l, 2l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException)Assert.assertTrue(true);
            else Assert.assertTrue(false);
        }
    }

    @Test
    public void given2TimesWhenBuscarCorCamisaTimeDeForaThenCamisaPrincipalTimeFora(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirTime(2l, "Corinthians", LocalDate.of(1992, 1, 1),
                "Preto", "Laranja");

        String corUniformePrincipal = "";

        try{
            corUniformePrincipal = desafio.buscarCorCamisaTimeDeFora(1l, 2l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException)Assert.assertTrue(false);
        }

        Assert.assertEquals("Preto", corUniformePrincipal);
    }

    @Test
    public void given2TimesWhenBuscarCorCamisaTimeDeForaThenCamisaSecundariaTimeFora(){
        DesafioMeuTimeApplication desafio = new DesafioMeuTimeApplication();

        desafio.incluirTime(1l, "Real Madrid", LocalDate.of(2016, 1, 1),
                "Branco", "Azul");
        desafio.incluirTime(2l, "Corinthians", LocalDate.of(1992, 1, 1),
                "Branco", "Laranja");

        String corUniformePrincipal = "";

        try{
            corUniformePrincipal = desafio.buscarCorCamisaTimeDeFora(1l, 2l);
        }catch (Exception e){
            if (e instanceof TimeNaoEncontradoException)Assert.assertTrue(false);
        }

        Assert.assertEquals("Laranja", corUniformePrincipal);
    }
}

