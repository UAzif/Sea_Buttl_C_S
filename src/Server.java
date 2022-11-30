

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
            String name = read.nextLine(); //1.считывает переданное имя(name)
            Scanner scan = new Scanner(System.in);
            writer.println("Введите числа");
            int[] mass = new int[10];
            for (int i = 0; i < 4; i++) {
                int x = read.nextInt();//4.считывает число
                writer.println(x); // отправляет обратно
                writer.flush();
                mass[x] = 1;
            }

            Player player = new Player(name, input, writer,read,scan, mass);
            playersList.add(player);

            System.out.println("Подключены " + playersList.size() + " игроков ");
            int myNumb = playersList.size();//дает номер по порядку ==длине массива

            for (int i = 0; i < playersList.size(); i++) {
                System.out.println(playersList.get(i).name);
            }

            if (playersList.size() == 1) {
                writer.println(name + " вы зарегистрированы ваш № " + playersList.size() + " ждем пока");
                writer.flush();
            } else if (playersList.size() > 1) {
                writer.println(name + " вы зарегистрированы ваш № " + playersList.size());
                writer.flush();
                ArrayList<Player> temp = new ArrayList<>();
                temp.add(playersList.get(0));
                temp.add(playersList.get(1));
                playersList.remove(1);
                playersList.remove(0);
                System.out.println("размер " + playersList.size());
                System.out.println("Передаю игре " + temp.get(0).name + " " + temp.get(1).name);
                new Game(temp.get(0), temp.get(1));
                System.out.println("Передал игре " + temp.get(0).name + " " + temp.get(1).name);
            }


        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

