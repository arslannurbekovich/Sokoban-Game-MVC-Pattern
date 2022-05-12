import java.io.Serializable;

public class Desktop implements Serializable {
    private int[][] desktop;
    public Desktop() {
        desktop = new int[][] {};
    }
    public int[][] getDesktop() {
        if(MyClient.level.equals("7")) {
            desktop = getSeventhLevel();
        } else if(MyClient.level.equals("8")) {
            desktop = getEighthLevel();
        } else if (MyClient.level.equals("9")) {
            desktop = getNinthLevel();
        }
        return desktop;
    }
    public int[][] getSeventhLevel() {
        int[][] seventhLevel = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 2, 2, 2, 2, 0, 0, 0, 0, 0},
                {2, 0, 0, 1, 2, 0, 0, 0, 0, 0},
                {2, 0, 3, 3, 2, 0, 2, 2, 2, 0},
                {2, 0, 3, 0, 2, 0, 2, 4, 2, 0},
                {2, 2, 2, 0, 2, 2, 2, 4, 2, 0},
                {0, 2, 2, 0, 0, 0, 0, 4, 2, 0},
                {0, 2, 0, 0, 0, 2, 0, 0, 2, 0},
                {0, 2, 0, 0, 0, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 0, 0, 0, 0}
        };
        return seventhLevel;
    }
    public int[][] getEighthLevel() {
        int[][] eighthLevel = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 0, 0, 0},
                {0, 2, 0, 0, 4, 0, 2, 0, 0, 0},
                {0, 2, 0, 2, 4, 0, 2, 2, 0, 0},
                {0, 2, 0, 0, 4, 0, 0, 2, 0, 0},
                {0, 2, 0, 3, 3, 3, 0, 2, 0, 0},
                {0, 2, 0, 0, 2, 1, 0, 2, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return eighthLevel;
    }
    public int[][] getNinthLevel() {
        int[][] ninthLevel = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 0, 0},
                {0, 2, 0, 0, 4, 0, 0, 2, 2, 0},
                {0, 2, 0, 3, 3, 2, 4, 0, 2, 0},
                {0, 2, 0, 2, 1, 3, 0, 0, 2, 0},
                {0, 2, 0, 0, 4, 2, 0, 0, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        return ninthLevel;
    }
}
