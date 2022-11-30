import java.io.IOException;

public class Game {
    Game(Player player1, Player player2) throws IOException {

        player1.writer.println(player1.name + " Начинаем игру");
        player1.writer.flush();//->4
        player2.writer.println(player2.name + "  Начинаем игру");
        player2.writer.flush();//->4

        int a = (int) (Math.random() * 2 + 4) - 3;
        int y;
        if (a == 1) {
            player1.writer.println("Первый ход за вами. Введите число");
            player1.writer.flush();//->5

            player2.writer.println("Первый ход за ним. Ждем результат ");
            player2.writer.flush();//->5}
            y = player1.read.nextInt();//==>6
            player2.writer.println("Первый игрок ввел число ");// + y);
            player2.writer.flush();
            player1.writer.println("Первый игрок ввел число ");// + y);
            player1.writer.flush();
        }
        else {
            player2.writer.println("Первый ход за вами. Введите число");
            player2.writer.flush();//->5

            player1.writer.println("Первый ход за ним. Ждем результат ");
            player1.writer.flush();//->5
            y = player2.read.nextInt();//==>6
            player2.writer.println("Первый игрок ввел число ");// + y);
            player2.writer.flush();
            player1.writer.println("Первый игрок ввел число ");// + y);
            player1.writer.flush();
        }
//        player2.writer.println("Первый игрок ввел число ");// + y);
//        player2.writer.flush();
//        player1.writer.println("Первый игрок ввел число ");// + y);
//        player1.writer.flush();

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
            arr[x] = 0;
            return true;
        } else return false;
    }
}

