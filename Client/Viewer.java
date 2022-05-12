import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
public class Viewer {
    private Canvas canvas;
    private Model model;
    private JFrame frame;
    private JPanel menuPanel;
    private JMenuBar menuBar;
    JMenuItem musicOnItem;
    JMenuItem musicOffItem;
    public Viewer() {
        Controller controller = new Controller(this);
        model =  controller.getModel();
        canvas = new Canvas(model);
        menuBar = new JMenuBar();
        initMenuEdit(controller);
        initMenuLevels(controller);
        initMenuSound(controller);

        frame = new JFrame("Sokoban Game MVC");
        frame.setSize(750,850);
        frame.add("Center",canvas);
        frame.setLocation(500,100);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addKeyListener(controller);
        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(menuBar, BorderLayout.NORTH);
        frame.add(menuPanel,BorderLayout.NORTH);
        java.awt.Image iconLogo = java.awt.Toolkit.getDefaultToolkit().getImage("images/logo.png");
        frame.setIconImage(iconLogo);
    }
    public boolean showDialogWon() {
        JOptionPane.showMessageDialog(frame, "You are won!!!", "Sokoban-MVC",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("./images/won.png"));
        return true;
    }
    public boolean showDialogGameOver() {
        int choice = 0;
        choice = JOptionPane.showConfirmDialog(null,
                "Game over! Want to play again?","Sokoban-MVC",JOptionPane.YES_NO_OPTION);
        if(choice==1)System.exit(0);
        else if(choice==0){
        }
        return true;
    }
    private void initMenuSound(Controller controller) {
        JMenu soundMenu = new JMenu("Music");
        soundMenu.setFont(initFont(18));
        musicOnItem = createMenuItem("Music On", controller,"on");
        musicOnItem.setIcon(new ImageIcon("./images/music-on.png"));
        musicOnItem.setFont(initFont(12));
        musicOnItem.setEnabled(true);
        musicOffItem = createMenuItem("Music Off", controller,"off");
        musicOffItem.setIcon(new ImageIcon("./images/music-off.png"));
        musicOffItem.setEnabled(false);
        musicOffItem.setFont(initFont(12));
        soundMenu.add(musicOnItem);
        soundMenu.add(musicOffItem);
        menuBar.add(soundMenu);
    }
    private void initMenuEdit(Controller controller) {
        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(initFont(18));
        JMenuItem editItemRestart = createMenuItem("Restart", controller,"restart");
        editItemRestart.setFont(initFont(12));
        JMenuItem editItemExit = createMenuItem("Exit", controller,"exit");
        editItemExit.setFont(initFont(12));
        editMenu.add(editItemRestart);
        editMenu.add(editItemExit);
        menuBar.add(editMenu);
    }
    private void initMenuLevels(Controller controller) {
        JMenu levelsMenu = new JMenu("Levels");
        levelsMenu.setFont(initFont(18));
        for (int i = 1; i <= 9; i++) {
            JMenuItem levelItem = createMenuItem("Level " + i, controller,"" + i);
            levelItem.setFont(initFont(12));
            levelsMenu.add(levelItem);
        }
        menuBar.add(levelsMenu);
    }
    private JMenuItem createMenuItem(String name,Controller controller, String comand) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(controller);
        menuItem.setActionCommand(comand);
        return menuItem;
    }
    private Font initFont(int size) {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, new java.io.File("font/Gameplay Regular.ttf")).deriveFont(Font.PLAIN,size);
            } catch (java.awt.FontFormatException | IOException e) {
                e.printStackTrace();
            }
            return null;
    }
    public void update() {
        canvas.repaint();
    }
}
