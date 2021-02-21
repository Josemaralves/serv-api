package com.josegabs.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

public class MongoUtils {

    @Inject MongoClient mongoClient;

    @ApplicationScoped
    public MongoCollection servidorCollection(){
        return mongoClient.getDatabase("testeFinal").getCollection("servidor");
    }

}
