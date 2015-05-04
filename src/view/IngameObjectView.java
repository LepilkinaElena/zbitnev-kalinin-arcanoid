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

    protected final IngameObject _ingameObject;

    protected PublishingSprite _publishingSprite = null;

    /**
     * Создает представление объекта на основе его модели и спрайта. Этот метод
     * автоматически согласует слушателей и связывает спрайт с объектом
     * представления, которому он принадлежит.
     *
     * @param ingameObject Модель игрового объекта.
     * @param publishingSprite Спрайт, которым он будет отображен.
     */
    public IngameObjectView(IngameObject ingameObject, PublishingSprite publishingSprite) {

        _ingameObject = ingameObject;
        _publishingSprite = publishingSprite;
    }

    public int getId() {
        return _publishingSprite.getId();
    }

    void removeFromSpriteGroup(SpriteGroup spriteGroup) {
        _publishingSprite.removeFromSpriteGroup(spriteGroup);
    }

}
