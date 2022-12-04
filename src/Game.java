

import java.io.IOException;
import java.util.ArrayList;

public class Game {
    Game() throws IOException {

    }

    public boolean poPal(ArrayList<Player> playersList, Player player, int y) {
        Player activePlayer = new Player(player.name, player.socket, player.writer, player.read, player.scan, player.arr);
        Player passivPlayer=new Player(player.name, player.socket, player.writer, player.read, player.scan, player.arr);

        if (activePlayer.equals(playersList.get(0))) {
            activePlayer = playersList.get(0);
            passivPlayer = playersList.get(1);
        } else {
            passivPlayer = playersList.get(0);
            activePlayer = playersList.get(1);
        }
        //если не попал
        if (!bulCheck(passivPlayer.arr, y)) {
            String resCheck = check(passivPlayer.arr, y);
            activePlayer.writer.println("ОТ ИГРЫ---Вы " + resCheck + ". Ход переходит другому");
            activePlayer.writer.flush();

            passivPlayer.writer.println("ОТ ИГРЫ---Он " + resCheck + ". Ход переходит вам ");
            passivPlayer.writer.flush();

            return false;
            //если попал
        } else {
            String resCheck = check(passivPlayer.arr, y);

            passivPlayer.writer.println("ОТ ИГРЫ---Он " + resCheck + ". Ход остается за ним");
            passivPlayer.writer.flush();

            activePlayer.writer.println("ОТ ИГРЫ---Вы " + resCheck + ". Ход остается за вами");
            activePlayer.writer.flush();
            return true;
        }
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

    public boolean bulCheck(int[] arr, int x) {
        if (arr[x] == 1) {
            return true;
        } else return false;
    }


}

