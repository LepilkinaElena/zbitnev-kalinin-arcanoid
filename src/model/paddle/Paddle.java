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
    protected final Speed2D _startedSpeed = new Speed2D(0, -0.2);

    public Paddle(PublishingSprite publishingSprite) {
        super(publishingSprite);
        super.addDefaultBehaviuor(BehaviourStop.getInstance());
    }

    public Paddle(PublishingSprite publishingSprite, Speed2D speed2D) {
        super(publishingSprite, speed2D);
        super.addDefaultBehaviuor(BehaviourStop.getInstance());
    }

    /**
     * Поместить шар на ракетку.
     *
     * @param ball Шар.
     */
    public void addBall(Ball ball) {

        ball.setSpeed(new Speed2D(0, 0));
        ball.setPosition(new Point2D.Float((float) (getPosition().x + this.getSize().width/2 - ball.getSize().getWidth()/2), this.getPosition().y - ball.getSize().height));
        _balls.add(ball);
    }

    /**
     * Убрать шар с ракетки.
     *
     * @param b Шарball
     */
    public void removeBall(Ball ball) {
        _balls.remove(ball);
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
