package com.davidcastella.prophetik.core.userdistance;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by davidkaste on 10/06/2015.
 */
public class PearsonCorrelationTest {
    private Model graphTest;
    private UserDistance pearson;

    @Before
    public void setUp() throws Exception {
        OntModel auxGraph = ModelFactory.createOntologyModel();
        pearson = new PearsonCorrelation();
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
        Double expected = 0.396059017191;
        Double result = pearson.getUserDistance(graphTest, lisa, gene);
        assertEquals(expected, result);
    }
}