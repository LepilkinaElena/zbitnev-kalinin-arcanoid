package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D.Float;

import model.GameField;
import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.collision.BehaviourPaddleRebound;
import service.PublishingSprite;

/**
 * Модель абстрактного кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Brick extends IngameObject {

	public Brick (PublishingSprite sprite) {
            super(sprite);
        }

    
    public Brick clone(){   	
    	Brick clone = (Brick) super.clone();
    	return clone;
    }
}
