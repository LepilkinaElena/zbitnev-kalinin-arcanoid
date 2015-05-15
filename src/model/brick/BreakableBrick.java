package model.brick;

import model.Speed2D;
import model.ball.Ball;
import model.collision.BehaviourDestroy;
import integrationGTGE.PublishingSprite;

/**
 * Модель разрушаемого кирпича.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BreakableBrick extends Brick {

    /**
     * Создать разрушаемый кирпич
     * 
     * @param publishingSprite спрайт кирпича
     */
    public BreakableBrick(PublishingSprite publishingSprite) {
        
        super(publishingSprite);
    }
    
    /**
     * Создать разрушаемый кирпич (конструктор для абстракции кирпича, являющегося результатом системы нескольких кирпичей)
     * 
     * @param axis ось кирпича
     */
    public BreakableBrick(Speed2D.Axis axis) {
        
        super(null);
        setAxis(axis);
    }

    /**
     * Клонировать кирпич
     * 
     * @return клон кирпича
     */
    public BreakableBrick clone() {
        
        BreakableBrick clone = null;
        clone = (BreakableBrick) super.clone();
        return clone;
    }

    /**
     * Задать специальные поведения для разрушаемого кирпича, характерные для данных правил игры
     * 
     */
    public void initSpecialBehaviours() {
        
        addSpecificCollisionBehaviour(Ball.class, BehaviourDestroy.getInstance());
    }
}
