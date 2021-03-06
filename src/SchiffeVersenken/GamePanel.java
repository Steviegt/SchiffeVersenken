package SchiffeVersenken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseListener {

    public static boolean placed = false;
    public static int len = 2;
    public static int schiffid = 0;
    public static int hits = 0;
    public static int waters = 0;
    public static ArrayList<Integer> contains;
    public static ArrayList<Integer> hitpoints = new ArrayList<>();
    JLabel remaining = new JLabel("Du hast noch " + (30 - waters) + " Fehlversuche und dir fehlen noch " + (14 - hits) + " Treffer!");
    JButton restart = new JButton("Restart");


    public GamePanel() {
        setBackground(Color.CYAN);
        requestFocus();
        addMouseListener(this);
        restart.addActionListener((e) -> {
            String[] options = {"Ja", "Nein"};
            int a = JOptionPane.showOptionDialog(this, "Sind Sie sicher, dass sie neu Starten wollen?", "Sind Sie sicher?!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
            if (a == 0) {
                SchiffeVersenken.instance.initGame();
            }
        });
    }

    public void reset() {
        placed = false;
        len = 2;
        schiffid = 0;
        hits = 0;
        waters = 0;
        contains = new ArrayList<>();
        hitpoints = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //eigene Zeichnungen
        for (Field field : SchiffeVersenken.instance.getFields()) {
            field.draw(g2d);
        }
        remaining.setBounds(20, 507, 500, 50);
        restart.setBounds(380, 517, 100, 30);

        add(remaining);
        add(restart);
        if (!placed) {
            remaining.setText("Bitte Platziere die Schiffe!");
        } else {
            remaining.setText("Du hast noch " + (30 - waters) + " Fehlversuche und dir fehlen noch " + (14 - hits) + " Treffer!");
        }
        repaint();
    }


    public void checkField(int x, int y) {

        Rectangle cursorHitbox = new Rectangle(x, y, 1, 1);
        for (Field field : SchiffeVersenken.instance.getFields()) {
            if (cursorHitbox.intersects(field)) {
                if (field.getValue() == FieldValue.EMPTY) {
                    field.setValue(FieldValue.KEINTREFFER);
                    repaint();
                    waters++;
                } else if (field.getValue() == FieldValue.SCHIFF) {
                    field.setValue((FieldValue.TREFFER));
                    repaint();
                    hitpoints.add(field.getId());
                    hits++;
                }
                break;
            }
        }
    }

    public void placeShips(int x, int y) {
        Rectangle cursorHitbox = new Rectangle(x, y, 1, 1);
        for (Field field : SchiffeVersenken.instance.getFields()) {
            if (cursorHitbox.intersects(field)) {
                if (field.getValue() == FieldValue.EMPTY) {
                    field.setValue(FieldValue.MARKER);
                    repaint();
                    String[] options = {"Norden", "Osten", "S??den", "Westen"};
                    int a = JOptionPane.showOptionDialog(this, "Bitte w??hlen Sie die Himmelsrichtung aus, in die Ihr Schiff " + (len - 1) + " zeigen soll", "Bitte ausw??hlen!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    field.setValue(FieldValue.EMPTY);
                    if (a == 0) {
                        if (isImpossible(a, field)) {
                            JOptionPane.showMessageDialog(this, "Das ist so nicht m??glich! Bitte platzieren Sie ihr Schiff erneut!", "Unm??gliche Position", JOptionPane.ERROR_MESSAGE);
                            field.setValue(FieldValue.EMPTY);
                            repaint();
                        } else {
                            SchiffeVersenken.schiffe[schiffid] = new Schiff(len);
                            for (int i = 0; i < len; i++) {
                                SchiffeVersenken.schiffe[schiffid].contains[i] = field.getId() - (i * 10);
                                contains.add(field.getId() - (i * 10));
                                SchiffeVersenken.instance.getFields()[SchiffeVersenken.schiffe[schiffid].contains[i]].setValue(FieldValue.SCHIFF);
                                repaint();
                            }
                            schiffid++;
                            len++;
                        }
                    } else if (a == 1) {
                        if (isImpossible(a, field)) {
                            JOptionPane.showMessageDialog(this, "Das ist so nicht m??glich! Bitte platzieren Sie ihr Schiff erneut!", "Unm??gliche Position", JOptionPane.ERROR_MESSAGE);
                            field.setValue(FieldValue.EMPTY);
                            repaint();
                        } else {
                            SchiffeVersenken.schiffe[schiffid] = new Schiff(len);
                            for (int i = 0; i < len; i++) {

                                SchiffeVersenken.schiffe[schiffid].contains[i] = field.getId() + (i);
                                contains.add(field.getId() + (i));
                                SchiffeVersenken.instance.getFields()[SchiffeVersenken.schiffe[schiffid].contains[i]].setValue(FieldValue.SCHIFF);
                                repaint();
                            }
                            schiffid++;
                            len++;
                        }
                    } else if (a == 2) {
                        if (isImpossible(a, field)) {
                            JOptionPane.showMessageDialog(this, "Das ist so nicht m??glich! Bitte platzieren Sie ihr Schiff erneut!", "Unm??gliche Position", JOptionPane.ERROR_MESSAGE);
                            field.setValue(FieldValue.EMPTY);
                            repaint();
                        } else {
                            SchiffeVersenken.schiffe[schiffid] = new Schiff(len);
                            for (int i = 0; i < len; i++) {
                                SchiffeVersenken.schiffe[schiffid].contains[i] = field.getId() + (i * 10);
                                contains.add(field.getId() + (i * 10));
                                SchiffeVersenken.instance.getFields()[SchiffeVersenken.schiffe[schiffid].contains[i]].setValue(FieldValue.SCHIFF);
                                repaint();
                            }
                            schiffid++;
                            len++;
                        }
                    } else if (a == 3) {
                        if (isImpossible(a, field)) {
                            JOptionPane.showMessageDialog(this, "Das ist so nicht m??glich! Bitte platzieren Sie ihr Schiff erneut!", "Unm??gliche Position", JOptionPane.ERROR_MESSAGE);
                            field.setValue(FieldValue.EMPTY);
                            repaint();
                        } else {
                            SchiffeVersenken.schiffe[schiffid] = new Schiff(len);
                            for (int i = 0; i < len; i++) {
                                SchiffeVersenken.schiffe[schiffid].contains[i] = field.getId() - (i);
                                contains.add(field.getId() - (i));
                                SchiffeVersenken.instance.getFields()[SchiffeVersenken.schiffe[schiffid].contains[i]].setValue(FieldValue.SCHIFF);
                                repaint();
                            }
                            schiffid++;
                            len++;
                        }
                    }
                    if (len == 6) {
                        placed = true;
                        SchiffeVersenken.instance.visible = false;
                        repaint();
                    }
                }
            }
        }
    }

    public boolean isImpossible(int a, Field field) {
        //??berschneidung
        boolean possible = true;
        switch (a) {
            case 0 -> {
                for (int i = 0; i < len; i++) {
                    for (int in : contains) {
                        if (field.getId() - (i * 10) == in) {
                            possible = false;
                            break;
                        }
                    }
                }
            }
            case 1 -> {
                for (int i = 0; i < len; i++) {
                    for (int in : contains) {
                        if (field.getId() + (i) == in) {
                            possible = false;
                            break;
                        }
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < len; i++) {
                    for (int in : contains) {
                        if (field.getId() + (i * 10) == in) {
                            possible = false;
                            break;
                        }
                    }
                }
            }
            case 3 -> {
                for (int i = 0; i < len; i++) {
                    for (int in : contains) {
                        if (field.getId() - (i) == in) {
                            possible = false;
                            break;
                        }
                    }
                }
            }
        }
        //Rand

        switch (a) {
            case 0 -> {
                for (int i = 0; i < len; i++) {
                    if (field.getId() - (i * 10) < 0) {
                        possible = false;
                        break;
                    }
                }
            }
            case 1 -> {
                int row = field.getId() / 10;
                for (int i = 0; i < len; i++) {
                    if ((field.getId() + (i)) / 10 != row) {
                        possible = false;
                        break;
                    }
                }
            }
            case 2 -> {
                for (int i = 0; i < len; i++) {
                    if (field.getId() + (i * 10) > 99) {
                        possible = false;
                        break;
                    }
                }
            }
            case 3 -> {
                int row = field.getId() / 10;
                for (int i = 0; i < len; i++) {

                    if ((field.getId() - (i)) / 10 != row || (field.getId() - (i) < 0)) {
                        possible = false;
                        break;
                    }
                }
            }
        }
        return !possible;
    }

    public void checkWin() {
        if (hits == 14) {
            schiffVersenkt();
            JOptionPane.showMessageDialog(this, "Alle Schiffe versenkt! Gl??ckwunsch!", "WIN!", JOptionPane.INFORMATION_MESSAGE);
            SchiffeVersenken.instance.initGame();
            repaint();
        } else if (waters == 30) {
            SchiffeVersenken.instance.visible = true;
            repaint();
            JOptionPane.showMessageDialog(this, "Alle Versuche aufgebraucht! Viel Gl??ck n??chstes mal!", "GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
            SchiffeVersenken.instance.initGame();
            repaint();
        }

    }

    public void schiffVersenkt() {
        for (Schiff schiff : SchiffeVersenken.schiffe) {
            if (schiff != null) {
                schiff.versenkt(hitpoints);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (placed) checkField(e.getX(), e.getY());
        if (!placed) placeShips(e.getX(), e.getY());
        checkWin();
        schiffVersenkt();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}