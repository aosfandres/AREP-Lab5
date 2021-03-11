package edu.escuelaing.arep.docker;


import com.google.gson.*;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

import edu.escuelaing.arep.docker.persistence.*;

public class App {
    private static final Gson gson = new Gson();

    public static void main(String args[]) {

        port(getPort());
        get("/data", (req, res) -> inputDataPage(req, res) );
        get("/result", (req, res) -> resultsPage(req, res));

    }

    /**
     * Recibe los datos solicitados en el servicio REST por el endpoit /Datos
     * @param req requerimientos de la solicitud
     * @param res respuesta al llamado REST
     * @return Formulario de entrada de la cadena
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html> <html> <body>"
                + "<h2>Servicio de log</h2>"
                + "<form action=\"/result\">"
                + "  Ingrese su nuevo mensaje <br>"
                + "  <input type=\"text\" name=\"mensaje\" >"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
    
    /**
     * Realiza la solicitud de conexion a la base de datos 
     * @param req requerimientos de la solicitud
     * @param res respuesta al llamado REST
     * @return Objeto JSON con la consulta a DB
     */
    private static Object resultsPage(Request req, Response res) {
        
        String mensaje = req.queryParams("mensaje");
        DataBase c = new DataBase();
        c.addMensaje(mensaje);
        res.header("Content-Type","application/json");
        Object result;
        try {
            result = gson.toJson(c.getAllMensajes());
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
        
    }

    /**
     * Obtiene el puerto de la variable de entorno
     * @return la variable de entorno o 35001 por defecto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35001;
    }

}