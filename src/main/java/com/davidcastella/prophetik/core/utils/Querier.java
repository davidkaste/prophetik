package com.davidcastella.prophetik.core.utils;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.SelectorImpl;
import org.apache.log4j.lf5.LogLevel;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by davidkaste on 10/7/15.
 */
public class Querier {

    private static final Logger log = Logger.getLogger(Querier.class.getName());

    public static Map<String, Double> getUserProductsRatings(Model graph, String userResource) {
        String key;
        Double value;
        Map<String, Double> result = new HashMap<String, Double>();
        Resource user = graph.getResource(userResource);
        Query q = QueryFactory.create(Queries.getUserProductsRating(userResource));
        log.info("Setted query: " + q.toString());
        QueryExecution qe = QueryExecutionFactory.create(q, graph);
        ResultSet rs = qe.execSelect();
        QuerySolution qs;
        while (rs.hasNext()) {
            qs = rs.next();
            key = qs.getResource("product").toString();
            value = qs.getLiteral("ratingValue").getDouble();
            result.put(key, value);
        }

        return result;
    }

    public static List<String> getAllUsers(Model graph, String userResource, String userClassUri) {
        List<String> result = new ArrayList<String>();
        Query q = QueryFactory.create(Queries.getAllUsers(userResource, userClassUri));
        QueryExecution qe = QueryExecutionFactory.create(q, graph);
        ResultSet rs = qe.execSelect();
        QuerySolution qs;
        String user;
        while(rs.hasNext()) {
            qs = rs.next();
            user = qs.getResource("user").toString();
            result.add(user);
        }
        return result;
    }
}
