import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player {

    String name;
    Socket socket= new Socket("127.0.0.1", 8200);
    PrintWriter writer = new PrintWriter(socket.getOutputStream());
    Scanner read = new Scanner(socket.getInputStream());
    Scanner scan = new Scanner(System.in);
    int[] arrOfPlayer;

    public Player(String name) throws IOException {
    }

    public Player(String name, Socket socket, PrintWriter writer, Scanner read, Scanner scan) throws IOException {
        this.name = name;
        this.socket = socket;
        this.writer = writer;
        this.read = read;
        this.scan = scan;
    }

    public Player() throws IOException {
    }
    public static void main(String[] args) throws IOException {
        Player player = new Player();
        System.out.println("Ваше имя: ");
        String name = player.scan.nextLine();// считывает с консоли
        player.writer.println(name); //1.отправляет серверу
        player.writer.flush();
//2. считывает и выводит в консоле
        System.out.println(player.read.nextLine());
//3. считывает текст и выводит в консоль
        System.out.println(player.read.nextLine());


        for (int i = 0; i < 4; i++) {
            int x = player.scan.nextInt();//читает с консоли число
            player.writer.println(x);//4. отправляет серверу
            player.writer.flush();
            System.out.println(" Вы ввели число " + player.read.nextLine());//выводит в консоль
        }

    }
}
