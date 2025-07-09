package gui;

import particles.Grid;
import particles.Particle;
import particles.SandParticle;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel {
    private final Grid grid;
    private boolean mouseHeld = false;
    private Timer holdTimer;

    public Canvas(Grid grid, int windowWidth, int windowHeight) {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.grid = grid;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseHeld = true;
                addParticleAtMouse(e);

                holdTimer = new Timer(AppGUI.TIMER_DELAY, ev -> {
                    if (mouseHeld)
                        addParticleAtMouse(e);
                });
                holdTimer.start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseHeld = false;
                if (holdTimer != null)
                    holdTimer.stop();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseHeld = false;
                addParticleAtMouse(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g, grid);
    }

    private void drawGrid(Graphics g, Grid grid) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int row = grid.getRows() - 1; row >= 0; row--) {
            for (int col = 0; col < grid.getCols(); col++) {
                Particle p = grid.getParticle(row, col);
                if (p != null) {
                    p.draw(row, col, g);
                }
            }
        }
    }

    private void addParticleAtMouse(MouseEvent e) {
        int row = e.getY() / Particle.PARTICLE_SIZE;
        int col = e.getX() / Particle.PARTICLE_SIZE;

        boolean rowInBounds = row >= 0 && row < grid.getRows();
        boolean colInBounds = col >= 0 && col < grid.getCols();
        if (rowInBounds && colInBounds) {
            grid.setParticle(row, col, new SandParticle());
            repaint();
        }
    }

}
