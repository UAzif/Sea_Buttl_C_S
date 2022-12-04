

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Players {
    String name;
    Socket socket;
    PrintWriter writer;
    Scanner read;
    Scanner scan;
    int[] arr;

    public Players(String name, Socket socket, PrintWriter writer, Scanner read, Scanner scan, int[] arr) {
        this.name = name;
        this.socket = socket;
        this.writer = writer;
        this.read = read;
        this.scan = scan;
        this.arr = arr;
    }

    public Players(Socket socket, PrintWriter writer, Scanner read, Scanner scan, int[] arr) {
        this.socket = socket;
        this.writer = writer;
        this.read = read;
        this.scan = scan;

    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8500);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        Scanner read = new Scanner(socket.getInputStream());
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[10];
        Players player = new Players(socket, writer, read, scan, arr);
        System.out.println("Имя");
        String name = player.scan.nextLine();
        player.writer.println(name);
        player.writer.flush();
        System.out.println(player.read.nextLine());//1
        System.out.println(player.read.nextLine());//2
        System.out.println(player.read.nextLine());//
        System.out.println(player.read.nextLine());//

    }
}

