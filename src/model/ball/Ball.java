package model.ball;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.collision.BehaviourPaddleRebound;
import model.collision.BehaviourRebound;
import model.collision.BehaviourReflect;
import model.interaction.BallFailEvent;
import model.interaction.BallFailListener;
import model.paddle.Paddle;
import service.PublishingSprite;

/**
 * Модель абстрактного шарика
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Ball extends IngameObject {
    
    private ArrayList<BallFailListener> _ballFailListeners = new ArrayList();

    public void addBallFailListener (BallFailListener ballFailListener) {
        _ballFailListeners.add(ballFailListener);
    }    

    private void fireBallFail() {
        for (BallFailListener listener: _ballFailListeners) {
            listener.endGame(new BallFailEvent(this));
        }
    }
    
    public Ball(PublishingSprite sprite) {
        super(sprite);
        setWeight(1);
        super.addDefaultBehaviuor(BehaviourReflect.getInstance());
    }
    
    public Ball(Speed2D.Axis axis) {
        super(null);
        setAxis(axis);
        setWeight(1);
        super.addDefaultBehaviuor(BehaviourReflect.getInstance());
    }

    public Ball(PublishingSprite sprite, Speed2D speed2D) {
        super(sprite, speed2D);
        setWeight(1);
        super.addDefaultBehaviuor(BehaviourReflect.getInstance());
    }

    @Override
    public void setPosition(Point2D.Float pos) {
        super.setPosition(pos);
    }

    public Ball clone() {

        Ball clone = (Ball) super.clone();
        return clone;
    }

    public void initSpecialBehaviours() {
        addSpecificCollisionBehaviour(Ball.class, BehaviourRebound.getInstance());
        addSpecificCollisionBehaviour(Paddle.class, BehaviourPaddleRebound.getInstance());
    }
    
    @Override
    public void destroy() {
        super.destroy();
        fireBallFail();
    }
}
