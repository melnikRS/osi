package ru.melnik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static ru.melnik.Server.LOCALHOST_PORT;

public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", LOCALHOST_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String name = InetAddress.getLocalHost().getHostName();
           writer.println(name);
            System.out.println(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
