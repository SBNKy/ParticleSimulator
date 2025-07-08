package gui;

import particles.Grid;
import particles.Particle;
import particles.SandParticle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppGUI extends JFrame {
    private final int windowWidth = 640;
    private final int windowHeight = 480;
    private JPanel canvas;
    private Grid grid;
    private boolean mouseHeld = false;
    private Timer holdTimer;

    public AppGUI() {
        setTitle("Particle Simulator");
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();

        setVisible(true);
    }

    private void createComponents() {
        int rows = windowHeight / Particle.PARTICLE_SIZE;
        int cols = windowWidth / Particle.PARTICLE_SIZE;
        System.out.println("Rows: " + rows + ", Cols:" + cols);
        grid = new Grid(rows, cols);

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
            }
        };

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseHeld = true;
                addParticleAtMouse(e);

                holdTimer = new Timer(50, ev -> {
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

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseHeld = false;
                addParticleAtMouse(e);
            }
        });

        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        add(canvas);
        pack();

        Timer timer = new Timer(50, e -> {
            grid.updateGrid();
            canvas.repaint();
        });
        timer.start();
    }

    private void drawGrid(Graphics g) {
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
            canvas.repaint();
        }
    }

}
