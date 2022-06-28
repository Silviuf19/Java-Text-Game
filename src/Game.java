import org.json.*;
import java.io.*;
import org.apache.commons.io.FileUtils;
import java.lang.*;
import java.util.*;


public class Game{
    ArrayList<Account> accounts = new ArrayList<>();
    HashMap<CellEnum,ArrayList<String>> dictionary = new HashMap<>();
    Helper helper = new Helper();

    private static Game instance = new Game();

    private Game(){}

    public static Game getInstance(){
        return instance;
    }

    public void run(){
        //incarcare date din json-uri
        JSONArray jsonAccountArray = null;
        JSONArray jsonStoriesArray = null;
        try {
            //incarcare date din accounts.json
            File file1 = new File("Input\\accounts.json");
            String content1 = FileUtils.readFileToString(file1, "utf-8");
            JSONObject jsonAccountList = new JSONObject(content1);
            jsonAccountArray= jsonAccountList.getJSONArray("accounts");

            //incarcare date din stories.json
            File file2 = new File("Input\\stories.json");
            String content2 = FileUtils.readFileToString(file2, "utf-8");
            JSONObject jsonStories = new JSONObject(content2);
            jsonStoriesArray = jsonStories.getJSONArray("stories");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        //creare lista de conturi

            for (int i = 0; i < Objects.requireNonNull(jsonAccountArray).length(); i++) {
                JSONObject jsonAccount = jsonAccountArray.getJSONObject(i);
                Account account = new Account();
                InformationBuilder infoBuilder = new InformationBuilder();
                account.information = infoBuilder.buildInformation(jsonAccount);

                JSONArray jsonCharactersArray = jsonAccount.getJSONArray("characters");
                CharacterFactory characterFactory = new CharacterFactory();
                ArrayList<Character> charactersList = new ArrayList<>();

                for(int j = 0; j < jsonCharactersArray.length(); j++){
                    JSONObject jsonCharacter = jsonCharactersArray.getJSONObject(j);
                    Character character = characterFactory.getCharacter(jsonCharacter);
                    character.name = jsonCharacter.getString("name");
                    character.level = Integer.parseInt(jsonCharacter.getString("level"));
                    character.xp = jsonCharacter.getInt("experience");
                    charactersList.add(character);
                }
                account.characterList = charactersList;
                account.mapsCompleted = jsonAccount.getInt("maps_completed");
                accounts.add(account);
            }


        //creare dictionar
        ArrayList<String> empty = new ArrayList<>();
        ArrayList<String> enemy = new ArrayList<>();
        ArrayList<String> shop = new ArrayList<>();
        ArrayList<String> finish = new ArrayList<>();

        for(int i = 0; i < Objects.requireNonNull(jsonStoriesArray).length(); i++){
            JSONObject jsonStory = jsonStoriesArray.getJSONObject(i);
            if(jsonStory.getString("type").equalsIgnoreCase("EMPTY")){
                empty.add(jsonStory.getString("value"));
            }else if(jsonStory.getString("type").equalsIgnoreCase("ENEMY")){
                enemy.add(jsonStory.getString("value"));
            }else if(jsonStory.getString("type").equalsIgnoreCase("SHOP")){
                shop.add(jsonStory.getString("value"));
            }else if(jsonStory.getString("type").equalsIgnoreCase("FINISH")){
                finish.add(jsonStory.getString("value"));
            }
        }
        dictionary.put(CellEnum.EMPTY,empty);
        dictionary.put(CellEnum.ENEMY,enemy);
        dictionary.put(CellEnum.SHOP,shop);
        dictionary.put(CellEnum.FINISH,finish);

    }

    public void selectGameMode(){
        System.out.println("-------------Word of Marcel------------");

        boolean ok =  false;
        Integer game_mode = null;
        System.out.println("Select the game mode: ");
        System.out.println("1. Graphic Window");
        System.out.println("2. Terminal");

        while(!ok){
            try{
                game_mode = Integer.parseInt(helper.getUserInput("Game mode: "));
                if(game_mode > 0 && game_mode < 3 ){
                    ok = true;
                }else{
                    System.out.println("Invalid input!");
                }
            }catch(Exception ex){
                System.out.println("Invalid input!");
            }
        }
        if(game_mode == 1){
            GUI gui = new GUI();
            gui.go();
        }else {
            TerminalRunner terminal = new TerminalRunner();
            terminal.terminalRun();
        }
    }

    public Integer getCommand(Cell cell){
        Integer command = null;
        boolean ok = false;

        switch (cell.cellEnum) {
            case ENEMY ->{
                System.out.println("Options:");
                System.out.println("1. Attack");
                System.out.println("2. Spell");
                System.out.println("3. Potion");
                while(!ok) {
                    try {
                        command = Integer.parseInt(helper.getUserInput("Enter your next move:"));
                        if (command > 0 && command < 4) {
                            ok = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid command!");
                    }
                }
            }
            case SHOP -> {
                System.out.println("Available potions:");
                Shop shop;
                shop = (Shop) cell.cellElement;

                for(int i = 0; i < shop.potionList.size(); i++ ){
                    System.out.println(i+1 + ". " + shop.potionList.get(i) + " " + shop.potionList.get(i).getPrice() + " coins");
                }
                System.out.println("0. Exit Shop");
                while(!ok) {
                    try {
                        command = Integer.parseInt(helper.getUserInput("Enter your next move:"));
                        if (command >= 0 && command <= shop.potionList.size()) {
                            ok = true;
                        }else{
                            System.out.println("Invalid command!");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid command!");
                    }
                }
            }
        }
        return command;
    }

    public int getMove(){
        //System.out.println("You landed on an empty cell!");
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        boolean ok =  false;
        Integer command = null;
        while(!ok) {
            try {
                command = Integer.parseInt(helper.getUserInput("Enter your next move:"));
                if (command > 0 && command < 5) {
                    ok = true;
                }else{
                    System.out.println("Invalid command!");
                    throw new InvalidCommandException("Invalid command!");
                }
            } catch (Exception e) {
                System.out.println("Invalid command!");
            }
        }
        return command;
    }

    public void printStory(Cell cell){
        int index;
        index = (int) (Math.random() * dictionary.get(cell.cellEnum).size());
        System.out.println(dictionary.get(cell.cellEnum).get(index));
        dictionary.get(cell.cellEnum).remove(index);
    }
}
