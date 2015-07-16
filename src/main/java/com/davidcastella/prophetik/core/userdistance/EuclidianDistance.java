package com.davidcastella.prophetik.core.userdistance;

import com.davidcastella.prophetik.core.utils.Querier;
import com.hp.hpl.jena.rdf.model.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by david on 03/06/15.
 */
public class EuclidianDistance implements UserDistance {
    public double getUserDistance(Model graph, String user1Uri, String user2Uri) {
        Map<String, Integer> si = new HashMap<String, Integer>();
        Map<String, Double> prefs1 = Querier.getUserProducts(graph, user1Uri);
        Map<String, Double> prefs2 = Querier.getUserProducts(graph, user2Uri);

        Double sumOfSquares = 0.0;

        for(String key : prefs1.keySet()) {
            if(prefs2.containsKey(key)) {
                si.put(key, 1);
                sumOfSquares += Math.pow(prefs1.get(key) - prefs2.get(key), 2);
            }
        }

        if(si.isEmpty()) {
            return 0;
        }

        double returnValue = 1 / (1 + sumOfSquares);

        return returnValue;
    }
}
