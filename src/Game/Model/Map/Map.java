//Kyle Pittman 2/13/2016.
package Game.Model.Map;

import java.awt.*;

public class Map {

    private Cell[][] map;

    public Map(int width, int height){
        map = new Cell[width][height];
    }

    public Cell getCell(int x, int y){
        return map[x][y];
    }

    public void setCell(int x, int y, Cell cell){
        map[x][y] = cell;
    }

    public int getWidth(){
        return map.length;
    }

    public int getHeight(){
        return map[0].length;
    }

    public void paint(Graphics g){
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int size = map[x][y].getSize();
                /**
                 * Walls
                 */
                if (map[x][y].getType() == 1){
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * size, y * size, size, size);
                    g.setColor(Color.black);

                    /**
                     * Bottom Left
                     */
                    if (!((x + 1) >= getWidth() || (y + 1) >= getHeight())){
                        if (map[x + 1][y].getType() == 1 &&  map[x][y + 1].getType() == 1 && map[x + 1][y + 1].getType() == 0){
                            g.fillRect(x * size + (size - 1) - size / 10, y * size + (size - 1) - size / 10, size / 10 + 1, size / 10 + 1);
                        }
                    }
                    /**
                     * Top Left
                     */
                    if (!((x + 1) >= getWidth() || (y - 1) < 0)){
                        if (map[x + 1][y].getType() == 1 &&  map[x][y - 1].getType() == 1 && map[x + 1][y - 1].getType() == 0){
                            g.fillRect(x * size + (size - 1) - size / 10, y * size, size / 10 + 1, size / 10 + 1);
                        }
                    }
                    /**
                     * Top Right
                     */
                    if (!((x - 1) < 0 || (y - 1) < 0)){
                        if (map[x - 1][y].getType() == 1 &&  map[x][y - 1].getType() == 1 && map[x - 1][y - 1].getType() == 0){
                            g.fillRect(x * size, y * size, size / 10 + 1, size / 10 + 1);
                        }
                    }
                    /**
                     * Bottom Right
                     */
                    if (!((x - 1) < 0 || (y + 1) >= getHeight())){
                        if (map[x - 1][y].getType() == 1 &&  map[x][y + 1].getType() == 1 && map[x - 1][y + 1].getType() == 0){
                            g.fillRect(x * size, y * size + (size - 1) - size / 10, size / 10 + 1, size / 10 + 1);
                        }
                    }

                    /**
                     * Fills lines on right side
                     */
                    if (!( (x + 1) >= getWidth() ) && map[x+1][y].getType() == 0){
                        for (int i = 0; i <= (size / 10); i++) {
                            g.drawLine(x * size + (size - i - 1), y * size ,x * size + (size - i - 1) , y * size + (size - 1));
                        }
                    }
                    /**
                     * Fills lines on left side
                     */
                    if (!((x - 1) < 0) && map[x-1][y].getType() == 0){
                        for (int i = 0; i <= (size / 10); i++) {
                            g.drawLine((x * size) + i, y * size, (x * size) + i, y * size + (size - 1));
                        }
                    }
                    /**
                     * Fills lines below
                     */
                    if (!( (y + 1) >= getHeight() )&& map[x][y+1].getType() == 0){
                        for (int i = 0; i <= (size / 10); i++) {
                            g.drawLine(x * size, y * size + (size - i - 1), x * size + (size - 1), y * size + (size - i - 1));
                        }
                    }

                    /**
                     * Fills lines above
                     */
                    if (!( (y - 1) < 0 ) && map[x][y-1].getType() == 0){
                        for (int i = 0; i <= (size / 10); i++) {
                            g.drawLine(x * size, (y* size) + i, (x*size) + (size - 1), (y * size) + i);
                        }
                    }
                }
                /**
                 * Path
                 */
                else{
                    g.setColor(Color.GRAY);
                    g.fillRect(x * size, y * size, size, size);
                }
            }
        }
    }
}
