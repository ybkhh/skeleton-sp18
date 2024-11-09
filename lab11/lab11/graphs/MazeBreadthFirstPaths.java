package lab11.graphs;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;

    private boolean targetFound = false;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze =m;
       int  s=maze.xyTo1D(sourceX,sourceY);
       int t=maze.xyTo1D(targetX,targetY);
       distTo[s]=0;
       edgeTo[s]=s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue= new ArrayDeque<>();
        queue.add(s);
        marked[s]=true;
        while (!queue.isEmpty()) {
            int v = queue.poll();

            {
                for (int w : maze.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        marked[w]=true;

                        announce();


                    }
                    if (w==t){
                        return;
                    }
                    else
                    {
                        queue.add(w);
                    }


                }
            }

        }



    }


    @Override
    public void solve() {
         bfs();

    }
}

