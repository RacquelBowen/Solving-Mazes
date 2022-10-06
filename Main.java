
//Racquel Miller
//CSCI 132 Spring 2022
//Due Friday April 22 @ 7:00pm

package com.Maze;
public class Main {

    public static void main(String[] args) {
	System.out.println(args[0]);
    GettingOut maze = new GettingOut(args[4]);
    maze.Starting();
    maze.ToTheFinish();
    }
}