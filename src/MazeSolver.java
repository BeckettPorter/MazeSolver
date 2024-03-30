/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze

        MazeCell currentCell = maze.getEndCell();
        ArrayList<MazeCell> path = new ArrayList<>();
        path.add(currentCell);

        while (currentCell != maze.getStartCell())
        {
            currentCell = currentCell.getParent();
            path.add(currentCell);
        }

        // Should be from start to end cells
        return reverseArray(path);
    }

    private ArrayList<MazeCell> reverseArray(ArrayList<MazeCell> ar)
    {
        Stack<MazeCell> s = new Stack<>();
        ArrayList<MazeCell> newAr = new ArrayList<>();

        for (MazeCell mazeCell : ar) {
            s.push(mazeCell);
        }
        for (int i = 0; i < ar.size(); i++)
        {
            newAr.add(i, s.pop());
        }
        return newAr;
    }



    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze

        ArrayList<MazeCell> ar = new ArrayList<>();
        MazeCell currentCell = maze.getStartCell();


        while (currentCell != maze.getEndCell())
        {
            if (maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol()))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                nextCell.setParent(currentCell);
                currentCell = nextCell;

                ar.add(currentCell);
            }

            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                nextCell.setParent(currentCell);
                currentCell = nextCell;

                ar.add(currentCell);
            }

            if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol()))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                nextCell.setParent(currentCell);
                currentCell = nextCell;

                ar.add(currentCell);
            }

            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                nextCell.setParent(currentCell);
                currentCell = nextCell;

                ar.add(currentCell);
            }
        }

        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        for (MazeCell e : ar)
        {
            System.out.println(e.getParent());
        }
        return ar;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
