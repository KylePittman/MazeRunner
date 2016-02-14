//Kyle Pittman 2/11/2016.
package Game.View;

import Game.Model.Entities.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Animation {

    private ArrayList<BufferedImage> animation;
    private Entity entity;
    private int index;
    private boolean oscillatingAnimation;
    private boolean up = true;

    /**
     *
     * @param entity
     * @throws IOException
     */
    public Animation(Entity entity, SpriteSheet sheet, boolean oscillatingAnimation) throws IOException {
        this.entity = entity;
        this.oscillatingAnimation = oscillatingAnimation;
        animation = new ArrayList<BufferedImage>();
        for (int i = entity.getSpriteRow(); i < entity.getSpriteRow() + entity.getNumOfSprites(); i++) {
            animation.add(sheet.getSprite(entity.getSpriteColumn(), i));
            System.out.println(i);
        }
        setIndex(0);
    }

    /**
     *
     * @param index
     * @return
     */
    public void setIndex(int index){
        this.index = index;
    }

    public void tick(){
        if (!oscillatingAnimation) {
            index++;
            if (index >= animation.size()) {
                index = 0;
            }
        }
        else{
            if (up){
                index++;
            }
            else {
                index--;
            }
            if (index == 0 || index == animation.size() - 1)
                up = !up;
        }
    }

    /**
     *
     * @param g
     */
    public void render(Graphics2D g){
        //g.translate(200,200);
        //g.rotate(Math.toRadians(90));
        g.drawImage(animation.get(index).getScaledInstance(200,200,0), entity.getX(), entity.getY(), null);
    }


}
