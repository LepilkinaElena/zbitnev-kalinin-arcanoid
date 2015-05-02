package model.paddle;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import model.GameField;
import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.ball.Ball;
import model.collision.BehaviourPaddleRebound;
import model.collision.BehaviourStop;
import model.collision.CollisionBehaviour;
import service.PublishingSprite;

/**
 * Модель абстрактной ракетки.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class Paddle extends IngameObject {

    protected ArrayList<Ball> _balls = new ArrayList<>();
    protected final Speed2D _startedSpeed = new Speed2D(0.2, 0);

        public Paddle (PublishingSprite sprite) {
            super(sprite);
            super.addDefaultBehaviuor(BehaviourStop.getInstance());
        }
        
        public Paddle (PublishingSprite sprite, Speed2D speed2D) {
            super(sprite, speed2D);
            super.addDefaultBehaviuor(BehaviourStop.getInstance());
        }

	/**
	 * Поместить шар на ракетку.
	 * @param b Шар.
	 */
	public void addBall(Ball b) {
	    
	    b.setSpeed(new Speed2D(0, 0));
	    _balls.add(b);
    }
	
	/**
	 * Убрать шар с ракетки.
	 * @param b Шар.
	 */
    public void removeBall(Ball b) {
        _balls.remove(b);
    }
    
    /**
     * Запускает шары с ракетки.
     */
    public void pushBalls() {
        
        while (!_balls.isEmpty()) {
            Ball b = _balls.get(0);
            b.setSpeed(_startedSpeed);
            _balls.remove(b);
        }
    }
    
    public void initSpecialBehaviours() {
        addSpecificCollisionBehaviour(Ball.class, CollisionBehaviour.getInstance());
    }
    
    public Paddle clone() {
    	
    	Paddle clone = (Paddle) super.clone();
    	return clone;
    }
}
