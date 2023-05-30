package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class Server {
    private UserController UserController;
    private ProductController ProductController;
    private OrdersController OrdersController;

    public Server(){
        Database conn = new Database();
        UserController = new UserController(conn);
        ProductController = new ProductController(conn);
        OrdersController = new OrdersController(conn);
    }

    public void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 4116), 0);
        httpServer.createContext("/", new Handler());
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }

    private class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String[] path = exchange.getRequestURI().getPath().split("/");
            String query = exchange.getRequestURI().getQuery();
            String response = "";
            if(method.equals("GET")){
                if(path[1].equals("users")){
                    response = UserController.getUsersMethod(path, query);
                }else if(path[1].equals("products")){
                    if(path.length==3) {
                        response = ProductController.getProducts(Integer.parseInt(path[2]));
                    }else {
                        response = ProductController.getProducts(0);
                    }
                }else if(path[1].equals("orders")){
                    response = OrdersController.getOrders(path[2]);
                }
            }else if(method.equals("DELETE")){
                if(path[1].equals("users")){
                    response = UserController.deleteMethod(Integer.parseInt(path[2]));
                }else if(path[1].equals("products")){
                    response = ProductController.deleteMethod(path[2]);
                }else if(path[1].equals("orders")){
                    response = OrdersController.deleteMethod(path[2]);
                }
            }else if(method.equals("POST")){
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = UserController.postMethod(requestBodyJson);
                }else if(path[1].equals("products")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = ProductController.postMethod(requestBodyJson);
                }else if(path[1].equals("orders")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = OrdersController.postMethod(requestBodyJson);
                }
            }else if(method.equals("PUT")){
                if(path[1].equals("users")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = UserController.putMethod(path[2], requestBodyJson);
                }else if(path[1].equals("products")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = ProductController.putMethod(path[2],requestBodyJson);
                }else if(path[1].equals("orders")){
                    JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                    response = OrdersController.putMethod(path[2],requestBodyJson);
                }
            }
            OutputStream outputStream = exchange.getResponseBody();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        private JSONObject parseRequestBody(InputStream requestBody) throws IOException {
            byte[] requestBodyBytes = requestBody.readAllBytes();
            String requestBodyString = new String(requestBodyBytes);
            return new JSONObject(requestBodyString);
        }
    }
}