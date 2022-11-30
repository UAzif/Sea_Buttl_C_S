

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    Socket socket = new Socket("127.0.0.1", 8300);
    PrintWriter writer = new PrintWriter(socket.getOutputStream());
    Scanner read = new Scanner(socket.getInputStream());
    Scanner scan = new Scanner(System.in);
    int[] arOfPlayer1 = new int[10];
    int[] arOfPlayer2 = new int[10];
    public ArrayList<Player> playersList = new ArrayList<>();

    Game(Player player1, Player player2) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8300);

        player1.writer.println(player1.name + " Начинаем игру");
        player1.writer.flush();
        player2.writer.println(player2.name + "  Начинаем игру");
        player2.writer.flush();

        //   int a = (int) (Math.random() * 2 + 4) - 3;

        player2.writer.println("Первый ход за вами ");
        player2.writer.flush();
        player2.writer.println("Введите число ");
        player2.writer.flush();

        System.out.println("Хочу принять y");
        int y = player2.scan.nextInt();
        System.out.println(y);


        player2.writer.println("Веден ");
        player2.writer.flush();


    }






    public boolean end(int[] arr) {
        int summ = 0;
        for (int i = 0; i < 10; i++) {
            summ = summ + arr[i];
        }
        return summ != 0;
    }

    public String check(int[] arr, int x) {
        if (arr[x] == 1) {
            arr[x] = 0;
            return "Попал";
        } else return "Не попал";
    }
}

