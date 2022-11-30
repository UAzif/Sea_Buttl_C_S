import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player {

    String name;
    Socket socket;
    PrintWriter writer;
    Scanner read;
    Scanner scan;
    int[] arr;

    public Player(String name, Socket socket, PrintWriter writer, Scanner read, Scanner scan, int [] arr) {
        this.name = name;
        this.socket = socket;
        this.writer = writer;
        this.read = read;
        this.scan = scan;
        this.arr=arr;
    }

    public Player(Socket socket, PrintWriter writer, Scanner read, Scanner scan, int [] arr) {
        this.socket = socket;
        this.writer = writer;
        this.read = read;
        this.scan = scan;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8300);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        Scanner read = new Scanner(socket.getInputStream());
        Scanner scan = new Scanner(System.in);
        int [] arr = new int[10];
        Player player = new Player(socket, writer, read, scan,arr);
        System.out.println("Ваше имя: ");
        String name = player.scan.nextLine();// считывает с консоли
        player.writer.println(name); //1.отправляет серверу
        player.writer.flush();

        System.out.println(player.read.nextLine());//2 получает

        for (int i = 0; i < 1; i++) {
            int x = player.scan.nextInt();//читает с консоли число
            if ((x>=0)&&(x<10)) {
                player.writer.println(x);//4. отправляет серверу
                player.writer.flush();
                System.out.println(" Вы ввели " + (i + 1) + " число оно = " + player.read.nextLine());//выводит в консоль
            }else {
                i--;
                System.out.println("Не правильно!!! Повторите ввод");
            }
        }

        System.out.println("3 "+player.read.nextLine());//==3 от сервера
        System.out.println("4 "+player.read.nextLine());//==4 от игры

        System.out.println("5 "+player.read.nextLine());//==5

        int x= player.scan.nextInt();//считывает число
        player.writer.println(x);
        player.writer.flush();//отправляет ->6

        System.out.println("6 "+player.read.nextLine());
        System.out.println("7 "+player.read.nextLine());
        System.out.println("8 "+player.read.nextLine());
        System.out.println("9 "+player.read.nextLine());

    }
}
