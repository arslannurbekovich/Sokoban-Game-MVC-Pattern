public class Model {
    private Viewer viewer;
    private int [][] desktop;
    private int [][] arrayIndexesGoal;
    private int indexX;
    private int indexY;
    private Levels levels;
    public boolean isOk;
    private Sound sound;
    private String command;
    public Model(Viewer viewer) {
        this.viewer = viewer;
        isOk = true;
        levels = new Levels();
        sound = new Sound();
        desktop = levels.nextLevel();
        command = "face";
        initialization();
    }
    private void initialization() {
        arrayIndexesGoal = new int[desktop.length][desktop[0].length];
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j < desktop[i].length; j++) {
                if (desktop[i][j] == 3 || desktop[i][j] == 4 )
                arrayIndexesGoal[i][j] = desktop[i][j];
            }
        }
        if (isErrorState()) {
            for (int i = 0; i < desktop.length; i++) {
                for (int j = 0; j < desktop[i].length; j++) {
                    if (desktop[i][j] == 1) {
                        indexX = i;
                        indexY = j;
                        desktop[indexX][indexY] = 1;
                    }
                }
            }
        }
        getLevel();
    }
    public void move(String direction) {
        if (direction.equals("Up")) {
            moveUp();
            command = "back";
        } else if (direction.equals("Right")) {
            moveRight();
            command = "right";
        } else if (direction.equals("Down")) {
            moveDown();
            command = "face";
        } else if (direction.equals("Left")){
            moveLeft();
            command = "left";
        } else {
            return;
        }
        checkGoal();
        viewer.update();
        won();
    }
    private void checkGoal() {
        for (int i = 0; i < arrayIndexesGoal.length; i++) {
            for (int j = 0; j < arrayIndexesGoal[i].length; j++) {
                if (arrayIndexesGoal[i][j] == 4) {
                    if (desktop[i][j] == 0) {
                        desktop[i][j] = 4;
                    }
                }
            }
        }
    }
    public void won() {
        boolean isWon = true;
        for (int i = 0; i <arrayIndexesGoal.length ; i++) {
            for (int j = 0; j <arrayIndexesGoal[i].length ; j++) {
                if (arrayIndexesGoal[i][j] == 4) {
                    if (desktop[i][j] != 3) {
                        isWon = false;
                    }
                }
            }
        }
        if (isWon) {
            if (levels.getLevel() > 9) {
                viewer.showDialogWon();
                viewer.showDialogGameOver();
                levels.setLevel(1);
                init();
            } else {
                viewer.showDialogWon();
                init();
            }
        }
    }
    private void moveUp() {
        if (desktop[indexX - 1][indexY] == 3 ) {
            if (desktop[indexX - 2][indexY] == 0 || desktop[indexX - 2][indexY] == 4 ) {
                desktop[indexX - 1][indexY] = 0;
                desktop[indexX - 2][indexY] = 3;
            }
        }
        if (desktop[indexX - 1][indexY] == 0 || desktop[indexX - 1][indexY] == 4 ) {
            desktop[indexX][indexY] = 0;
            indexX = indexX - 1;
            desktop[indexX][indexY] = 1;
        }
    }
    private void moveRight() {
        if (desktop[indexX][indexY + 1] == 3 ) {
            if (desktop[indexX][indexY + 2] == 0 || desktop[indexX][indexY + 2] == 4 ) {
                desktop[indexX][indexY + 1] = 0;
                desktop[indexX][indexY + 2] = 3;
            }
        }
        if (desktop[indexX][indexY + 1] == 0 || desktop[indexX][indexY + 1] == 4 ) {
            desktop[indexX][indexY] = 0;
            indexY = indexY + 1;
            desktop[indexX][indexY] = 1;
        }
    }
    private void moveDown() {
        if (desktop[indexX + 1][indexY] == 3 ) {
            if (desktop[indexX + 2][indexY] == 0 || desktop[indexX + 2][indexY] == 4 ) {
                desktop[indexX + 1][indexY] = 0;
                desktop[indexX + 2][indexY] = 3;
            }
        }
        if (desktop[indexX + 1][indexY] == 0 || desktop[indexX + 1][indexY] == 4) {
            desktop[indexX][indexY] = 0;
            indexX = indexX + 1;
            desktop[indexX][indexY] = 1;
        }
    }
    private void moveLeft() {
        if (desktop[indexX][indexY - 1] == 3 ) {
            if (desktop[indexX][indexY - 2] == 0 || desktop[indexX][indexY - 2] == 4 ) {
                desktop[indexX][indexY - 1] = 0;
                desktop[indexX][indexY - 2] = 3;
            }
        }
        if (desktop[indexX][indexY - 1] == 0 || desktop[indexX][indexY - 1] == 4 ) {
            desktop[indexX][indexY] = 0;
            indexY = indexY - 1;
            desktop[indexX][indexY] = 1;
        }
    }
    public int[][] getDesktop() {
        return desktop;
    }
    public boolean isErrorState() {
        int box = 0;
        int goal = 0;
        int player = 0;
        for (int i = 0; i < desktop.length; i++) {
            for (int j = 0; j <desktop[i].length ; j++) {
                if (desktop[i][j] == 3) {
                    box = box + 1;
                } else if (desktop[i][j] == 4){
                    goal = goal + 1;
                } else if (desktop[i][j] == 1){
                    player = player + 1;
                }
            }
        }
        if ((box != goal) || (box == 0) || (player != 1)) {
            isOk = false;
        }
        return isOk;
    }
    public void doAction(String command) {
        if (command.equals("exit")) {
            System.exit(0);
        }
        else if (command.equals("on")) {
                sound.loadSound();
                viewer.musicOnItem.setEnabled(false);
                viewer.musicOffItem.setEnabled(true);
            }
        else if(command.equals("off")) {
                sound.myStop();
                viewer.musicOnItem.setEnabled(true);
                viewer.musicOffItem.setEnabled(false);
            }
        else{
            switch (command) {
                case "1" :
                    levels.setLevel(1);
                    break;
                case "2" : levels.setLevel(2);
                    break;
                case "3" : levels.setLevel(3);
                    break;
                case "4" : levels.setLevel(4);
                    break;
                case "5" : levels.setLevel(5);
                    break;
                case "6" : levels.setLevel(6);
                    break;
                case "7" : levels.setLevel(7);
                    break;
                case "8" : levels.setLevel(8);
                    break;
                case "9" : levels.setLevel(9);
                    break;
                case "restart" : levels.setLevel(levels.getLevel() - 1);
                    break;
                default: levels.nextLevel();
            }
            init();
        }
    }
    public int getLevel() {
        return levels.getLevel() - 1;
    }
    public String getDirection() {
        return command;
    }
    private void init(){
        desktop = levels.nextLevel();
        initialization();
        viewer.update();
    }
}
