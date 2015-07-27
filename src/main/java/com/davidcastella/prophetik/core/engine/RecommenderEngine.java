package com.davidcastella.prophetik.core.engine;

import com.davidcastella.prophetik.core.userdistance.UserDistance;
import com.hp.hpl.jena.rdf.model.Model;

import java.util.Map;

/**
 * Created by davidkaste on 21/7/15.
 */
public interface RecommenderEngine {
    public Map<String, Double> getRecommendations(Model graph, String userResource, UserDistance method);
}
