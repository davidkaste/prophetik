package com.davidcastella.prophetik.core.userdistance;

import com.davidcastella.prophetik.core.userdistance.PearsonCorrelation;
import com.davidcastella.prophetik.core.userdistance.UserDistance;
import com.hp.hpl.jena.rdf.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by david_000 on 10/06/2015.
 */
public class PearsonCorrelationTest {

    private Model graphTest;
    private UserDistance pearson;

    @Before
    public void setUp() throws Exception {
        pearson = new PearsonCorrelation();
    }

    @Test
    public void testGetUserDistance() throws Exception {
        String lisa = "http://testdomain.com/user/LisaRose";
        String gene = "http://testdomain.com/user/GeneSeymour";
        Double expected = 0.396059017191;
        Double result = pearson.getUserDistance(graphTest, lisa, gene);
        assertEquals(expected, result);
    }
}