package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //Рекурсивный метод получения числа Фибоначчи
    private static long fiboRec(long n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fiboRec(n - 1) + fiboRec(n - 2);
        }
    }

    //Линейный метод получения числа Фибоначчи
    private static long fibo(int n) {
        long a = 0;
        long b = 1;
        for (long i = 2; i <= n; ++i) {
            long next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket servSocket = new ServerSocket(23444);

        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        break;
                    }
                    int numb = Integer.parseInt (line);
                    long fibo = fiboRec(numb);
                    out.println("число Фибоначчи: " + fibo);
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

}
