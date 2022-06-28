import java.util.ArrayList;

public class TerminalRunner {
    Grid grid;
    Game game = Game.getInstance();
    Helper helper = new Helper();

    public void terminalRun(){
        int accountIndex = 0;
        int characterIndex = 0;
        boolean ok;
        int enemiesDefeated = 0;
        int collectedXP = 0;

        ArrayList<Account> accounts = game.accounts;

        System.out.println("Accounts:");
        for(int i = 0; i < accounts.size(); i++){
            System.out.println(i + 1 + ". " + accounts.get(i).information.name);
        }
        ok = false;
        while(!ok) {
            try {
                accountIndex = Integer.parseInt(helper.getUserInput("Type the number of your account: "))-1;
                if(accountIndex >= 0 && accountIndex < accounts.size()){
                    ok = true;
                }else{
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }

        System.out.println("Characters:");
        for(int i = 0; i < accounts.get(accountIndex).characterList.size(); i++){
            System.out.println(i + 1 + ". " + accounts.get(accountIndex).characterList.get(i).name);
        }

        ok = false;
        while(!ok) {
            try {
                characterIndex = Integer.parseInt(helper.getUserInput("Type the number of your character: "))-1;
                if(characterIndex >= 0 && characterIndex < accounts.get(accountIndex).characterList.size()){
                    ok = true;
                }else{
                    System.out.println("Invalid input!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }

        int randLength = 5 + (int) (Math.random()*3);
        int randWidth = 5 + (int) (Math.random()*3);

        grid = Grid.generateGrid(randLength,randWidth);
        grid.currentCharacter = game.accounts.get(accountIndex).characterList.get(characterIndex);
        grid.currentCharacter.current_x = grid.currentCell.x;
        grid.currentCharacter.current_y = grid.currentCell.y;

        Character character = grid.currentCharacter;
        int commandMove;
        int commandShop;
        int commandBattle;
        boolean okGame = false;
        boolean okBattle;
        boolean okMove;
        boolean okSpell;

        while(!okGame){
            //grid.printGrid();
            if(!grid.currentCell.visited){
                game.printStory(grid.currentCell);
            }
            if(grid.currentCell.cellEnum == CellEnum.EMPTY){
                if(!grid.currentCell.visited)
                    character.getCoinsCell();
                grid.currentCell.visited = true;
            }else if(grid.currentCell.cellEnum == CellEnum.SHOP){
                Shop shop = (Shop) grid.currentCell.cellElement;
                ok = false;
                while(!ok){
                    System.out.println("Your coins: " + character.inventory.coins);
                    commandShop = game.getCommand(grid.currentCell);
                    if(commandShop == 0){
                        ok = true;
                    }else{
                        if(character.buyPotion(shop.potionList.get(commandShop - 1))){
                            System.out.println(shop.potionList.get(commandShop - 1));
                            shop.removePotion(commandShop-1);
                        }else{
                            ok = true;
                        }
                    }
                }
                grid.currentCell.visited = true;
            }else if(grid.currentCell.cellEnum == CellEnum.ENEMY && !grid.currentCell.visited){
                Enemy enemy = (Enemy) grid.currentCell.cellElement;
                okBattle = false;
                while(!okBattle) {
                    ok = false;
                    while (!ok) {
                        commandBattle = game.getCommand(grid.currentCell);
                        if (commandBattle == 1) {
                            enemy.receiveDamage(character.getDamage());
                            System.out.println("Enemy health: " + enemy.currentHealth + "/" + enemy.maxHealth);
                            ok = true;
                        } else if (commandBattle == 2) {
                            okSpell = false;
                            while(!okSpell) {
                                if (character.useSpellOnEnemy(enemy) == 1) {
                                    System.out.println("Enemy health: " + enemy.currentHealth + "/" + enemy.maxHealth);
                                    ok = true;
                                }
                                okSpell = true;
                            }
                        } else if (commandBattle == 3) {
                            if(character.inventory.potionList.size() == 0){
                                System.out.println("You have no potions");
                            }else {
                                character.usePotion();
                                ok = true;
                            }
                        }
                    }
                    if(enemy.currentHealth == 0){
                        character.getCoinsEnemy();
                        character.addXP(enemy.maxHealth);
                        enemiesDefeated++;
                        collectedXP += enemy.maxHealth;
                        grid.currentCell.visited = true;
                        okBattle = true;
                    }
                    if(enemy.currentHealth > 0 && character.currentHealth > 0){
                    enemy.attackCharacter(character);
                    System.out.println("Your health: " + character.currentHealth + "/" + character.maxHealth);
                    }

                    if(character.currentHealth == 0){
                        System.out.println("You died.");
                        System.out.println("Enemies killed: " + enemiesDefeated);
                        System.out.println("Collected experience: " + collectedXP);
                        System.out.println("Level: " + character.level);
                        System.out.println("Collected coins: " + character.inventory.coins);
                        grid.currentCell.visited = true;
                        okGame = true;
                        okBattle = true;
                    }
                }
            }else if(grid.currentCell.cellEnum == CellEnum.FINISH){
                System.out.println("Congratulations! You finished the game!");
                System.out.println("Enemies killed: " + enemiesDefeated);
                System.out.println("Collected experience: " + collectedXP);
                System.out.println("Level: " + character.level);
                System.out.println("Collected coins: " + character.inventory.coins);
                break;
            }
            okMove = false;
            if(character.currentHealth != 0)
            while(!okMove){
                grid.printGrid();
                commandMove = game.getMove();
                if(commandMove == 1){
                    if(grid.goNorth()){
                        okMove = true;
                    }
                }else if(commandMove == 2){
                    if(grid.goSouth()){
                        okMove = true;
                    }
                }else if(commandMove == 3){
                    if(grid.goEast()){
                        okMove = true;
                    }
                }else if(commandMove == 4){
                    if(grid.goWest()){
                        okMove = true;
                    }
                }
            }
        }
    }
}
