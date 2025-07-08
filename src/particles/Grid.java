package particles;

public class Grid {
    private final int rows;
    private final int cols;
    private final Particle[][] grid;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Particle[rows][cols];
    }

    public void updateGrid() {
        for (int row = rows-1; row >= 0; row--) {
            for (int col = 0; col < cols; col++) {
                Particle p = grid[row][col];
                if (p != null)
                    p.update(row, col, this);
            }
        }
    }

    public void setParticle(int row, int col, Particle particle) {
        grid[row][col] = particle;
    }

    public Particle getParticle(int row, int col) {
        return grid[row][col];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Particle[][] getGrid() {
        return grid;
    }
}
