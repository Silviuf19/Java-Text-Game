import java.util.ArrayList;
import java.util.Arrays;

public class Grid extends ArrayList<ArrayList<Cell>> {
    public int length;
    public int width;
    Cell currentCell;
    Character currentCharacter;

    private Grid(){}

    static Grid generateGrid(int length, int width){
        Grid grid = new Grid();
        grid.length = length;
        grid.width = width;
        int[][] tempGrid = new int[length][width];
        int numberOfEnemies = 4 + (int)(Math.random()*3);
        int numberOfShops = 3 + (int)(Math.random()*3);
        int xRandom;
        int yRandom;

        for (int[] row: tempGrid)
            Arrays.fill(row, 0);

        while(numberOfEnemies > 0){
            xRandom = (int)(Math.random()*length);
            yRandom = (int)(Math.random()*width);
            if(tempGrid[xRandom][yRandom] == 0){
                tempGrid[xRandom][yRandom] = 2;
                numberOfEnemies--;
            }
        }

        while(numberOfShops > 0){
            xRandom = (int)(Math.random()*length);
            yRandom = (int)(Math.random()*width);
            if(tempGrid[xRandom][yRandom] == 0){
                tempGrid[xRandom][yRandom] = 1;
                numberOfShops--;
            }
        }

        boolean ok = false;
        while(!ok){
            xRandom = (int)(Math.random()*length);
            yRandom = (int)(Math.random()*width);
            if(tempGrid[xRandom][yRandom] == 0){
                tempGrid[xRandom][yRandom] = 3;
                ok = true;
            }
        }

        Cell cell;


        for(int i = 0; i < length; i++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                cell = new Cell();
                if(tempGrid[i][j] == 0){
                    cell.cellEnum = CellEnum.EMPTY;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new EmptyCell();
                    row.add(cell);
                }else if(tempGrid[i][j] == 1){
                    cell.cellEnum = CellEnum.SHOP;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new Shop();
                    row.add(cell);
                }else if(tempGrid[i][j] == 2){
                    cell.cellEnum = CellEnum.ENEMY;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new Enemy();
                    row.add(cell);
                }else if(tempGrid[i][j] == 3){
                    cell.cellEnum = CellEnum.FINISH;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new FinishCell();
                    row.add(cell);
                }
            }
            grid.add(row);
        }
        ok = false;
        while(!ok){
            xRandom = (int)(Math.random()*length);
            yRandom = (int)(Math.random()*width);
            if(tempGrid[xRandom][yRandom] == 0){
                grid.currentCell = grid.get(xRandom).get(yRandom);
                grid.currentCell.visited = true;
                ok = true;
            }
        }
        return grid;
    }

    static Grid generateGivenGrid(int length, int width, int[][] tempGrid){
        Grid grid = new Grid();
        grid.length = length;
        grid.width = width;
        Cell cell;
        for(int i = 0; i < length; i++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                cell = new Cell();
                if(tempGrid[i][j] == 0){
                    cell.cellEnum = CellEnum.EMPTY;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new EmptyCell();
                    row.add(cell);
                }else if(tempGrid[i][j] == 1){
                    cell.cellEnum = CellEnum.SHOP;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new Shop();
                    row.add(cell);
                }else if(tempGrid[i][j] == 2){
                    cell.cellEnum = CellEnum.ENEMY;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new Enemy();
                    row.add(cell);
                }else if(tempGrid[i][j] == 3){
                    cell.cellEnum = CellEnum.FINISH;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new FinishCell();
                    row.add(cell);
                }else if(tempGrid[i][j] == 4){
                    cell.cellEnum = CellEnum.EMPTY;
                    cell.x = i;
                    cell.y = j;
                    cell.cellElement = new EmptyCell();
                    row.add(cell);
                    grid.currentCell = cell;
                    grid.currentCell.visited = true;
                }
            }
            grid.add(row);
        }
        return grid;
    }

    public void printGrid(){
        for(int i = 0; i < length; i ++) {
            for (int j = 0; j < width; j++){
                if(currentCell.x == i && currentCell.y == j){
                    System.out.print("P ");
                }else if(!this.get(i).get(j).visited) {
                    System.out.print("? ");
                } else{
                System.out.print(this.get(i).get(j).cellElement.toCharacter() + " ");
                }
            }
            System.out.println(" ");
        }
    }

    public boolean goNorth() {
            if (currentCell.x > 0) {
                currentCell = this.get(currentCell.x - 1).get(currentCell.y);
                currentCharacter.current_x = currentCell.x;
                currentCharacter.current_y = currentCell.y;
                return true;
            } else {
                System.out.println("Invalid move");
                return false;
            }
    }
    public boolean goSouth(){
            if(currentCell.x < length-1){
                currentCell = this.get(currentCell.x+1).get(currentCell.y);
                currentCharacter.current_x = currentCell.x;
                currentCharacter.current_y = currentCell.y;
                return true;
            } else{
                System.out.println("Invalid move");
                return false;
            }
    }
    public boolean goWest(){
            if (currentCell.y > 0) {
                currentCell = this.get(currentCell.x).get(currentCell.y - 1);
                currentCharacter.current_x = currentCell.x;
                currentCharacter.current_y = currentCell.y;
                return true;
            } else {
                System.out.println("Invalid move");
                return false;
            }
    }
    public boolean goEast(){
            if (currentCell.y < width - 1) {
                currentCell = this.get(currentCell.x).get(currentCell.y + 1);
                currentCharacter.current_x = currentCell.x;
                currentCharacter.current_y = currentCell.y;
                return true;
            } else {
                System.out.println("Invalid move");
                return false;
            }
    }


}
