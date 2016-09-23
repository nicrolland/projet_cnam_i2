package Serverd;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GestionClient implements Runnable {

    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;
    private String nomclient;

    public GestionClient(Socket pSock) {
        sock = pSock;
    }

    //Le traitement lancé dans un thread séparé
    public void run() {
        System.err.println(getCurrentTimeStamp() + " GestionClient (Th:"
                + Thread.currentThread().getId() + ") > Start");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while (!sock.isClosed()) {
            try {
                writer = new PrintWriter(sock.getOutputStream());
                reader = new BufferedInputStream(sock.getInputStream());

                //On attend la demande du client            
                String demande = read();
                InetSocketAddress remote = (InetSocketAddress) sock.getRemoteSocketAddress();

                String debug = "";
                debug = getCurrentTimeStamp() + " GestionClient (Th:"
                        + Thread.currentThread().getId() + ") > " + demande + " reçu de : "
                        + remote.getAddress().getHostAddress()
                        + ":" + remote.getPort();
                System.err.println(debug);
                String toSend = demande + " bien recu de "
                        + remote.getAddress().getHostAddress() + ":" + remote.getPort();

                /*
                 * 
                 *
                 * TRAITEMENT DES DONNEES RECUES
                 *
                 *
                 *
                 */
                if (demande.equals("CLOSE")) {
                    closeConnexion = true;
                }

                // envoi reponse
                writer.write(toSend);
                writer.flush();

                if (closeConnexion) {
                    System.err.println(getCurrentTimeStamp() + " GestionClient (Th:"
                            + Thread.currentThread().getId() + ") > COMMANDE CLOSE DETECTEE, ON FERME ! ");
                    writer = null;
                    reader = null;
                    sock.close();
                    break;
                }
            } catch (SocketException e) {
                System.err.println(getCurrentTimeStamp() + " GestionClient (Th:"
                        + Thread.currentThread().getId() + ") > LA CONNEXION A ETE INTERROMPUE ! ");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //La méthode que nous utilisons pour lire les réponses
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
