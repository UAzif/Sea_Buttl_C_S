
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8200);
        while (true) {
            Socket input = serverSocket.accept();
            new Thread(new MyServer(input)).start();
        }
    }
}

class MyServer implements Runnable {
    Socket input;
    Player player;
    public static ArrayList<int[]> playersArr = new ArrayList();
    public static ArrayList<String> players = new ArrayList<>();
    int player1;
    int player2;
//    public ArrayList<Scanner> scans= new ArrayList<>();
//    public ArrayList<PrintWriter> writer= new ArrayList<>();

    public MyServer(Socket input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            Scanner read = new Scanner(input.getInputStream());
            PrintWriter writer = new PrintWriter(input.getOutputStream(), true);
            String name = read.nextLine(); //1.считывает переданное имя(name)
            players.add(name);//добавляет в массив имен
            System.out.println("Подключены " + players.size() + " игроков ");
            int myNumb = players.size();//дает номер по порядку ==длине массива
//2.передает номер игрока
            //         if (myNumb == 1) {
            writer.println(name + " вы зарегистрированы ваш № " + myNumb);
            writer.flush();
//            } else {
//                writer.println(name + " вы зарегистрированы ваш № " + myNumb+"\nВведите числа" );
//                writer.flush();
//            }
//3. передает текст
            writer.println("Введите числа");

            int[] mass = new int[10];

            for (int i = 0; i < 4; i++) {
                int x = read.nextInt();//4.считывает число
                writer.println(x); // отправляет обратно
                writer.flush();
                mass[x] = 1;
            }

            System.out.println("массив " + name);
            for (int i = 0; i < 10; i++) {
                System.out.print(mass[i] + " ");
            }
            System.out.println();
            playersArr.add(mass);

//            System.out.println("Массивы  игроков");
//            for (int i = 0; i < playersArr.size(); i++) {
//                System.out.println("массив " + i + "-го игрока");
//                System.out.println(Arrays.toString(playersArr.get(i)));
//            }
//            System.out.println();
//            System.out.println("Список игроков");
//            for (int i = 0; i < players.size(); i++) {
//                System.out.println(players.get(i));//+" размер списка"+players.size());
//            }
//            System.out.println("И их массивы");
//            for (int i = 0; i < playersArr.size(); i++) {
//                System.out.println("массив " + name + "-го игрока");
//                System.out.println(Arrays.toString(playersArr.get(i)));
//            }

            //  playersArr.add(mass);
            System.out.println(" размер "+playersArr.size());

            if (playersArr.size()==2) {
                new Game(players, playersArr);
                players.remove(0);
                players.remove(0);
                playersArr.remove(0);
                playersArr.remove(0);
            }
            writer.println("ooo");//5. отправляет
            writer.flush();

            System.out.println("размер теперь  "+playersArr.size());

            System.out.println(read.nextLine());

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

