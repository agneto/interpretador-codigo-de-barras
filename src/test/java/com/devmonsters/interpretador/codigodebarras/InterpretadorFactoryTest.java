package com.devmonsters.interpretador.codigodebarras;

import org.junit.Assert;
import org.junit.Test;

import com.devmonsters.interpretador.codigodebarras.arrecadacao.InterpretadorArrecadacao;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloBradesco;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloGenerico;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloHsbc;
import com.devmonsters.interpretador.codigodebarras.titulo.impl.InterpretadorTituloItau;

public class InterpretadorFactoryTest {

    @Test(expected = NullPointerException.class)
    public void nullPointerSeParametroNulo() throws Exception {
        new InterpretadorFactory().getInterpretador(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oCodigoDeBarrasDevePossuir44Digitos() throws Exception {
        new InterpretadorFactory().getInterpretador("399955672000011190028433067799");
    }

    @Test
    public void pagamentoDeArrecadacao() throws Exception {
        Assert.assertEquals(InterpretadorArrecadacao.class, new InterpretadorFactory().getInterpretador("85850000001045402941309401000000130151769700").getClass());
    }

    @Test
    public void bancoDesconhecidoDeveRetornarGenerico() throws Exception {
        Assert.assertEquals(InterpretadorTituloGenerico.class, new InterpretadorFactory().getInterpretador("00095567200001119002843306779912340123456001").getClass());
    }

    @Test
    public void banco399deveRetornarHSBC() throws Exception {
        Assert.assertEquals(InterpretadorTituloHsbc.class, new InterpretadorFactory().getInterpretador("39995567200001119002843306779912340123456001").getClass());
    }

    @Test
    public void banco237deveRetornarBradesco() throws Exception {
        Assert.assertEquals(InterpretadorTituloBradesco.class, new InterpretadorFactory().getInterpretador("23796565100000658712657090008068809100000120").getClass());
    }

    @Test
    public void banco341deveRetornarItau() throws Exception {
        Assert.assertEquals(InterpretadorTituloItau.class, new InterpretadorFactory().getInterpretador("34196568300043759501572273150081234987655000").getClass());
    }
}