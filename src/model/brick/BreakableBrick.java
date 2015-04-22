package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameField;
import model.IngameObject;
import model.Speed2D;
import model.collision.BehaviourDestroy;
import model.collision.BehaviourPaddleRebound;
import service.PublishingSprite;
import test.BehaviourDestroyTest;

/**
 * Модель разрушаемого кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BreakableBrick extends Brick implements Cloneable{

	public BreakableBrick (PublishingSprite sprite) {
            super(sprite);
            super.addDefaultBehaviuor(new BehaviourDestroy());
        }

    /**
	 * Разрушает кирпич.
	 */
	@Override
	public void destroy() {
	    super.destroy();
	}
	
	@Override
	public Object clone() {
            BreakableBrick clone = null;
            clone = (BreakableBrick) super.clone();
            return clone;
	}
}
