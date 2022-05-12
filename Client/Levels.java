import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class Levels {
    private int level;
    private final String HOST_NAME;
    private final int PORT_NUMBER;
    public Levels() {
        HOST_NAME = "194.152.37.7";
        PORT_NUMBER = 4445;
        level = 1;
    }
    public int[][] nextLevel() {
        int[][] desktop = null;
        switch (level) {
            case 1: desktop = getFirstLevel();
                break;
            case 2: desktop = getSecondLevel();
                break;
            case 3: desktop = getThirdLevel();
                break;
            case 4: try {
                    desktop = getFileLevel("levels/level4.sok");
                } catch (Exception exc) {
                    this.level = 1;
                    desktop = getFirstLevel();
                }
                break;
            case 5: try {
                    desktop = getFileLevel("levels/level5.sok");
                } catch (Exception exc) {
                    this.level = 1;
                    desktop = getFirstLevel();
                }
                break;
            case 6: try {
                    desktop = getFileLevel("levels/level6.sok");
                } catch (Exception exc) {
                    this.level = 1;
                    desktop = getFirstLevel();
                }
                break;
            case 7: desktop = getServerLevels("7");
                break;
            case 8: desktop = getServerLevels("8");
                break;
            case 9: desktop = getServerLevels("9");
                break;
            default: desktop = getFirstLevel();
                break;
        }
        level = level + 1;
        return desktop;
    }
    private int[][] getFirstLevel() {
        int[][] levelFirst = new int[][]{
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {0, 2, 0, 1, 0, 0, 0, 3, 4, 2},
                {0, 2, 0, 2, 2, 2, 2, 3, 2, 2},
                {0, 2, 0, 2, 0, 0, 2, 0, 2, 0},
                {0, 2, 3, 2, 0, 0, 2, 4, 2, 0},
                {0, 2, 0, 2, 0, 0, 2, 2, 2, 0},
                {0, 2, 0, 2, 0, 0, 0, 0, 0, 0},
                {0, 2, 4, 2, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return levelFirst;
    }
    private int[][] getSecondLevel() {
        int[][] levelSecond = new int[][]{
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 0, 0, 0, 2, 4, 0, 0, 0, 2},
                {2, 0, 0, 0, 2, 3, 0, 0, 0, 2},
                {2, 0, 0, 2, 2, 0, 2, 0, 0, 2},
                {2, 4, 0, 0, 3, 1, 2, 2, 2, 2},
                {2, 2, 2, 2, 3, 0, 0, 3, 4, 2},
                {2, 0, 0, 2, 0, 2, 2, 0, 0, 2},
                {2, 0, 0, 0, 0, 2, 0, 0, 0, 2},
                {2, 0, 0, 0, 4, 2, 0, 0, 0, 2},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        return levelSecond;
    }
    private int[][] getThirdLevel() {
        int[][] levelThird = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 0, 0, 0},
                {0, 2, 1, 0, 0, 0, 2, 2, 0, 0},
                {0, 2, 0, 3, 3, 0, 0, 2, 0, 0},
                {0, 2, 0, 2, 4, 0, 4, 2, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

        };
        return levelThird;
    }
    private int[][] getFileLevel(String fileName) throws Exception {
        File file = new File(fileName);
        String contentFile = getContentFile(file);
        int[][] desktop = convertStringIntoTwoDimensionArray(contentFile);
        return desktop;
    }
    private int[][] convertStringIntoTwoDimensionArray(String line) {
        int n = line.length();
        int row = 0;
        for (int i = 0; i < n; i++) {
            char symbol = line.charAt(i);
            if (symbol == '\n') {
                row = row + 1;
            }
        }
        int[][] array = new int[row][];
        int column = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            char symbol = line.charAt(i);
            if (symbol != '\n') {
                column = column + 1;
            } else if (symbol == '\n') {
                array[index] = new int[column];
                index = index + 1;
                column = 0;
            }
        }
        row = 0;
        column = 0;
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (symbol != '\n') {
                array[row][column] = Integer.parseInt("" + symbol);
                column = column + 1;
            } else if (symbol == '\n') {
                row = row + 1;
                column = 0;
            }
        }
        return array;
    }
    private String getContentFile(File file) throws Exception {
        try (FileInputStream in = new FileInputStream(file)) {
            int size = (int) file.length();
            char[] array = new char[size];
            int index = 0;
            int unicode;
            while ((unicode = in.read()) != -1) {
                char symbol = (char) unicode;
                if (('0' <= symbol && symbol <= '4') || (symbol == '\n')) {
                    array[index] = symbol;
                    index = index + 1;
                }
            }
            String content = new String(array, 0, index);
            return content;
        } catch (FileNotFoundException fnfe) {
            throw new Exception("File Not Found Exception: " + fnfe);
        } catch (IOException ioe) {
            throw new Exception("Basic I/O Exception: " + ioe);
        }
    }
    private int[][] getServerLevels(String level) {
        int[][] desktop;
        try (
                Socket socket = new Socket(HOST_NAME, PORT_NUMBER);
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            writer.println(level);
            writer.flush();
            desktop = (int[][]) in.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            this.level = 1;
            return getFirstLevel();
        }
        return desktop;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
}

