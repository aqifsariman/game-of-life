package gameOfLife;

import java.io.IOException;
import java.util.Arrays;

public class Game {
    private String fileName, line;
    private int width;
    private int height;
    private int x;
    private int y;
    private int[][] board;
    private int count = 1;

    public Game(String fileName) {
        this.fileName = fileName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void gameStart() throws IOException {
        ReaderWriter reader = new ReaderWriter(Constants.GOL_PATH);
        reader.readFile(this.fileName);
        while (null != (line = reader.bufferedFile())) {
            if (line.contains("#")) {
                continue;
            }
            if (line.contains("GRID")) {
                String[] gridArray = line.split(" ");
                this.width = Integer.parseInt(gridArray[1]);
                this.height = Integer.parseInt(gridArray[2]);
                this.board = new int[width][height];
            }
            if (line.contains("START")) {
                String[] startPointArray = line.split(" ");
                this.x = Integer.parseInt(startPointArray[1]);
                this.y = Integer.parseInt(startPointArray[2]);
            }
            if (line.contains("DATA")) {
                int currentRow = this.x - 1;
                int currentCol = this.y;

                while (null != (line = reader.bufferedFile())) {
                    currentRow++;
                    currentCol = y;
                    String[] lineArray = line.split("");
                    for (int i = 0; i < lineArray.length; i++) {
                        if (lineArray[i].equalsIgnoreCase("*")) {
                            this.board[currentRow][currentCol] = 1;
                            currentCol++;
                        } else if (lineArray[i].equalsIgnoreCase(" ")) {
                            currentCol++;
                        } else {
                            System.err
                                    .println("Error in the starting configuration. Please input \" \" or \"*\" only ");
                        }
                    }
                }
                createBoard(width, height, this.board);
            }
        }
        // return this.board;
    }

    // INITIALIZE BOARD
    public void createBoard(int rows, int cols, int[][] board) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        // Displaying the grid
        System.out.println("\nOriginal Generation");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        nextGeneration(board, rows, cols);
        // return board;
    }

    // Function to print next generation
    public void nextGeneration(int board[][], int rows, int cols) {
        int[][] future = new int[rows][cols];
        this.count++;
        // Loop through every cell
        for (int l = 0; l < rows; l++) {
            for (int m = 0; m < cols; m++) {
                // finding no Of Neighbours that are alive
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        if ((l + i >= 0 && l + i < rows) && (m + j >= 0 && m + j < cols))
                            aliveNeighbours += board[l + i][m + j];

                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveNeighbours -= board[l][m];

                // Implementing the Rules of Life

                // Cell is lonely and dies
                if ((board[l][m] == 1) && (aliveNeighbours < 2))
                    future[l][m] = 0;

                // Cell dies due to over population
                else if ((board[l][m] == 1) && (aliveNeighbours > 3))
                    future[l][m] = 0;

                // A new cell is born
                else if ((board[l][m] == 0) && (aliveNeighbours == 3))
                    future[l][m] = 1;

                // Remains the same
                else
                    future[l][m] = board[l][m];
            }
        }

        System.out.println("Next Generation");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (future[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        while (count < 5) {
            System.out.println(count);
            nextGeneration(future, rows, cols);
        }
    }
}
