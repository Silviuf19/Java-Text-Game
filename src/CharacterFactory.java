import org.json.*;

public class CharacterFactory {
    public Character getCharacter(JSONObject jsonCharacter){
        String profession = jsonCharacter.getString("profession");
        int level = Integer.parseInt(jsonCharacter.getString("level"));
        if(profession == null){
            return null;
        }
        if(profession.equalsIgnoreCase("Warrior")){
            return new Warrior(level);

        }else if(profession.equalsIgnoreCase("Mage")){
            return new Mage(level);

        }else if(profession.equalsIgnoreCase("Rogue")){
            return new Rogue(level);
        }

        return null;
    }


}
