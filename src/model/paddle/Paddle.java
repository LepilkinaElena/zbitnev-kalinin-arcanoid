package model.paddle;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.ball.Ball;
import model.collision.BehaviourStop;
import model.collision.CollisionBehaviour;
import service.PublishingSprite;

/**
 * Модель абстрактной ракетки.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class Paddle extends IngameObject {

    protected ArrayList<Ball> _balls = new ArrayList<>();
    protected final Speed2D _startedSpeed = new Speed2D(0, 0.2);

    public Paddle(PublishingSprite sprite) {
        super(sprite);
        super.addDefaultBehaviuor(BehaviourStop.getInstance());
    }

    public Paddle(PublishingSprite sprite, Speed2D speed2D) {
        super(sprite, speed2D);
        super.addDefaultBehaviuor(BehaviourStop.getInstance());
    }

    /**
     * Поместить шар на ракетку.
     *
     * @param b Шар.
     */
    public void addBall(Ball b) {

        b.setSpeed(new Speed2D(0, 0));
        b.setPosition(new Point2D.Float((float) (this.getPosition().x + this.getSize().width/2 - b.getSize().getWidth()/2), this.getPosition().y - b.getSize().height));
        _balls.add(b);
    }

    /**
     * Убрать шар с ракетки.
     *
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

    @Override
    public void setSpeed(Speed2D speed) {
        for (Ball ball : _balls) {
            ball.setSpeed(speed);
        }
        super.setSpeed(speed);

    }
    
    public void setPosition(Point2D.Float position) {
        super.setPosition(position);
        for (Ball ball : _balls) {
            ball.setPosition(new Point2D.Float((float)(position.getX() + getSize().getWidth()/2 - ball.getSize().getWidth()/2), (float) ball.getPosition().getY()));
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
