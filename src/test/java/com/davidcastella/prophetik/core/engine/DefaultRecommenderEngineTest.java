package com.davidcastella.prophetik.core.engine;

import com.davidcastella.prophetik.core.userdistance.EuclidianDistance;
import com.davidcastella.prophetik.core.userdistance.PearsonCorrelation;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by davidkaste on 27/7/15.
 */
public class DefaultRecommenderEngineTest {
    private RecommenderEngine re;
    private Model graphTest;

    @Before
    public void setUp() throws Exception {
        re = new DefaultRecommenderEngine();
        OntModel auxGraph = ModelFactory.createOntologyModel();
        graphTest = auxGraph.read(new InputStreamReader(
                        new FileInputStream(getClass().getClassLoader().getResource("test-fixture.rdf").getPath())),
                "RDF/XML-ABBREV");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetRecommendations() throws Exception {
        Map<String, Double> recs = re.getRecommendations(graphTest, "http://testdomain.com/user/Toby", "http://schema.org/Person", new PearsonCorrelation());
        assertEquals(getExpected(), recs);
    }

    private Map<String, Double> getExpected() {
        Map<String, Double> ex = new HashMap<String, Double>();
        ex.put("http://testdomain.com/movie/TheNightListener", 3.3477895267131013);
        ex.put("http://testdomain.com/movie/LadyInTheWater", 2.832549918264162);
        ex.put("http://testdomain.com/movie/JustMyLuck", 2.5309807037655645);
        return ex;
    }
}