package model.brick;

import model.Speed2D;
import model.ball.Ball;
import model.collision.BehaviourDestroy;
import service.PublishingSprite;

/**
 * Модель разрушаемого кирпича.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BreakableBrick extends Brick {

    public BreakableBrick(PublishingSprite publishingSprite) {
        super(publishingSprite);
    }
    
    public BreakableBrick(Speed2D.Axis axis) {
        super(null);
        setAxis(axis);
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
