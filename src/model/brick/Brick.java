package model.brick;

import model.collisionProcessing.IngameObject;
import service.PublishingSprite;

/**
 * Модель абстрактного кирпича.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class Brick extends IngameObject {

    /**
     * Создать кирпич
     * 
     * @param publishingSprite спрайт кирпича
     */
    public Brick(PublishingSprite publishingSprite) {
        
        super(publishingSprite);
    }

    /**
     * Клонировать кирпич
     * 
     * @return клон кирпича
     */
    public Brick clone() {
        
        Brick clone = (Brick) super.clone();
        return clone;
    }
}
