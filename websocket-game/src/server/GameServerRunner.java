package server;


import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;


import java.util.Scanner;

public class GameServerRunner {
    public static void main(String[] args) {
        Server server = new Server("localhost",80,"/ws",null, GameServer.class);

        try{
            server.start();
            System.out.println("Chat server started on ws://localhost:8080/ws");
            System.out.println("Press Enter to stop server...");
            new Scanner(System.in).nextLine();
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }finally {
            server.stop();
            System.out.println("Chat server stopped.");
        }
    }
}
