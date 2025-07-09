package gui;

import particles.Grid;
import particles.Particle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AppGUI extends JFrame {
    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;
    public static final int TIMER_DELAY = 50;
    private Canvas canvas;
    private Grid grid;


    public AppGUI() {
        setTitle("Particle Simulator");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createComponents();

        setVisible(true);
    }

    private void createComponents() {
        int rows = WINDOW_HEIGHT / Particle.PARTICLE_SIZE;
        int cols = WINDOW_WIDTH / Particle.PARTICLE_SIZE;
        System.out.println("Rows: " + rows + ", Cols:" + cols);
        grid = new Grid(rows, cols);

        canvas = new Canvas(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        add(canvas);
        pack();

        Timer timer = new Timer(TIMER_DELAY, e -> {
            grid.updateGrid();
            canvas.repaint();
        });
        timer.start();
    }

}
