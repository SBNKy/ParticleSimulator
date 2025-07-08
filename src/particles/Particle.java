package particles;

import java.awt.Graphics;

public abstract class Particle {
    public static final int PARTICLE_SIZE = 10;
    public abstract void draw(int x, int y, Graphics g);
    public abstract void update(int row, int col, Grid grid);
}
