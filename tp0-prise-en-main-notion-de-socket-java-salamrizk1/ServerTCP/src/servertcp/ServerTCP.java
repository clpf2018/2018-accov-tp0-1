/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Salam Rizk
 */
public class ServerTCP {

    private static BufferedReader getInput(Socket p) throws IOException {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    private static PrintWriter getoutput(Socket p) throws IOException {
        return new PrintWriter(new OutputStreamWriter(p.getOutputStream()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket l = new ServerSocket(2000);
        System.out.println(l.getLocalSocketAddress());
        while (true) {
            //try-with-resource
            try (Socket serviceSocket = l.accept()) {
                System.out.println(serviceSocket.getRemoteSocketAddress());
                BufferedReader ir = getInput(serviceSocket);
                PrintWriter reply = getoutput(serviceSocket);
                String line;
                System.out.println("Avant boucle");
                while ((line = ir.readLine()) != null) {
                    System.out.println("Dans boucle");
                    System.out.printf("je répond ping %s\n", line);
                    reply.printf("je répond ping %s\n", line);
                    reply.flush();
                }
            }
        }
    }

}