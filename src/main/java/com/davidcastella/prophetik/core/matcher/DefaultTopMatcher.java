package com.davidcastella.prophetik.core.matcher;

import com.davidcastella.prophetik.core.userdistance.PearsonCorrelation;
import com.davidcastella.prophetik.core.userdistance.UserDistance;
import com.davidcastella.prophetik.core.utils.Querier;
import com.hp.hpl.jena.rdf.model.Model;

import java.util.*;

/**
 * Created by David Castellà on 14/06/2015.
 */
public class DefaultTopMatcher implements TopMatcher {
    private Model graph;
    private String userResourceUri;
    private int numberResults;
    private UserDistance distanceMethod;
    private String userClassUri;

    public DefaultTopMatcher(Model graph, String userResourceUri, String userClassUri, int numberResults, UserDistance distanceMethod) {
        this.graph = graph;
        this.userResourceUri = userResourceUri;
        this.numberResults = numberResults;
        this.distanceMethod = distanceMethod;
        this.userClassUri = userClassUri;
    }

    public DefaultTopMatcher(Model graph, String userResourceUri, String userClassUri) {
        this(graph, userResourceUri, userClassUri, 5, new PearsonCorrelation());
    }

    public DefaultTopMatcher(Model graph, String userResourceUri, String userClassUri, int numberResults) {
        this(graph, userResourceUri, userClassUri, numberResults, new PearsonCorrelation());
    }

    public DefaultTopMatcher(Model graph, String userResourceUri, String userClassUri, UserDistance distanceMethod) {
        this(graph, userResourceUri, userClassUri, 5, distanceMethod);
    }

    public Map<String, Double> getTopMatches() {
        List<String> allUsers = Querier.getAllUsers(graph, userResourceUri, userClassUri);
        Map<Double, String> similarity = new HashMap<Double, String>();
        List<Double> similarityIndex = new ArrayList<Double>();
        Double value;
        for(String user : allUsers) {
            value = distanceMethod.getUserDistance(graph, userResourceUri, user);
            similarity.put(value, user);
            similarityIndex.add(value);
        }

        Collections.sort(similarityIndex);
        Collections.reverse(similarityIndex);

        int counter = 0;
        String key;
        Map<String, Double> returnMap = new HashMap<String, Double>();
        while(counter < numberResults) {
            key = similarity.get(similarityIndex.get(counter));
            value = similarityIndex.get(counter);
            returnMap.put(key, value);
            counter++;
        }

        return returnMap;

    }

}
