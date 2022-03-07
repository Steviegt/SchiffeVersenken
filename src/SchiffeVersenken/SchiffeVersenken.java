package SchiffeVersenken;

public class SchiffeVersenken {

    public static SchiffeVersenken instance;
    public static Schiff[] schiffe;
    private final Field[] fields = new Field[100];
    public boolean visible;
    public boolean setup;


    public SchiffeVersenken() {
        setup = true;
        new GameWindow(550, 600);
        initGame();

    }

    public static void main(String[] args) {
        instance = new SchiffeVersenken();
    }

    public void initGame() {


        schiffe = new Schiff[4];

        GameWindow.gamePanel.reset();

        setup = true;
        visible = true;

        int fieldMarginLeft = 15;
        int fieldMarginTop = 5;
        int fieldWidth = 50;
        int fieldHeight = 50;
        int a = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fields[a] = new Field(fieldMarginLeft + j * fieldWidth, fieldMarginTop + i * fieldHeight, fieldWidth, fieldHeight, a);
                a++;
            }
        }
    }

    public Field[] getFields() {
        return fields;
    }
}
