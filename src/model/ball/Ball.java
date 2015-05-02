package model.ball;

import java.awt.geom.Point2D;
import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.collision.BehaviourPaddleRebound;
import model.collision.BehaviourRebound;
import model.collision.BehaviourReflect;
import model.paddle.Paddle;
import service.PublishingSprite;

/**
 * Модель абстрактного шарика
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Ball extends IngameObject {

    public Ball(PublishingSprite sprite) {
        super(sprite);
        super.addDefaultBehaviuor(BehaviourReflect.getInstance());
    }

    public Ball(PublishingSprite sprite, Speed2D speed2D) {
        super(sprite, speed2D);
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
}
