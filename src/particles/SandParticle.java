package particles;

import java.awt.Color;
import java.awt.Graphics;

public class SandParticle extends Particle {
    private final Color color = Color.YELLOW;

    @Override
    public void draw(int row, int col, Graphics g) {
        g.setColor(color);
        g.fillRect(col * PARTICLE_SIZE, row * PARTICLE_SIZE, PARTICLE_SIZE, PARTICLE_SIZE);

    }

    @Override
    public void update(int row, int col, Grid g) {
        System.out.println(row + " " + col);
        if (row + 1 >= g.getRows())
            return;

        Particle[][] grid = g.getGrid();

        if (grid[row+1][col] == null) {
            grid[row][col] = null;
            grid[row+1][col] = this;
            return;
        }

        boolean canMoveLeft = col > 0 && grid[row+1][col-1] == null;
        boolean canMoveRight = col < g.getCols() - 1 && grid[row+1][col+1] == null;

        if (canMoveLeft && canMoveRight) {
            if (Math.random() < 0.5)
                grid[row+1][col-1] = this;
            else
                grid[row+1][col+1] = this;
            grid[row][col] = null;
        } else if (canMoveLeft) {
            grid[row+1][col-1] = this;
            grid[row][col] = null;
        } else if (canMoveRight) {
            grid[row+1][col+1] = this;
            grid[row][col] = null;
        }
    }
}
