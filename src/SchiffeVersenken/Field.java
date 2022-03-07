package SchiffeVersenken;

import java.awt.*;


public class Field extends Rectangle {

    private final int id;
    private FieldValue value;

    public Field(int x, int y, int a, int b, int id) {
        super(x, y, a, b);
        value = FieldValue.EMPTY;
        this.id = id;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x, y, 50, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, 50, 50);

        switch (value) {
            case TREFFER -> {
                g2d.setColor(Color.RED);
                g2d.fillRect(x, y, 50, 50);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, 50, 50);
            }
            case KEINTREFFER -> {
                g2d.setColor(Color.BLUE);
                g2d.fillRect(x, y, 50, 50);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, 50, 50);
            }
            case MARKER -> {
                g2d.setColor(Color.ORANGE);
                g2d.fillRect(x, y, 50, 50);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, 50, 50);
            }
            case SCHIFF -> {
                if (SchiffeVersenken.instance.visible) {
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(x, y, 50, 50);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x, y, 50, 50);
                } else {
                    g2d.setColor(Color.CYAN);
                    g2d.fillRect(x, y, 50, 50);
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x, y, 50, 50);

                }
            }
            case VERSENKT -> {
                g2d.setColor(Color.GREEN);
                g2d.fillRect(x, y, 50, 50);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, 50, 50);
            }
            case EMPTY -> {
            }

        }
    }

    public FieldValue getValue() {
        return value;
    }

    public void setValue(FieldValue val) {
        value = val;
    }

    public int getId() {
        return id;
    }
}
