package com.davidcastella.prophetik;

import com.davidcastella.prophetik.core.engine.DefaultRecommenderEngine;
import com.davidcastella.prophetik.core.engine.RecommenderEngine;
import com.davidcastella.prophetik.core.matcher.DefaultTopMatcher;
import com.davidcastella.prophetik.core.userdistance.UserDistance;
import com.hp.hpl.jena.rdf.model.Model;

import java.util.Map;

/**
 * Created by david on 26/04/15.
 */
public class Prophetik {
    private String userColumn;
    private String productColumn;
    private Model dataGraph;
    private UserDistance userDistanceType;
    RecommenderEngine re;


    public Prophetik(Model graph,
              String userResourceUri,
              String productResourceUri,
              UserDistance userDistanceType) {
        userColumn = userResourceUri;
        productColumn = productResourceUri;
        dataGraph = graph;
        this.userDistanceType = userDistanceType;
        re = new DefaultRecommenderEngine();
    }

    public void extendGraph(Model graph) {
        dataGraph.add(graph);
    }

    public Map<String, Double> recommend(String userUri) {
        return re.getRecommendations(dataGraph, userUri, userColumn, userDistanceType);
    }

    public Map<String, Double> getMostSimilarUsers(String userUri, int top) {
        return new DefaultTopMatcher(dataGraph, userUri, userColumn, top, userDistanceType).getTopMatches();
    }
}
