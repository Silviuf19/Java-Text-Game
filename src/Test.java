
import java.lang.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Helper helper = new Helper();
        Game game = Game.getInstance();
        game.run();
        System.out.println("-------------Word of Marcel------------");
        System.out.println("Select the game mode: ");
        System.out.println("1. Graphic Window");
        System.out.println("2. Terminal");

        testCommand(helper);

        System.out.println("Game mode: 2");
        int accountIndex = 0;
        int characterIndex = 0;

        testCommand(helper);

        ArrayList<Account> accounts = game.accounts;
        System.out.println("Accounts:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i + 1 + ". " + accounts.get(i).information.name);
        }

        testCommand(helper);

        System.out.println("Type the number of your account: 1");

        testCommand(helper);


        System.out.println("Characters:");
        for (int i = 0; i < accounts.get(accountIndex).characterList.size(); i++) {
            System.out.println(i + 1 + ". " + accounts.get(accountIndex).characterList.get(i).name);
        }

        testCommand(helper);

        System.out.println("Type the number of your character: 1");

        testCommand(helper);

        int[][] tempMatrix = new int[][]{
                {4, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 2},
                {0, 0, 0, 0, 3},
        };
        Grid grid = Grid.generateGivenGrid(5, 5, tempMatrix);
        grid.currentCharacter = game.accounts.get(accountIndex).characterList.get(characterIndex);
        grid.currentCharacter.current_x = grid.currentCell.x;
        grid.currentCharacter.current_y = grid.currentCell.y;
        Character character = grid.currentCharacter;
        Enemy enemyTemp = new Enemy();
        enemyTemp.maxHealth = 200;
        enemyTemp.currentHealth = 200;
        grid.get(3).get(4).cellElement = enemyTemp;
        ManaPotion manaPotionTemp = new ManaPotion();
        HealthPotion healthPotionTemp = new HealthPotion();
        Shop shopTemp = (Shop) grid.get(0).get(3).cellElement;
        shopTemp.potionList.add(manaPotionTemp);
        shopTemp.potionList.add(healthPotionTemp);
        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 3");

        testCommand(helper);

        grid.goEast();
        game.printStory(grid.currentCell);
        character.getCoinsCell();
        grid.currentCell.visited = true;
        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 3");

        testCommand(helper);

        grid.goEast();
        game.printStory(grid.currentCell);
        character.getCoinsCell();
        grid.currentCell.visited = true;
        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 3");

        testCommand(helper);

        grid.goEast();
        game.printStory(grid.currentCell);
        grid.currentCell.visited = true;
        character.currentHealth = 1000;
        character.maxHealth = 1000;
        character.inventory.coins = 20;
        character.maxMana = 1000;
        character.currentMana = 1000;
        Shop shop = (Shop) grid.currentCell.cellElement;
        System.out.println("Your coins: " + character.inventory.coins);
        System.out.println("Available potions:");
        for (int i = 0; i < shop.potionList.size(); i++) {
            System.out.println(i + 1 + ". " + shop.potionList.get(i) + " " + shop.potionList.get(i).getPrice() + " coins");
        }
        System.out.println("0. Exit Shop");
        System.out.println("Enter your next move:  " + shop.potionList.size());

        testCommand(helper);

        character.buyPotion(shop.potionList.get(shop.potionList.size() - 1));
        shop.removePotion(shop.potionList.size() - 1);

        testCommand(helper);


        System.out.println("Your coins: " + character.inventory.coins);
        System.out.println("Available potions:");
        for (int i = 0; i < shop.potionList.size(); i++) {
            System.out.println(i + 1 + ". " + shop.potionList.get(i) + " " + shop.potionList.get(i).getPrice() + " coins");
        }
        System.out.println("0. Exit Shop");
        System.out.println("Enter your next move:  " + shop.potionList.size());

        testCommand(helper);

        character.buyPotion(shop.potionList.get(shop.potionList.size() - 1));
        shop.removePotion(shop.potionList.size() - 1);

        testCommand(helper);

        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 3");

        testCommand(helper);

        grid.goEast();
        game.printStory(grid.currentCell);
        character.getCoinsCell();
        grid.currentCell.visited = true;
        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 2");

        testCommand(helper);

        grid.goSouth();
        game.printStory(grid.currentCell);
        character.getCoinsCell();
        grid.currentCell.visited = true;
        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 2");

        testCommand(helper);

        grid.goSouth();
        game.printStory(grid.currentCell);
        character.getCoinsCell();
        grid.currentCell.visited = true;
        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 2");

        testCommand(helper);

        grid.goSouth();
        game.printStory(grid.currentCell);
        Enemy enemy = (Enemy) grid.currentCell.cellElement;
        int size = character.spells.size();
        for (int j = 0; j < size; j++) {
            System.out.println("Options:");
            System.out.println("1. Attack");
            System.out.println("2. Spell");
            System.out.println("3. Potion");
            System.out.println("Enter your next move: 2");

            testCommand(helper);

            character.printSpellList();
            System.out.println("Choose spell number: 1");
            character.useSpell(character.spells.get(0), enemy);
            System.out.println("Enemy health: " + enemy.currentHealth + "/" + enemy.maxHealth);
            enemy.attackCharacter(character);
            System.out.println("Your health: " + character.currentHealth + "/" + character.maxHealth);

            testCommand(helper);
        }

        System.out.println("Options:");
        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Potion");
        System.out.println("Enter your next move: 3");

        testCommand(helper);

        character.printPotionList();
        System.out.println("Choose potion number: 1");
        System.out.println("You used " + character.inventory.potionList.get(0));
        character.inventory.potionList.get(0).usePotion(character);
        character.inventory.removePotion(0);
        enemy.attackCharacter(character);
        System.out.println("Your health: " + character.currentHealth + "/" + character.maxHealth);

        testCommand(helper);

        System.out.println("Options:");
        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Potion");
        System.out.println("Enter your next move: 3");

        testCommand(helper);

        character.printPotionList();
        System.out.println("Choose potion number: 1");
        System.out.println("You used " + character.inventory.potionList.get(0));
        character.inventory.potionList.get(0).usePotion(character);
        character.inventory.removePotion(0);
        enemy.attackCharacter(character);
        System.out.println("Your health: " + character.currentHealth + "/" + character.maxHealth);

        testCommand(helper);

        while (enemy.currentHealth > 0) {
            System.out.println("Options:");
            System.out.println("1. Attack");
            System.out.println("2. Spell");
            System.out.println("3. Potion");
            System.out.println("Enter your next move: 1");

            testCommand(helper);

            enemy.receiveDamage(character.getDamage());
            if (enemy.currentHealth == 0)
                break;
            System.out.println("Enemy health: " + enemy.currentHealth + "/" + enemy.maxHealth);
            enemy.attackCharacter(character);
            System.out.println("Your health: " + character.currentHealth + "/" + character.maxHealth);

            testCommand(helper);
        }

        character.getCoinsEnemy();
        character.addXP(enemy.maxHealth);
        grid.currentCell.visited = true;
        grid.printGrid();
        System.out.println("Options:");
        System.out.println("1. go north");
        System.out.println("2. go south");
        System.out.println("3. go east");
        System.out.println("4. go west");
        System.out.println("Enter your next move: 2");

        testCommand(helper);

        grid.goSouth();
        grid.printGrid();
        game.printStory(grid.currentCell);
        System.out.println("Congratulations! You finished the game!");
        System.out.println("Enemies killed: 1");
        System.out.println("Collected experience: 200");
        System.out.println("Level: " + character.level);
        System.out.println("Collected coins: " + character.inventory.coins);


    }

    public static void testCommand(Helper helper){
        String command = helper.getUserInput("Press P: ");
        boolean ok = false;
        while(!ok){
            if(!command.equalsIgnoreCase("P")){
                command = helper.getUserInput("Press P!: ");
            }else{
                ok = true;
            }
        }
    }
}



