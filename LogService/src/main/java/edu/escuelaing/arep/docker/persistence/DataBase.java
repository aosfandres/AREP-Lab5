package edu.escuelaing.arep.docker.persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import edu.escuelaing.arep.docker.model.*;

public class DataBase {

    
    private static final BasicDBObject filterByDate = new BasicDBObject("fecha", -1);

    public DataBase() {
    }

    /**
     * Conecta con la DB y le añade un dato
     * 
     * @param cadena cadena a añadir
     */
    public void addMensaje(String cadena) {

        ArrayList<Mensaje> data = new ArrayList<Mensaje>();

        data.add(new Mensaje(cadena, new Date()));
            MongoClient mongoClient = new MongoClient(
                    new MongoClientURI("mongodb://ec2-54-145-137-246.compute-1.amazonaws.com:27017"));
            MongoDatabase database = mongoClient.getDatabase("andresdb");
            MongoCollection<Document> collection = database.getCollection("logs");
            Document document = new Document();
            document.put("mensaje", data.get(0).getMensaje());
            document.put("fecha", data.get(0).getFecha());
            collection.insertOne(document);
            System.out.println("normal");
            mongoClient.close();
    }

    /**
     * Conecta con la DB y obtiene todos los datos de ella
     * 
     * @return list con los datos almacenados
     */
    public List<Mensaje> getAllMensajes() {
        List<Mensaje> messages = new ArrayList<>();
        try {
            
            MongoClient mongoClient = new MongoClient(
                new MongoClientURI("mongodb://ec2-54-145-137-246.compute-1.amazonaws.com:27017"));
            MongoDatabase database = mongoClient.getDatabase("andresdb");
            MongoCollection<Document> collection = database.getCollection("logs");
            FindIterable fit = collection.find().sort(filterByDate).limit(10);
            ArrayList<Document> docs = new ArrayList<>();
            fit.into(docs);
            String message;
            Date date = null;
            for (Document document : docs) {
                message = (String) document.get("mensaje");
                date = (Date) document.get("fecha");
                messages.add(new Mensaje(message, date));
            }
            System.out.println("Ultimos 10 logs de mongo: " + LocalDateTime.now());
            return messages;

        } catch (Exception ex) {
            System.out.println("Exception al conectar al server de Mongo: " + ex.getMessage());
        }
        return messages;
    }

}