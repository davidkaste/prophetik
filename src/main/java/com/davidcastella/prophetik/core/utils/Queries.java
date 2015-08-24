package com.davidcastella.prophetik.core.utils;

/**
 * Created by davidkaste on 19/7/15.
 */
public class Queries {

    public static String getUserProductsRating(String userResource) {
        return "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "prefix schema: <http://schema.org/>\n" +
                "select ?product ?ratingValue where {\n" +
                "    ?review schema:author <" + userResource + ">.\n" +
                "    ?review schema:itemReviewed ?product.\n" +
                "    ?review schema:reviewRating ?rating.\n" +
                "    ?rating schema:ratingValue ?ratingValue\n" +
                "}";
    }

    public static String getAllUsers(String userResource, String userTypeUri) {
        return "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "prefix schema: <http://schema.org/>\n" +
                "SELECT ?user\n" +
                "WHERE {\n" +
                "  ?user rdf:type <"+ userTypeUri +">\n" +
                "  FILTER(str(?user) !=\"" + userResource + "\")\n" +
                "}";
    }
}
