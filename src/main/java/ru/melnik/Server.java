package ru.melnik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

public class Server {
    public static final int LOCALHOST_PORT = 80;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(LOCALHOST_PORT)) {
            out.println("Сервер запущен");
            while (true) {
                try (Socket client = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))
                ) {
                    String line = reader.readLine();
                    out.println(String.format("Hi %s, your port is %d", line, client.getPort()));
                    writer.println(client.getPort());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}