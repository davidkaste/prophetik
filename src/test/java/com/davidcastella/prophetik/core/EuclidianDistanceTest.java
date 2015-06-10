package com.davidcastella.prophetik.core;

import com.hp.hpl.jena.rdf.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David Castellà <david.castella@riseup.net> on 10/06/2015.
 */
public class EuclidianDistanceTest {

    private Model graphTest;
    private UserDistance euclidian;

    @Before
    public void setUp() throws Exception {
        euclidian = new EuclidianDistance();
    }

    @Test
    public void testGetUserDistance() throws Exception {
        String lisa = "http://testdomain.com/user/LisaRose";
        String gene = "http://testdomain.com/user/GeneSeymour";
        Double expected = 1.148148148148;
        Double result = euclidian.getUserDistance(graphTest, lisa, gene);
        assertEquals(expected, result);
    }
}