

public class Game {
    public static int u=0;
    public Game(Players player1, Players player2){
        u++;
        Players activPlayer=player1;
        Players passivPlayer=player2;
        activPlayer.writer.println("Hi!"+u);
        activPlayer.writer.flush();
        passivPlayer.writer.println("Hi!"+u);
        passivPlayer.writer.flush();

    }
    public int prosto(){
        return 1;
    }
}
