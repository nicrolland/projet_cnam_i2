package Serverd;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 2345;

        SocketsServer ts = new SocketsServer(host, port);
        ts.open();
    
        String heure = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
        System.out.println(heure + " Main > Serveur initialisé.");

        for (int i = 0; i < 1; i++) {
            Thread t = new Thread(new Client(host, port, i));
            t.start();
//            heure = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
//            System.out.println(heure + " Main > Client " + i + " Start ");
        }
    }
}
