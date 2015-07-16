package com.davidcastella.prophetik.core.userdistance;

import com.davidcastella.prophetik.core.userdistance.EuclidianDistance;
import com.davidcastella.prophetik.core.userdistance.UserDistance;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Created by David Castellà <david.castella@riseup.net> on 10/06/2015.
 */
public class EuclidianDistanceTest {

    private Model graphTest;
    private UserDistance euclidian;

    @Before
    public void setUp() throws Exception {
        OntModel auxGraph = ModelFactory.createOntologyModel();
        euclidian = new EuclidianDistance();
        graphTest = auxGraph.read(new InputStreamReader(
                        new FileInputStream(getClass().getClassLoader().getResource("test-fixture.rdf").getPath())),
                "RDF/XML-ABBREV");
        graphTest.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
        graphTest.setNsPrefix("schema", "http://schema.org/");
    }

    @Test
    public void testGetUserDistance() throws Exception {
        String lisa = "http://testdomain.com/user/LisaRose";
        String gene = "http://testdomain.com/user/GeneSeymour";
        DecimalFormat df = new DecimalFormat("###0.0000");
        Double expected = 0.148148148148;
        Double result = euclidian.getUserDistance(graphTest, lisa, gene);
        assertEquals(df.format(expected), df.format(result));
    }
}