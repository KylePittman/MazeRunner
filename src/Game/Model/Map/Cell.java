//Kyle Pittman 2/13/2016.
package Game.Model.Map;

public class Cell {


    private int size;
    private int type;


    public Cell(int size, int type){
        this.size = size;
        this.type = type;
    }


    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }
}
