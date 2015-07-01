package com.davidcastella.prophetik.core.matcher;

import com.davidcastella.prophetik.core.userdistance.PearsonCorrelation;
import com.davidcastella.prophetik.core.userdistance.UserDistance;
import com.hp.hpl.jena.rdf.model.Model;

import java.util.Map;

/**
 * Created by David Castellà on 14/06/2015.
 */
public class Matcher {
    private Model graph;
    private String userResourceUri;
    private int numberResults;
    private UserDistance distanceMethod;

    public Matcher(Model graph, String userResourceUri, int numberResults, UserDistance distanceMethod) {
        this.graph = graph;
        this.userResourceUri = userResourceUri;
        this.numberResults = numberResults;
        this.distanceMethod = distanceMethod;
    }

    public Matcher(Model graph, String userResourceUri) {
        this(graph, userResourceUri, 5, new PearsonCorrelation());
    }

    public Matcher(Model graph, String userResourceUri, int numberResults) {
        this(graph, userResourceUri, numberResults, new PearsonCorrelation());
    }

    public Matcher(Model graph, String userResourceUri, UserDistance distanceMethod) {
        this(graph, userResourceUri, 5, distanceMethod);
    }

    public Map<String, Double> getTopMatches() {
        return getTopMatches(numberResults);
    }

    public Map<String, Double> getTopMatches(int numberResults) {
        return null;
    }

}
