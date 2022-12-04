import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8500);
        while (true) {
            Socket input = serverSocket.accept();
            new Thread(new MyServer(input)).start();
        }
    }
}

class MyServer implements Runnable {
    Socket input;
    public static ArrayList<Players> playersList = new ArrayList<>();

    public MyServer(Socket input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            Scanner read = new Scanner(input.getInputStream());
            PrintWriter writer = new PrintWriter(input.getOutputStream(), true);
            Scanner scan = new Scanner(System.in);
            int[] mass = new int[10];

            while (playersList.size() != 2) {
                String name = read.nextLine(); //1.считывает переданное имя(name)
                Players player = new Players(name, input, writer, read, scan, mass);
                playersList.add(player);
                int myNumb = playersList.size();
                System.out.println(" Стало " + playersList.size() + " игроков");
                playersList.get(playersList.size() - 1).writer.println("Привет "
                        + name + " ! Вы " + myNumb);//1
                playersList.get(playersList.size() - 1).writer.flush();
                Thread.sleep(1000);
            }
            if (playersList.size() == 2) {
                int a = (int) (Math.random() * 2 + 4) - 3;
                System.out.println("Ход за " + a);
                if (a == 1) {
                    playersList.get(0).writer.println("Ваш ход");//2
                    playersList.get(0).writer.flush();//2
                    playersList.get(1).writer.println("Ход за ним");
                    playersList.get(1).writer.flush();
                } else {
                    playersList.get(0).writer.println("Ход за ним");//2
                    playersList.get(0).writer.flush();//2
                    playersList.get(1).writer.println("Ваш ход");
                    playersList.get(1).writer.flush();
                }

                int z = read.nextInt();//получает от 3
                Game logic = new Game();
                boolean x = logic.poPal(z);
                System.out.println(x);

                playersList.get(0).writer.println(x);//2
                playersList.get(0).writer.flush();//2
                playersList.get(1).writer.println(x);//2
                playersList.get(1).writer.flush();//2
                playersList.remove(0);
                playersList.remove(0);
            }

        } catch (IOException|InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}


//            writer.println("Введите числа");//2 отправляет
//            int[] mass = new int[10];
//            for (int i = 0; i < 1; i++) {
//                int x = read.nextInt();
//                mass[x] = 1;





