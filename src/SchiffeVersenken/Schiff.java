package SchiffeVersenken;

import java.util.ArrayList;

public class Schiff {
    private final int iD;
    private final int length;
    private Field start;
    public int[] contains;


    public Schiff(int l) {
        length = l;
        iD = l;
        contains = new int[l];
    }

    public void versenkt(ArrayList<Integer> containsall) {
            boolean versenkt = true;
            for (int i : contains) {
                boolean v2 = false;
                for (int ia : containsall) {
                    if (i == ia) v2 = true;
                }
                if (!v2) {
                    versenkt = false;
                    break;
                }
            }
            if(versenkt){
                for(int i : contains ){
                    SchiffeVersenken.instance.getFields()[i].setValue(FieldValue.VERSENKT);
                }
            }
        }
}
