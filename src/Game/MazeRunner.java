//Kyle Pittman 2/4/2016.
package Game;

import Game.Model.Entities.Entity;
import Game.Model.Entities.Player;
import Game.Model.Map.Map;
import Game.View.Animation;
import Game.View.RenderHandler;
import Game.View.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MazeRunner extends Canvas implements Runnable{

    private static final double AspectRatio = (12/9);
    private static int WIDTH = 320;
    private static int HEIGHT = (int) (WIDTH / AspectRatio);
    private static final double SCALE = 2.5;
    private final String TITLE = "Maze Runner";

    private RenderHandler handler;
    private Map map;
    private Player player;
    private Entity objective;
    private SpriteSheet sheet;

    private boolean running = false;
    private boolean timed = true;
    private Thread thread;
    private BufferedImage image = new BufferedImage((int) (WIDTH*SCALE), (int) (HEIGHT*SCALE), BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args) throws IOException {
        MazeRunner game = new MazeRunner();

        game.setPreferredSize(new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE)));
        game.setMinimumSize(new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE)));
        game.setMaximumSize(new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE)));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public MazeRunner() throws IOException {
        init();
    }

    private synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop(){
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void init() throws IOException {
        handler = new RenderHandler();
        sheet = new SpriteSheet("View\\SpriteSheet.png");
        if (timed)
            objective = new Entity(0,1,8);
        else
            objective = new Entity(5,0,3);
        objective.setX(10);
        objective.setY(10);

        player = new Player(300, 300, 0, 0, 5);
        handler.addAnimation(new Animation(player, sheet, true));
        handler.addAnimation(new Animation(objective, sheet, !timed));
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ticksPerSecond = 20.0;
        double ns = 1000000000 / ticksPerSecond;
        double delta = 0.0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += ((now - lastTime) / ns);
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                System.out.println("FPS: " + frames);
                frames = 0;
                System.out.println("UPS: " + updates);
                updates = 0;
                timer+=1000;
            }

        }
        this.stop();
    }

    public void tick(){
        handler.tick();
    }

    public void render(){

        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        handler.render((Graphics2D) g);


        g.dispose();
        bs.show();
    }

    public void loadMap(String file)throws IOException{
        int height = 0, width = 0;

        String pathToFile = "C:\\Java Workspace\\MazeRunner\\src\\Resources\\PresetMaps";
        File inFile = new File(pathToFile, file);
        Scanner inData = new Scanner(inFile);

        if (inData.hasNextInt()){
            width = inData.nextInt();
        }

        if (inData.hasNextInt()){
            height = inData.nextInt();
        }

        Map tempMap = new Map(width, height, HEIGHT/height);

        for (int y = 0; y < height && inData.hasNextLine(); y++) {
            for (int x = 0; x < width && inData.hasNextInt(); x++) {
                tempMap.setCellType(x,y,inData.nextInt());
            }
            inData.nextLine();
        }
    }
}
