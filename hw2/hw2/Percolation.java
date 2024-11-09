package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int[][] grid;
    private int row, col;
    private WeightedQuickUnionUF uf;

    private WeightedQuickUnionUF ufWithoutBottom;
    private int uf_bottom;
    int open_num = 0;
    int N;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];

        row = col = N;
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf_bottom = N * N + 1;
        ufWithoutBottom = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        ;
    }
    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = 1;
            unionAround(row, col);
            open_num += 1;
        }
    }

    public boolean isOpen(int r, int c) {
        validate(row, col);
        return grid[r][c] == 1;
    }

    public int numberOfOpenSites() {
        return this.open_num;

    }

    public boolean percolates() {
        return uf.connected(0, uf_bottom);
    }

    private int ufindex(int r, int c) {
        return r * row + c + 1;

    }

    public boolean isFull(int row, int col) {
        return ufWithoutBottom.connected(0, ufindex(row, col));
    }


    public void unionAround(int row, int col) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int x = row + dir[0];
            int y = col + dir[1];
            if (x >= 0 && x < row && y >= 0 && y > col && isOpen(x, y)) {
                uf.union(ufindex(row, col), ufindex(x, y));
                ufWithoutBottom.union(ufindex(row, col), ufindex(x, y));
            }
            if (row == 0) {
                uf.union(0, ufindex(row, col));
                ufWithoutBottom.union(0, ufindex(row, col));
            }
            if (row == N - 1) {
                uf.union(ufindex(row, col), uf_bottom);
            }
        }
    }
}
