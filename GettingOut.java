package com.Maze;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GettingOut{
    String[][] array;
    private int startX;
    private int startY;
    private int startHandX;
    private int startHandY;
    private int columns;
    private int rows;
    private Scanner scanner;
    private String direction;

    public void Starting(){ //sets the array to start on the outside of the maze
        for (int i = 0; i<=rows; i++) {
            for (int j = 0; j <=columns; j++) {
                if(i == 0 || i == rows){
                    if(array[j][i].equals(" . ")){
                        startX = j;
                        startY = i;
                    }
                }
                else if(j == 0 || j == columns){
                    if(array[j][i].equals(" . ")){
                        startX = j;
                        startY = i;
                    }
                }
            }
        }

        if(startY == rows) { //starts facing North
            startHandX = startX - 1;
            startHandY = startY;
            direction = "North";
        }

        else if(startX == 0){ //starts facing East
            startHandX = startX;
            startHandY = startY + 1;
            direction = "East";
        }

        else if(startY == 0){ //starts facing South
            startHandX = startX + 1;
            startHandY = startY;
            direction = "South";
        }

        else if(startX == columns){ //starts facing West
            startHandX = startX;
            startHandY = startY - 1;
            direction = "West";
        }
    }

    public void ToTheFinish(){ //starts the process of walking through the maze
        System.out.println();
        Moving(startX, startY, startHandX, startHandY);
    }

    public void EndMaze(int xM, int yM, int xF, int yF){ //once you reach the letter 'F' it does not call itself again
        array[xM][yM] = " X ";                           //it ends the recursive calls and prints where you are at the
        array[xF][yF] = " ● ";                           //maze
        DisplayMaze();
        System.out.println("You reached the finish line.");
    }

    public void DisplayMaze(){ //prints out the maze and the path you have taken and the position you are at
        System.out.println();
        for (int i = 0; i<= rows; i++) {
            for (int j = 0; j <= columns; j++) {
                System.out.print(array[j][i]);
            }
            System.out.println();
        }
    }

    public void Moving(int xMaze, int yMaze, int RHandX, int RHandY) {
        int forwardX; //checks to see what x value is in front of you
        int forwardY; //checks to see what y value is in front of you

        switch (direction) {
            case "North" -> { //if you are going up it goes in the Northern direction
                forwardX = xMaze;
                forwardY = yMaze - 1;
                RHandX = xMaze + 1;
                RHandY = yMaze;
                if (!(forwardX >= 0 && forwardX <= columns && forwardY >= 0 && forwardY <= rows)) { //checks to see if you are
                    System.out.println("You can't reach the Finish using the right hand rule");          //leaving the maze
                } else if (array[forwardX][forwardY].equals(" F ")) { //checks to see if you have reached the letter or string
                    EndMaze(xMaze, yMaze, forwardX, forwardY);        //" F "
                } else if (array[RHandX][RHandY].equals(" . ") || array[RHandX][RHandY].equals(" X ")) { //checks to see if you
                    direction = "East";                                                                  //can turn to your right
                    array[xMaze][yMaze] = " X ";
                    xMaze = xMaze + 1;
                    array[xMaze][yMaze] = " ● ";
                    DisplayMaze();
                    Moving(xMaze, yMaze, RHandX, RHandY);
                } else if (array[forwardX][forwardY].equals(" . ") || array[forwardX][forwardY].equals(" X ")) { //checks to see if you
                    array[xMaze][yMaze] = " X ";                                                                 //can move forward
                    xMaze = forwardX;
                    yMaze = forwardY;
                    array[xMaze][yMaze] = " ● ";
                    DisplayMaze();
                    Moving(xMaze, yMaze, RHandX, RHandY);
                } else if (array[forwardX][forwardY].equals(" # ") && array[RHandX][RHandY].equals(" # ")) { //checks to see if
                    direction = "West";                                                                      //you have to turn
                    Moving(xMaze, yMaze, RHandX, RHandY);                                                    //left
                }
            }
            case "East" -> { //if you are going right it goes in the Eastern direction
                forwardX = xMaze + 1;
                forwardY = yMaze;
                RHandX = xMaze;
                RHandY = yMaze + 1;
                if (!(forwardX >= 0 && forwardX <= columns && forwardY >= 0 && forwardY <= rows)) { //checks to see if you are
                    System.out.println("You can't reach the Finish using the right hand rule");          //leaving the maze
                } else if (array[forwardX][forwardY].equals(" F ")) { //checks to see if you have reached the letter or string
                    EndMaze(xMaze, yMaze, forwardX, forwardY);        //" F "
                } else if (array[RHandX][RHandY].equals(" . ") || array[RHandX][RHandY].equals(" X ")) { //checks to see if you
                    direction = "South";                                                                 //can turn to your right
                    array[xMaze][yMaze] = " X ";
                    yMaze = yMaze + 1;
                    array[xMaze][yMaze] = " ● ";///
                    DisplayMaze();///
                    Moving(xMaze, yMaze, RHandX, RHandY);
                } else if (array[forwardX][forwardY].equals(" . ") || array[forwardX][forwardY].equals(" X ")) { //checks to see if you
                    array[xMaze][yMaze] = " X ";                                                                 //can move forward
                    xMaze = forwardX;
                    yMaze = forwardY;
                    array[xMaze][yMaze] = " ● ";
                    DisplayMaze();
                    Moving(xMaze, yMaze, RHandX, RHandY);

                } else if (array[forwardX][forwardY].equals(" # ") && array[RHandX][RHandY].equals(" # ")) { //checks to see if you
                    direction = "North";                                                                     //you have to turn
                    Moving(xMaze, yMaze, RHandX, RHandY);                                                    //left
                }
            }
            case "South" -> { //if you are going down it goes in the Southern direction
                forwardX = xMaze;
                forwardY = yMaze + 1;
                RHandX = xMaze - 1;
                RHandY = yMaze;
                if (!(forwardX >= 0 && forwardX <= columns && forwardY >= 0 && forwardY <= rows)) { //checks to see if you are
                    System.out.println("You can't reach the Finish using the right hand rule");          //leaving the maze
                } else if (array[forwardX][forwardY].equals(" F ")) {
                    EndMaze(xMaze, yMaze, forwardX, forwardY);
                } else if (array[RHandX][RHandY].equals(" . ") || array[RHandX][RHandY].equals(" X ")) { //checks to see if you
                    direction = "West";                                                                  //can turn to your right
                    array[xMaze][yMaze] = " X ";
                    xMaze = xMaze - 1;
                    array[xMaze][yMaze] = " ● ";
                    DisplayMaze();
                    Moving(xMaze, yMaze, RHandX, RHandY);
                } else if (array[forwardX][forwardY].equals(" . ") || array[forwardX][forwardY].equals(" X ")) { //checks to see if you
                    array[xMaze][yMaze] = " X ";                                                                 //can move forward
                    xMaze = forwardX;
                    yMaze = forwardY;
                    array[xMaze][yMaze] = " ● ";
                    DisplayMaze();
                    Moving(xMaze, yMaze, RHandX, RHandY);
                } else if (array[forwardX][forwardY].equals(" # ") && array[RHandX][RHandY].equals(" # ")) { //checks to see if you
                    direction = "East";                                                                      //you have to turn
                    Moving(xMaze, yMaze, RHandX, RHandY);                                                    //left
                }
            }
            case "West" -> { //if you are going left it goes in the Western direction
                forwardX = xMaze - 1;
                forwardY = yMaze;
                RHandX = xMaze;
                RHandY = yMaze - 1;
                if (!(forwardX >= 0 && forwardX <= columns && forwardY >= 0 && forwardY <= rows)) { //checks to see if you are
                    System.out.println("You can't reach the Finish using the right hand rule");          //leaving the maze
                } else if (array[forwardX][forwardY].equals(" F ")) {
                    EndMaze(xMaze, yMaze, forwardX, forwardY);
                } else if (array[RHandX][RHandY].equals(" . ") || array[RHandX][RHandY].equals(" X ")) { //checks to see if you
                    direction = "North";                                                                 //can turn to your right
                    array[xMaze][yMaze] = " X ";
                    yMaze = yMaze - 1;
                    array[xMaze][yMaze] = " ● ";
                    DisplayMaze();
                    Moving(xMaze, yMaze, RHandX, RHandY);
                } else if (array[forwardX][forwardY].equals(" . ") || array[forwardX][forwardY].equals(" X ")) { //checks to see if you
                    array[xMaze][yMaze] = " X ";                                                                 //can move forward
                    xMaze = forwardX;
                    yMaze = forwardY;
                    array[xMaze][yMaze] = " ● ";
                    DisplayMaze();
                    Moving(xMaze, yMaze, RHandX, RHandY);
                } else if (array[forwardX][forwardY].equals(" # ") && array[RHandX][RHandY].equals(" # ")) { //checks to see if you
                    direction = "South";                                                                     //you have to turn
                    Moving(xMaze, yMaze, RHandX, RHandY);                                                    //left
                }
            }
        }
    }

    public void countColumns(String x) {
        File fileMaze = new File(x);
        try {
            scanner = new Scanner(fileMaze);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner.hasNextLine()) {
            columns = scanner.nextLine().split(" ").length;
        }
        scanner.close();
        columns = columns - 2;
    }

    public void countRows(String file) {
        Path path = Paths.get(file);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rows = (int)lines - 1;
    }

    GettingOut(String fileName){
        countColumns(fileName);
        countRows(fileName);
        array = new String[columns+1][rows+1];
        try{
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNext()){
                for(int i = 0; i <= rows; i++) {
                    for (int j = 0; j <= columns; j++) {
                        array[j][i] = " " + input.next() + " ";
                    }
                }
            }
        }catch(Exception e){e.printStackTrace();}
        DisplayMaze();
    }
}
