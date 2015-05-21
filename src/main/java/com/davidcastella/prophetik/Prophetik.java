package com.davidcastella.prophetik;

import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * Created by david on 26/04/15.
 */
public class Prophetik {
    private String userColumn;
    private String ratingColumn;
    private String productColumn;
    private Model dataGraph;


    Prophetik(Model graph,
              String userResourceUri,
              String ratingResourceUri,
              String productResourceUri) {
        userColumn = userResourceUri;
        ratingColumn = ratingResourceUri;
        productColumn = productResourceUri;
        dataGraph = graph;
    }

    public void extendGraph(Model graph) {
        dataGraph.add(graph);
    }
}
