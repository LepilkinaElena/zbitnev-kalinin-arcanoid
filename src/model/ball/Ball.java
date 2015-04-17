package model.ball;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import model.GameField;
import model.IngameObject;
import model.Speed2D;
import model.collision.BehaviourPaddleRebound;
import model.collision.BehaviourRebound;
import model.paddle.Paddle;
import service.PublishingSprite;

/**
 * Модель абстрактного шарика
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Ball extends IngameObject implements Cloneable {

	public Ball (PublishingSprite sprite) {
            super(sprite);
        }
        
        public Ball (PublishingSprite sprite, Speed2D speed2D) {
            super(sprite, speed2D);
        }
	
	@Override
	public void setPosition(Point2D.Float pos) {
	    
	    super.setPosition(pos);
	}
	
	@Override
	public Object clone()  {
		
            Ball clone = (Ball) super.clone();
            return clone;
	}
}
