/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
    public ArrayList<MazeCell> solveMazeDFS()
    {
        // Make a stack to add the cells to
        Stack<MazeCell> stack = new Stack<>();

        // Set the first cell to explored
        maze.getStartCell().setExplored(true);
        // Add the first cell to the stack
        stack.push(maze.getStartCell());

        // While loop that runs while the stack isn't empty
        while (!stack.isEmpty())
        {
            MazeCell currentCell = stack.peek();

            if (currentCell == maze.getEndCell())
            {
                return getSolution();
            }
            // Check each direction (north, east, south, then west) in order and find if it can search there.
            if (maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol()))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                if (!nextCell.isExplored()) {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    stack.push(nextCell);
                }
            }
            else if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                if (!nextCell.isExplored())
                {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    stack.push(nextCell);
                }
            }
            else if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol()))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                if (!nextCell.isExplored())
                {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    stack.push(nextCell);
                }
            }
            else if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                if (!nextCell.isExplored())
                {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    stack.push(nextCell);
                }
            }
            else
            {
                stack.pop();
            }
        }

        return null;
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> queue = new LinkedList<>();

        // Set the first cell to explored
        maze.getStartCell().setExplored(true);
        // Add the first cell to the queue
        queue.add(maze.getStartCell());

        // While loop that runs while the queue isn't empty
        while (!queue.isEmpty())
        {
            MazeCell currentCell = queue.remove();

            if (currentCell == maze.getEndCell())
            {
                return getSolution();
            }

            // Check each direction (north, east, south, then west) in order and find if it can search there.
            if (maze.isValidCell(currentCell.getRow() - 1, currentCell.getCol()))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow() - 1, currentCell.getCol());
                if (!nextCell.isExplored()) {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    queue.add(nextCell);
                }
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() + 1))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow(), currentCell.getCol() + 1);
                if (!nextCell.isExplored())
                {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    queue.add(nextCell);
                }
            }
            if (maze.isValidCell(currentCell.getRow() + 1, currentCell.getCol()))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow() + 1, currentCell.getCol());
                if (!nextCell.isExplored())
                {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    queue.add(nextCell);
                }
            }
            if (maze.isValidCell(currentCell.getRow(), currentCell.getCol() - 1))
            {
                MazeCell nextCell = maze.getCell(currentCell.getRow(), currentCell.getCol() - 1);
                if (!nextCell.isExplored())
                {
                    nextCell.setExplored(true);
                    nextCell.setParent(currentCell);
                    queue.add(nextCell);
                }
            }
        }

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
