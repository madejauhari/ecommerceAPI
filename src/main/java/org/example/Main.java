package org.example;

import com.sun.net.httpserver.HttpExchange;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        Server httpConnection = new Server();
        try {
            httpConnection.startServer();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}