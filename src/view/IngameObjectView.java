package view;

import service.PublishingSprite;

import model.collisionProcessing.IngameObject;

/**
 * Представление отдельного игрового объекта
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public abstract class IngameObjectView {

    protected final IngameObject ingameObject;

    protected PublishingSprite _sprite = null;

    /**
     * Создает представление объекта на основе его модели и спрайта. Этот метод
     * автоматически согласует слушателей и связывает спрайт с объектом
     * представления, которому он принадлежит.
     *
     * @param obj Модель игрового объекта.
     * @param sprite Спрайт, которым он будет отображен.
     */
    public IngameObjectView(IngameObject obj, PublishingSprite sprite) {

        this.ingameObject = obj;
        this._sprite = sprite;
    }

    public int getId() {
        return _sprite.getId();
    }

}
