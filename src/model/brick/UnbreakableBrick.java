package model.brick;


import model.Speed2D;
import model.collision.CollisionBehaviour;
import service.PublishingSprite;

/**
 * Модель неразрушаемого кирпича.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class UnbreakableBrick extends Brick {

    public UnbreakableBrick(PublishingSprite publishingSprite) {
        super(publishingSprite);
        super.addDefaultBehaviuor(CollisionBehaviour.getInstance());
    }
    
    public UnbreakableBrick(Speed2D.Axis axis) {
        super(null);
        setAxis(axis);
        super.addDefaultBehaviuor(CollisionBehaviour.getInstance());
    }

    public UnbreakableBrick clone() {
        UnbreakableBrick clone = (UnbreakableBrick) super.clone();
        return clone;
    }

}
