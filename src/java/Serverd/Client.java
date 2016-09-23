package Serverd;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

    private Socket connexion = null;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private String name;

    public Client(String host, int port, int numClient) {
        name = "Client" + numClient;
        try {
            connexion = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            writer = new PrintWriter(connexion.getOutputStream(), true);
            reader = new BufferedInputStream(connexion.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
            writer.write("NAME:" + name);
            writer.flush();
            for (int i = 0; i < 5; i++) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //On envoie la commande au serveur
            String commande = "OBJET" + (i + 1);
            writer.write(commande);
            writer.flush();

            System.out.println(getCurrentTimeStamp() + " " + name + " > " + commande
                    + " envoyé au serveur (Port:" + connexion.getPort() + ")");
            //On attend la réponse
            String response;
            try {
                response = read();
                System.out.println(getCurrentTimeStamp() + " " + name + " > Réponse reçue : " + response);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        writer.write("CLOSE");
        writer.flush();
        writer.close();
    }

    //Méthode pour lire les réponses du serveur
    private String read() throws IOException {
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
