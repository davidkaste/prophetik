package com.davidcastella.prophetik.core.userdistance;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Created by david on 27/04/15.
 */
public interface UserDistance {
    double getUserDistance(Model graph, String user1Uri, String user2Uri);
}
