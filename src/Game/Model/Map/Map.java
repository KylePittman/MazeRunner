//Kyle Pittman 2/13/2016.
package Game.Model.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The <code>Map</code> class is a 2D array that contains
 * paint-able <code>Cell</code>s. The class allows three
 * types of <code>Cell</code>s: -1, 0, 1. This correspond
 * to null cells, path cells, and wall cells respectively.
 * It is possible to set the <code>Color</code> of the:
 * wall, path, border, null Cell, and null Cell border
 * The border goes between the wall and path on the wall
 * side
 *
 * It is possible to get and set properties of individual
 * <code>Cell</code>s except for size, which must be the
 * same across all
 */
public class Map {

    private Color wall = Color.DARK_GRAY;
    private Color path = Color.GRAY;
    private Color border = Color.BLACK;
    private Color nullCell = Color.WHITE;
    private Color nullCellBorder = Color.BLACK;

    private ArrayList<Point> objectiveLocations;
    private Cell[][] map;

    /**
     * Constructor for a <code>Map</code>
     * @param width     the width of the map (x)
     * @param height    the height of the map(y)
     * @param cellSize  the size of the <code>Cell</code>s
     */
    public Map(int width, int height, int cellSize){
        map = new Cell[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setCell(x, y, new Cell(cellSize, -1));
            }
        }
    }

    /**
     * This will get the <code>Cell</code> at
     * position x,y in the map
     * @param x x coordinate of <code>Cell</code>
     * @param y y coordinate of <code>Cell</code>
     * @return  <code>Cell</code> at <code>map[x][y]</code>
     */
    public Cell getCell(int x, int y){
        return map[x][y];
    }

    /**
     * This will set <code>map[x][y]</code>
     * to <code>Cell</code>
     * @param x     x coordinate of <code>Cell</code> to set
     * @param y     y coordinate of <code>Cell</code> to set
     * @param cell  cell to set <code>map[x][y]</code> to
     */
    public void setCell(int x, int y, Cell cell){
        map[x][y] = cell;
    }

    /**
     * This will set the <code>Cell</code> at
     * <code>map[x][y]</code> to type
     * @param x     x coordinate of <code>Cell</code>
     * @param y     y coordinate of <code>Cell</code>
     * @param type  type to set <code>Cell</code> to
     */
    public void setCellType(int x, int y, int type){
        map[x][y].setType(type);
    }

    /**
     * This will get the width of the map
     * @return  int width of map
     */
    public int getWidth(){
        return map.length;
    }

    /**
     * This will get the height of the map
     * @return  int height of map
     */
    public int getHeight(){
        return map[0].length;
    }

    /**
     * paint method to paint the map
     * All <code>Cell</code>s of
     *
     *      type 0 will be painted
     *      as a square with sides of length
     *      <code>getCellSize()</code> of
     *      <code>Color</code> <code>path</code>
     *
     *      type 1 with <code>Color</code>
     *      <code>wall</code> with an inside border
     *      between wall and path of <code>Color</code>
     *      <code>border</code>
     *
     *      type -1 with <code>Color</code>
     *      <code>nullCell</code> with inside border
     *      <code>Color</code> <code>nullCellBorder</code>
     *
     * @param g <code> Graphics </code> to draw on
     */
    public void paint(Graphics g){
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int size = map[x][y].getSize();
                /**
                 * Walls
                 */
                if (map[x][y].getType() == 1){
                    g.setColor(wall);
                    g.fillRect(x * size, y * size, size, size);
                    g.setColor(border);
                    /**
                     * Begin Corner lines
                     */

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
                     * End Corner Lines
                     */

                    /**
                     * Begin Side Lines
                     */

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

                    /**
                     * End Side Lines
                     */
                }
                /**
                 * Path
                 */
                else if (map[x][y].getType() == 0){
                    g.setColor(path);
                    g.fillRect(x * size, y * size, size, size);
                }
                /**
                 * Null Cell
                 */
                else{
                    g.setColor(nullCell);
                    g.fillRect(x * size, y * size, size, size);
                    g.setColor(nullCellBorder);
                    g.drawRect(x * size, y * size, size, size);
                }
            }
        }
    }

    /**
     * This adds a point to the objective
     * points <code>ArrayList</code>
     * @param point <code>Point</code> to add to <code>ArrayList</code>
     */
    public void addObjectiveLocation(Point point){
        objectiveLocations.add(point);
    }

    /**
     * This adds a point of coordinates x, y
     * to the objective points <code>ArrayList</code>
     * @param x x coordinate of objective
     * @param y y coordinate of objective
     */
    public void addObjectiveLocation(int x, int y){
        objectiveLocations.add(new Point(x, y));
    }

    /**
     * Sets the <code>Color</code> of
     * wall to wall
     * @param wall Color to set wall
     */
    public void setWallColor(Color wall) {
        this.wall = wall;
    }

    /**
     * Sets the <code>Color</code> of
     * path to path
     * @param path Color to set path
     */
    public void setPathColor(Color path) {
        this.path = path;
    }

    /**
     * Sets the <code>Color</code> of
     * border to border
     * @param border Color to set border
     */
    public void setBorderColor(Color border) {
        this.border = border;
    }

    /**
     * Sets the <code>Color</code> of
     * nullCell to nullCell
     * @param nullCell Color to set nullCell
     */
    public void setNullCellColor(Color nullCell) {
        this.nullCell = nullCell;
    }

    /**
     * Sets the <code>Color</code> of
     * nullCellBorder to nullCellBorder
     * @param nullCellBorder Color to set nullCellBorder
     */
    public void setNullCellBorderColor(Color nullCellBorder) {
        this.nullCellBorder = nullCellBorder;
    }

    /**
     * Sets the size of all cells in map to size
     * @param size size to set cells to
     */
    public void setCellSize(int size){
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                map[x][y].setSize(size);
            }
        }
    }

    /**
     * This will return the <code>Cell</code>
     * size of the cell at 0,0. It should be
     * the same as the rest of the cells sizes
     * @return int size
     */
    public int getCellSize(){
        return map[0][0].getSize();
    }

    public Point getRandomObjectiveLocation(){
        Random r = new Random();
        return objectiveLocations.get(r.nextInt(objectiveLocations.size()));
    }

}
