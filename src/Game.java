

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    int[] arOfFirstPlayer;
    int[] arOfsecondPlayer;
    public ArrayList<Scanner> scans = new ArrayList<>();
    public ArrayList<PrintWriter> writer = new ArrayList<>();

    Game(ArrayList<String> players, ArrayList<int[]> playersArr, Socket socket) throws IOException {
        Player firstPlayer = new Player(players.get(0),
                socket = new Socket("127.0.0.1", 8200), new PrintWriter(socket.getOutputStream()),
                new Scanner(socket.getInputStream()), new Scanner(System.in));

        Player secondPlayer = new Player(players.get(1), socket = new Socket("127.0.0.1", 8200), new PrintWriter(socket.getOutputStream()),
                new Scanner(socket.getInputStream()), new Scanner(System.in));


        arOfFirstPlayer = playersArr.get(0);
        arOfsecondPlayer = playersArr.get(1);

        System.out.println("Массивы  игроков");
        for (int i = 0; i < playersArr.size(); i++) {
            System.out.println("массив " + (i + 1) + "-го игрока");
            System.out.println(Arrays.toString(playersArr.get(i)));
        }
        System.out.println();

        System.out.println("Начинаем игру");
        System.out.println("Играют " + firstPlayer.name + " и " + secondPlayer.name);

        firstPlayer.writer.println("1111");
        firstPlayer.writer.flush();
        while (!end(arOfFirstPlayer)) {

        }


    }

    public boolean end(int[] arr) {
        int summ = 0;
        for (int i = 0; i < 10; i++) {
            summ = summ + arr[i];
        }
        if (summ == 0) {
            return false;
        }
        return true;
    }

    public String check(int[] arr, int x) {
        for (int i = 0; i < 10; i++) {
            if (arr[x] == 1) {
                arr[x] = 0;
                return "Попал";
            } else return "Не попал";
        }
        return "Не попал";
    }
}
