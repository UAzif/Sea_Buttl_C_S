

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
            int myNumb = playersList.size()+1;
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

            System.out.println("Подключены " + myNumb + " игроков ");

            for (Player value : playersList) {
                System.out.println(value.name);
            }

            if (playersList.size() == 1) {
                writer.println(name + " вы зарегистрированы ваш № " + myNumb + " ждем пока");
                writer.flush();
            } else if (playersList.size() > 1) {
                writer.println(name + " вы зарегистрированы ваш № " + myNumb);
                writer.flush();//->3
                ArrayList<Player> temp = new ArrayList<>();
                temp.add(playersList.get(0));
                temp.add(playersList.get(1));
                playersList.remove(1);
                playersList.remove(0);
                temp.get(0).writer.println(temp.get(0).name + "1-- Начинаем игру");
                temp.get(0).writer.flush();//->4
                temp.get(1).writer.println(temp.get(1).name + " 1--Начинаем игру");
                temp.get(1).writer.flush();//->4
                temp.get(0).writer.println("2--Первый ход за вами. Введите число");
                temp.get(0).writer.flush();//-5
                temp.get(1).writer.println("2--Первый ход за ним. Ждем результат ");
                temp.get(1).writer.flush();//->5

                Game game= new Game();
                for (int i= 0; i<2;i++) {
                    int z = temp.get(0).read.nextInt();
                    System.out.println("Получил " + z + "от " + (myNumb-1)+" игрока");
                    boolean b = game.poPal(temp, temp.get(myNumb - 1), z);
                    //   game.poPal(temp, temp.get(myNumb - 1), z);
                    z=0;
                    //  System.out.println("Передал " + z);
                    //   System.out.println("Результат " + b);
                    temp.get(0).writer.println("3--Первый ход сделан");
                    temp.get(0).writer.flush();//-5
                    temp.get(1).writer.println("3--Первый ход сделан ");
                    temp.get(1).writer.flush();//->5
                    z=  temp.get(1).read.nextInt();
                    b = game.poPal(temp, temp.get(myNumb - 1), z);
                    //   game.poPal(temp, temp.get(myNumb - 1), z);
                    temp.get(0).writer.println("4--Еще один ход сделан");
                    temp.get(0).writer.flush();//-5
                    temp.get(1).writer.println("4---Еще один ход сделан ");
                    temp.get(1).writer.flush();//->5
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

