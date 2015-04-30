package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D.Float;

import model.GameField;
import model.Speed2D;
import model.collision.BehaviourDestroy;
import service.PublishingSprite;

/**
 * Модель неразрушаемого кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class UnbreakableBrick extends Brick {

	public UnbreakableBrick (PublishingSprite sprite) {
            super(sprite);
            super.addDefaultBehaviuor(new BehaviourDestroy());
        }
    
    public UnbreakableBrick clone() {
    	UnbreakableBrick clone = (UnbreakableBrick) super.clone();
    	return clone;
    }

}
