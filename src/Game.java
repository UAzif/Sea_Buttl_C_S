
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    int[] arOfFirstPlayer;
    int[] arOfsecondPlayer;

    Game(ArrayList<String> players, ArrayList<int[]> playersArr) throws IOException {
        Player firstPlayer = new Player();
        firstPlayer.name = players.get(0);
        Player secondPlayer = new Player();
        secondPlayer.name = players.get(1);

        arOfFirstPlayer = playersArr.get(0);
        arOfsecondPlayer = playersArr.get(1);

        System.out.println("Массивы  игроков");
        for (int i = 0; i < playersArr.size(); i++) {
            System.out.println("массив "+(i+1)+"-го игрока");
            System.out.println(Arrays.toString(playersArr.get(i)));
        }
        System.out.println();

        System.out.println("Начинаем игру");
        System.out.println("Играют " + firstPlayer.name + " и " + secondPlayer.name);

//        firstPlayer.writer.println("ooo");
//        firstPlayer.writer.flush();


    }
}
