import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;


public class Graphics extends JPanel implements ActionListener {

    ImageIcon[] headIcons = {
            new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\snake-game-java\\Picture\\snakeheadU.png"),
            new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\snake-game-java\\Picture\\snakeheadD.png"),
            new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\snake-game-java\\Picture\\snakeheadL.png"),
            new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\snake-game-java\\Picture\\snakeheadR.png")
    };
    ImageIcon flyIcon = new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\snake-game-java\\Picture\\fly.png");
    Image flyImage = flyIcon.getImage();
    ImageIcon bodyIcon = new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\snake-game-java\\Picture\\corp1.png");
    Image[] headImages = new Image[4];
    Image bodyImage = bodyIcon.getImage();
    static ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Acasa\\Desktop\\java pro\\snake-game-java\\Picture\\PictureDesert.jpeg");
    static Image backgroundImg = backgroundImage.getImage();

    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int TICK_SIZE = 50;
    static final int BOARD_SIZE = (WIDTH * HEIGHT) / (TICK_SIZE * TICK_SIZE);

    final Font font = new Font("TimesRoman", Font.BOLD, 30);

    int[] snakePosX = new int[BOARD_SIZE];
    int[] snakePosY = new int[BOARD_SIZE];
    int snakeLength;

    Food food;
    int foodEaten;

    char direction = 'R';
    boolean isMoving = false;
    final Timer timer = new Timer(150, this);

    public Graphics() {

        for (int i=0; i<4; i++) {
            headImages[i] = headIcons[i].getImage();
        }

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {


            @Override
            public void keyPressed(KeyEvent e) {
                if (isMoving) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') {
                                direction = 'D';
                            }
                            break;
                    }
                } else {
                    start();
                }
            }
        });

        start();
    }

    protected void start() {
        snakePosX = new int[BOARD_SIZE];
        snakePosY = new int[BOARD_SIZE];
        snakeLength = 5;
        foodEaten = 0;
        direction = 'R';
        isMoving = true;
        spawnFood();
        timer.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, this);

        if (isMoving) {
            switch (direction) {
                case 'U' -> g.drawImage(headImages[0], snakePosX[0], snakePosY[0], TICK_SIZE, TICK_SIZE, this);
                case 'D' -> g.drawImage(headImages[1], snakePosX[0], snakePosY[0], TICK_SIZE, TICK_SIZE, this);
                case 'L' -> g.drawImage(headImages[2], snakePosX[0], snakePosY[0], TICK_SIZE, TICK_SIZE, this);
                case 'R' -> g.drawImage(headImages[3], snakePosX[0], snakePosY[0], TICK_SIZE, TICK_SIZE, this);
            }
            g.drawImage(flyImage, food.getPosX(), food.getPosY(), TICK_SIZE, TICK_SIZE, null);

            for (int i = 1; i < snakeLength; i++) {
                g.drawImage(bodyImage, snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE, this);
            }
        } else {
            String scoreText = String.format("The End... Score: %d... Press any key to play again!", foodEaten);
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString(scoreText, (WIDTH - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, HEIGHT / 2);
        }
    }

    protected void move() {
        for (int i = snakeLength; i > 0; i--) {
            snakePosX[i] = snakePosX[i-1];
            snakePosY[i] = snakePosY[i-1];
        }

        switch (direction) {
            case 'U' -> snakePosY[0] -= TICK_SIZE;
            case 'D' -> snakePosY[0] += TICK_SIZE;
            case 'L' -> snakePosX[0] -= TICK_SIZE;
            case 'R' -> snakePosX[0] += TICK_SIZE;
        }
    }

    protected void spawnFood() {
        food = new Food();
    }

    protected void eatFood() {
        if ((snakePosX[0] == food.getPosX()) && (snakePosY[0] == food.getPosY())) {
            snakeLength++;
            foodEaten++;
            spawnFood();
        }
    }

    protected void collisionTest() {
        for (int i = snakeLength; i > 0; i--) {
            if ((snakePosX[0] == snakePosX[i]) && (snakePosY[0] == snakePosY[i])) {
                isMoving = false;
                break;
            }
        }

        if (snakePosX[0] < 0 || snakePosX[0] > WIDTH - TICK_SIZE || snakePosY[0] < 0 || snakePosY[0] > HEIGHT - TICK_SIZE) {
            isMoving = false;
        }

        if (!isMoving) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoving) {
            move();
            collisionTest();
            eatFood();
        }

        repaint();
    }



}
