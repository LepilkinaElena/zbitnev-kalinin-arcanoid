package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameField;
import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.ball.Ball;
import model.collision.BehaviourDestroy;
import model.collision.BehaviourPaddleRebound;
import service.PublishingSprite;

/**
 * Модель разрушаемого кирпича.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BreakableBrick extends Brick {

    public BreakableBrick(PublishingSprite sprite) {
        super(sprite);
    }

    public BreakableBrick clone() {
        BreakableBrick clone = null;
        clone = (BreakableBrick) super.clone();
        return clone;
    }

    public void initSpecialBehaviours() {
        addSpecificCollisionBehaviour(Ball.class, BehaviourDestroy.getInstance());
    }
}
