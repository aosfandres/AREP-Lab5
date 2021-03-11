package edu.escuelaing.arep.docker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class App {

    private static int balanceo = 1;

    public static void main(String args[]) {

        port(getPort());
        get("/data", (req, res) -> inputDataPage(req, res));
        get("/result", (req, res) -> resultsPage(req, res));

    }

    /**
     * metodo que interactua con el usuario
     * @param req
     * @param res
     * @return
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent = "<!DOCTYPE html>" + "<html>" + "<body>" + "<h2>Soy LB para servicio de logs</h2>"
                + "<form action=\"/result\">" + "  Ingrese su nuevo mensaje<br>"
                + "  <input type=\"text\" name=\"mensaje\" >" + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">" + "</form>" + "</body>" + "</html>";
        return pageContent;
    }

    /**
     * metodo que procesa la solicitud, balancea la carga y retorna resultado para el usuario
     * @param req
     * @param res
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private static String resultsPage(Request req, Response res) throws MalformedURLException, IOException {
        String mensaje = req.queryParams("mensaje").replace(" ", "+");
        BufferedReader in = null;
        balanceo = getNext();
        URL logService = null;
        String myURL="http://ec2-54-145-137-246.compute-1.amazonaws.com:";
        String post="/result?mensaje=";
        if (balanceo == 1) {
            logService = new URL( myURL +"35001" + post + mensaje);
        } else if (balanceo == 2) {
            logService = new URL(myURL + "35002" + post + mensaje);
        } else {
            logService = new URL(myURL + "35003" + post + mensaje);
        }
        System.out.println(logService);
        URLConnection con = logService.openConnection();
        in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        in.close();
        stdIn.close();
        res.header("Content-Type", "application/json");
        return line;
    }

    /**
     * metodo que itera el balanceador de carga
     * @return
     */
    private static int getNext() {
        if (balanceo == 3)balanceo = 0;
        balanceo++;
        return balanceo;
    }

    /**
     * puerto establecido
     * @return
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 20000;
    }

}