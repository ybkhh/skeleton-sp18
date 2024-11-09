package hw4.puzzle;


import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState {

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    /*public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }*/
    private final int Blank = 0;
    private int[][] tiles;
    private int size;

    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int boardsize = size();
        int blankrow = -1;
        int blankcol = -1;
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                if (tileAt(row, col) == Blank) {
                    blankcol = row;
                    blankcol = col;

                }
            }
        }
        int[][] tempttile = new int[boardsize][boardsize];
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                tempttile[i][j] = tileAt(i, j);
            }

        }
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                if (Math.abs(row - blankrow) + Math.abs(col - blankcol) == 1) {
                    tempttile[blankrow][blankcol] = tempttile[row][col];
                    tempttile[row][col] = Blank;
                    Board neighbor = new Board(tempttile);
                    neighbors.enqueue(neighbor);
                    tempttile[row][col] = tempttile[blankrow][blankcol];
                    tempttile[blankrow][blankcol] = Blank;

                }
            }
        }


        return neighbors;

    }

    public int hamming() {
        int incorrecttiles = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (tiles[row][col] == Blank) {
                    continue;
                }
                if (tiles[row][col] != xyTo1D(row, col)) {
                    incorrecttiles++;
                }

            }
        }
        return incorrecttiles;
    }

    private int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    public int manhattan() {
        int totaldistance = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                if (tiles[row][col] == Blank) {
                    continue;
                }
                if (tiles[row][col] != xyTo1D(row, col)) {
                    totaldistance += getxydiff(tiles[row][col], row, col);
                }
            }
        }
        return totaldistance;
    }
    public int getxydiff(int num,int r,int c){
        int dis=0;
        int [] a=intToXY(num);
        dis=Math.abs(r-a[0])+Math.abs(c-a[1]);
        return dis;


    }
    private int[] intToXY(int tileValue) {
        return new int[] { (tileValue - 1) / size, (tileValue - 1) % size };
    }
    @Override
    public  int estimatedDistanceToGoal(){
        return manhattan();
    }
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if ((y instanceof Board)) {
            return false;
        }
        Board otherBoard = (Board) y;
        if (this.size != otherBoard.size) {
            return false;
        }
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (this.tiles[row][col] != otherBoard.tiles[row][col]) {
                    return false;

                }
            }
        }
        return true;}

    }



