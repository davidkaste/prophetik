package com.davidcastella.prophetik.core.engine;

import com.davidcastella.prophetik.core.userdistance.UserDistance;
import com.davidcastella.prophetik.core.utils.Querier;
import com.hp.hpl.jena.rdf.model.Model;

import java.util.*;

/**
 * Created by davidkaste on 21/7/15.
 */
public class DefaultRecommenderEngine implements RecommenderEngine {

    public Map<String, Double> getRecommendations(Model graph, String userResource, String userClassUri, UserDistance simMethod) {
        Map<String, Double> totals = new HashMap<String, Double>();
        Map<String, Double> simSums = new HashMap<String, Double>();
        Map<String, Double> user1Ratings, user2Ratings;
        Map<Double, String> rankings = new HashMap<Double, String>();

        List<String> userList = Querier.getAllUsers(graph, userResource, userClassUri);
        List<Double> rankingsIndex = null;

        Double sim;

        user1Ratings = Querier.getUserProductsRatings(graph, userResource);

        for(String user : userList) {
            sim = simMethod.getUserDistance(graph, userResource, user);

            user2Ratings = Querier.getUserProductsRatings(graph, user);

            if(sim > 0) {

                for(String product : user2Ratings.keySet()) {
                    if(!user1Ratings.containsKey(product) || user1Ratings.get(product) == 0.0) {
                        if(!totals.containsKey(product)) {
                            totals.put(product, 0.0);
                            simSums.put(product, 0.0);
                        }
                        totals.put(product, totals.get(product) + (user2Ratings.get(product) * sim));
                        simSums.put(product, simSums.get(product) + sim);
                    }
                }
            }
        }

        return generateRecommendation(totals, simSums, rankings);
    }

    private Map<String, Double> generateRecommendation(Map<String, Double> totals, Map<String, Double> simSums, Map<Double, String> rankings) {
        Map<String, Double> rankReturn = new HashMap<String, Double>();
        List<Double> rankingsIndex;
        
        Double rank = 0.0;
        Double totalsValue = 0.0;
        Double simSumsValue = 0.0;
        
        for(String key : totals.keySet()) {
            totalsValue = totals.get(key);
            simSumsValue = simSums.get(key);
            rank = totalsValue / simSumsValue;
            rankings.put(rank, key);
        }
        
        rankingsIndex = new ArrayList<Double>(rankings.keySet());
        Collections.sort(rankingsIndex);
        Collections.reverse(rankingsIndex);
        
        for(Double key : rankingsIndex) {
            rankReturn.put(rankings.get(key), key);
        }
        
        return rankReturn;
    }
}
