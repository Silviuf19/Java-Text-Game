public class GameDrive {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.run();
        game.selectGameMode();
    }
}
