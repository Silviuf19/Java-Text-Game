import org.json.*;
import java.util.TreeSet;

public class InformationBuilder {
    public Information buildInformation(JSONObject jsonAccount){
        Information information = new Information();
        information.credentials = new Credentials();
        information.favouriteGames = new TreeSet<>();

        JSONObject jsonCredentials = jsonAccount.getJSONObject("credentials");
        information.credentials.setEmail(jsonCredentials.getString("email"));
        information.credentials.setPassword(jsonCredentials.getString("password"));

        JSONArray jsonFavouriteGames = jsonAccount.getJSONArray("favorite_games");
        for(int i = 0; i < jsonFavouriteGames.length(); i++){
            information.addFavouriteGame(jsonFavouriteGames.getString(i));
        }

        information.name = jsonAccount.getString("name");
        information.country = jsonAccount.getString("country");

        return information;
    }
}
