

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8300);
        while (true) {
            Socket input = serverSocket.accept();
            new Thread(new MyServer(input)).start();
        }
    }
}

class MyServer implements Runnable {
    Socket input;
    public static ArrayList<Player> playersList = new ArrayList<>();

    public MyServer(Socket input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            Scanner read = new Scanner(input.getInputStream());
            PrintWriter writer = new PrintWriter(input.getOutputStream(), true);
            Scanner scan = new Scanner(System.in);
            int myNumb = playersList.size() + 1;
            String name = read.nextLine(); //1.считывает переданное имя(name)
            writer.println("Введите числа");//2 отправляет
            int[] mass = new int[10];
            for (int i = 0; i < 1; i++) {
                int x = read.nextInt();
//                writer.println(x);
//                writer.flush();
                mass[x] = 1;
            }

            Player player = new Player(name, input, writer, read, scan, mass);
            playersList.add(player);

            System.out.println("Подключены " + playersList.size() + " игроков ");

            for (Player value : playersList) {
                System.out.println(value.name);
            }

            while (playersList.size() == 1) {
                writer.println(name + " вы зарегистрированы ваш № " + playersList.size() + " ждем пока");
                writer.flush();
                Thread.sleep(1000);
                if (playersList.size() ==2) {
                    writer.println(name + " вы зарегистрированы ваш № " + playersList.size());
                    writer.flush();//->3

                    playersList.get(0).writer.println(playersList.get(0).name + "1-- Начинаем игру");
                    playersList.get(0).writer.flush();//->4
                    playersList.get(1).writer.println(playersList.get(1).name + " 1--Начинаем игру");
                    playersList.get(1).writer.flush();//->4
                    playersList.get(0).writer.println("2--Первый ход за вами. Введите число");
                    playersList.get(0).writer.flush();//-5
                    playersList.get(1).writer.println("2--Первый ход за ним. Ждем результат ");
                    playersList.get(1).writer.flush();//->5
                    int z = playersList.get(1).read.nextInt();
                    System.out.println("Получил " + z + "от " + (myNumb - 1) + " игрока");
                    Game game = new Game();
                    boolean b = game.poPal(playersList, playersList.get(myNumb - 1), z);
                    System.out.println("Передал " + z);
                    System.out.println("Результат " + b);


                    playersList.get(0).writer.println("3--Первый ход сделан");
                    playersList.get(0).writer.flush();//-5
                    playersList.get(1).writer.println("3--Первый ход сделан ");
                    playersList.get(1).writer.flush();//->5
                    z = playersList.get(1).read.nextInt();
                    b = game.poPal(playersList, playersList.get(myNumb - 1), z);
                    //   game.poPal(temp, temp.get(myNumb - 1), z);
                    playersList.get(0).writer.println("4--Еще один ход сделан");
                    playersList.get(0).writer.flush();//-5
                    playersList.get(1).writer.println("4---Еще один ход сделан ");
                    playersList.get(1).writer.flush();//->5

                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

