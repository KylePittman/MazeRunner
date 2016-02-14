//Kyle Pittman 2/7/2016.
package Game.Model.Entities;

public class Player extends Entity {

    private int velX;
    private int velY;

    public Player(int x, int y, int spriteRow, int spriteColumn, int numOfSprites) {
        super(x, y, spriteRow, spriteColumn, numOfSprites);
    }

    public void tick(){
        this.setX(this.getX() + velX);
        this.setY(this.getY() + velY);
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
