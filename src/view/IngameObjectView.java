package view;

import com.golden.gamedev.object.SpriteGroup;
import service.PublishingSprite;

import model.collisionProcessing.IngameObject;

/**
 * Представление отдельного игрового объекта
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public abstract class IngameObjectView {

    /** Модель игрового объекта */
    protected final IngameObject _ingameObject;
    /** Спрайт объекта */
    protected PublishingSprite _publishingSprite = null;

    /**
     * Создает представление объекта на основе его модели и спрайта.
     *
     * @param ingameObject Модель игрового объекта.
     * @param publishingSprite Спрайт, которым он будет отображен.
     */
    public IngameObjectView(IngameObject ingameObject, PublishingSprite publishingSprite) {

        _ingameObject = ingameObject;
        _publishingSprite = publishingSprite;
    }

    /**
     * Получить идентификатор
     * 
     * @return идентификатор
     */
    public int getId() {
        
        return _publishingSprite.getId();
    }

    /**
     * Удалить из спрайт группы
     * 
     * @param spriteGroup группа спрайтов
     */
    void removeFromSpriteGroup(SpriteGroup spriteGroup) {
        
        _publishingSprite.removeFromSpriteGroup(spriteGroup);
    }

}
