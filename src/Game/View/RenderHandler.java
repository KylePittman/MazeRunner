//Kyle Pittman 2/7/2016.
package Game.View;

import java.awt.*;
import java.util.ArrayList;

/**
 * This Class will hold all update-able entities
 * in the game and update them as necessary
 */
public class RenderHandler {

    private ArrayList<Animation> animations;

    /**
     * Initialize entities ArrayList
     */
    public RenderHandler(){
        animations = new ArrayList<Animation>();
    }

    /**
     * Add entity to List
     * @param animation to be added
     */
    public void addAnimation(Animation animation){
        animations.add(animation);
    }

    /**
     * Remove entity from List
     * @param index of element to be removed
     */
    public void removeEntity(int index){
        animations.remove(index);
    }

    public void render(Graphics2D g){
        for (Animation animation : animations){
            animation.render(g);
        }
    }

    public void tick(){
        for(Animation animation : animations){
            animation.tick();
        }
    }
}
