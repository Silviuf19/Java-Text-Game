import java.util.TreeSet;

public class Information {
    Credentials credentials;
    TreeSet<String> favouriteGames;
    String name;
    String country;

    public void addFavouriteGame(String game){
        favouriteGames.add(game);
    }

}
