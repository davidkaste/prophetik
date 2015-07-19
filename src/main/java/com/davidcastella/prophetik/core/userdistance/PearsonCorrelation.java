package com.davidcastella.prophetik.core.userdistance;

import com.davidcastella.prophetik.core.utils.Querier;
import com.hp.hpl.jena.rdf.model.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by david on 03/06/15.
 */
public class PearsonCorrelation implements UserDistance {
    public double getUserDistance(Model graph, String user1Uri, String user2Uri) {
        Map<String, Integer> si = new HashMap<String, Integer>();
        Map<String, Double> prefs1 = Querier.getUserProductsRatings(graph, user1Uri);
        Map<String, Double> prefs2 = Querier.getUserProductsRatings(graph, user2Uri);

        Double sumSq1 = 0.0, sumSq2 = 0.0;
        Double sum1 = 0.0, sum2 = 0.0;
        Double pSum = 0.0;

        for(String key : prefs1.keySet()) {
            if(prefs2.containsKey(key)) {
                si.put(key, 1);
                sum1 += prefs1.get(key);
                sum2 += prefs2.get(key);
                sumSq1 += Math.pow(prefs1.get(key), 2);
                sumSq2 += Math.pow(prefs2.get(key), 2);
                pSum += prefs1.get(key) * prefs2.get(key);
            }
        }

        if(si.isEmpty()) return 0;

        Double num = pSum - (sum1 * sum2 / si.size());
        Double den = Math.sqrt((sumSq1 - Math.pow(sum1, 2) / si.size()) * (sumSq2 - Math.pow(sum2, 2) / si.size()));

        if(den == 0) return 0;

        return num / den;
    }
}
