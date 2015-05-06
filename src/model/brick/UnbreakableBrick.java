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

    /**
     * Создать неразрушаемый кирпич
     * 
     * @param publishingSprite спрайт кирпича
     */
    public UnbreakableBrick(PublishingSprite publishingSprite) {
        
        super(publishingSprite);
        super.addDefaultBehaviuor(CollisionBehaviour.getInstance());
    }
    
    /**
     * Создать неразрушаемый кирпич (конструктор для абстракции кирпича, являющегося результатом системы нескольких кирпичей)
     * 
     * @param axis ось кирпича
     */
    public UnbreakableBrick(Speed2D.Axis axis) {
        
        super(null);
        setAxis(axis);
        super.addDefaultBehaviuor(CollisionBehaviour.getInstance());
    }

    /**
     * Клонировать кирпич
     * 
     * @return клон кирпича
     */
    public UnbreakableBrick clone() {
        UnbreakableBrick clone = (UnbreakableBrick) super.clone();
        return clone;
    }

}
