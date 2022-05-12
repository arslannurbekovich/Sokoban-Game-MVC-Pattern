import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class Canvas extends JPanel {
    private  Model model;
    private Image imageGamer;
    private Image imageGamerBack;
    private Image imageGamerRight;
    private Image imageGamerLeft;
    private Image imageWall;
    private Image imageBox;
    private Image imageGoal;
    private Image imageError;
    private Image imageSnow;
    private Levels levels;
    public Canvas(Model model) {
        this.model = model;
        setBackground(new Color(35,46,40));
        setOpaque(true);
        levels = new Levels();
        File fileNameImageGamer = new File("images/player.png");
        File fileNameImageGamerBack = new File("images/player-b.png");
        File fileNameImageGamerRight = new File("images/player-r.png");
        File fileNameImageGamerLeft = new File("images/player-l.png");
        File fileNameImageWall = new File("images/wall.jpg");
        File fileNameImageBox = new File("images/box.png");
        File fileNameImageGoal = new File("images/goal.png");
        File fileNameImageError = new File("images/error.png");
        File fileNameImageSnow = new File("images/snow.png");
        try {
            imageGamer = ImageIO.read(fileNameImageGamer);
            imageGamerBack = ImageIO.read(fileNameImageGamerBack);
            imageGamerRight = ImageIO.read(fileNameImageGamerRight);
            imageGamerLeft = ImageIO.read(fileNameImageGamerLeft);
            imageWall = ImageIO.read(fileNameImageWall);
            imageBox = ImageIO.read(fileNameImageBox);
            imageGoal = ImageIO.read(fileNameImageGoal);
            imageError = ImageIO.read(fileNameImageError);
            imageSnow = ImageIO.read(fileNameImageSnow);
        } catch (IOException ioe) {
            System.out.println("Not Found Image " + ioe);
        }
    }
    public void drawDesktop(Graphics graphics) {
        int start = 50;
        int x = start;
        int y = start;
        int width = 50;
        int height = 50;
        int offset = 10;
        int [][] desktop = model.getDesktop();
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 1) {
                    switch (model.getDirection()) {
                        case "face" :
                            graphics.drawImage(imageGamer,x,y,null);
                            break;
                        case "back" :
                            graphics.drawImage(imageGamerBack,x,y,null);
                            break;
                       case "right" :
                            graphics.drawImage(imageGamerRight,x,y,null);
                            break;
                        case "left" :
                            graphics.drawImage(imageGamerLeft,x,y,null);
                            break;
                    }
                } else if (desktop[i][j] == 2) {
                    graphics.drawImage(imageWall, x, y, null);
                } else if (desktop[i][j] == 3) {
                    graphics.drawImage(imageBox, x, y, null);
                } else if (desktop[i][j] == 4) {
                    graphics.drawImage(imageGoal, x, y, null);
                } else if (desktop[i][j] == 0) {
                    graphics.drawImage(imageSnow, x, y, null);
                }
                x = x + width + offset;
            }
            x = start;
            y = y + height + offset;
        }
    }
    public void paint(Graphics graphics){
        super.paint(graphics);
        if (!model.isOk) {
           drawError(graphics);
            return;
        }
        drawDesktop(graphics);
        drawLevel(graphics);
    }
    private void drawError(Graphics graphics) {
        graphics.drawImage(imageError,250,60,null);
        graphics.setFont(new Font("Berlin Sans FB Demi",Font.BOLD,40));
        graphics.setColor(new Color(250,25,25));
        graphics.drawString("Error!Level has an incorrect structure!",35,400);
    }
    private void drawLevel(Graphics graphics) {
        graphics.setFont(new Font("Arial Black",Font.BOLD,40));
        graphics.setColor(new Color(200,200,200));
        graphics.drawRect(230,690,250,50);
        graphics.drawString("LEVEL: " + model.getLevel(),250,730);
    }
}
