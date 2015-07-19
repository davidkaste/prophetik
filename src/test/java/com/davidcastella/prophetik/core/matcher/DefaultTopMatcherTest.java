package com.davidcastella.prophetik.core.matcher;

import com.davidcastella.prophetik.core.utils.Querier;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 25/06/2015.
 */
public class DefaultTopMatcherTest {

    private TopMatcher m;
    private Model graphTest;

    @Before
    public void setUp() throws Exception {
        OntModel auxGraph = ModelFactory.createOntologyModel();
        graphTest = auxGraph.read(new InputStreamReader(
                        new FileInputStream(getClass().getClassLoader().getResource("test-fixture.rdf").getPath())),
                "RDF/XML-ABBREV");
        m = new DefaultTopMatcher(graphTest, "http://testdomain.com/user/Toby", 3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetTopMatches() throws Exception {
        Map<String, Double> result = m.getTopMatches();
        result = m.getTopMatches();
        Assert.assertEquals(getExpectedResult(), result);
    }

    private Map<String, Double> getExpectedResult() {
        Map<String, Double> expected = new HashMap<String, Double>();
        expected.put("http://testdomain.com/user/LisaRose", 0.99124070716192991);
        expected.put("http://testdomain.com/user/MickLaSalle", 0.92447345164190486);
        expected.put("http://testdomain.com/user/ClaudiaPuig", 0.89340514744156474);
        return expected;
    }
}