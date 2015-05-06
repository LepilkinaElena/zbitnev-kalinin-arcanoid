package service;

import com.golden.gamedev.object.SpriteGroup;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import model.Speed2D;

/**
 * Спрайт игрового объекта
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingSprite {

    /** Спрайт GTGE (заменить в случае смены библиотеки) */
    private final UniqSprite _uniqSpriteubiq;

    /**
     * Создать спрайт
     * 
     * @param uniqSprite спрайт
     */
    public PublishingSprite(UniqSprite uniqSprite) {
        
        _uniqSpriteubiq = uniqSprite;
    }

    /**
     * Получить идентификатор 
     * 
     * @return идентификатор спрайта
     */
    public int getId() {
        
        if (_uniqSpriteubiq != null) {
            return _uniqSpriteubiq.getId();
        }
        return -1;
    }

    /**
     * Утановить скорость
     * 
     * @param speed новая скорость
     */
    public void setSpeed(Speed2D speed) {
        
        _uniqSpriteubiq.setSpeed(speed.x(), speed.y());
    }

    /**
     * Получить скорость
     * 
     * @return скорость
     */
    public Speed2D getSpeed() {
        
        return new Speed2D(_uniqSpriteubiq.getHorizontalSpeed(), _uniqSpriteubiq.getVerticalSpeed());
    }

    /**
     * Установить позицию
     * 
     * @param point новая позиция
     */
    public void setPosition(Point2D.Float point) {
        
        _uniqSpriteubiq.setX(point.x);
        _uniqSpriteubiq.setY(point.y);
    }

    /**
     * Получить позицию
     * 
     * @return позиция
     */
    public Point2D.Float getPosition() {
        
        return new Point2D.Float((float) _uniqSpriteubiq.getX(), (float) _uniqSpriteubiq.getY());
    }

    /**
     * Получить размер
     * 
     * @return размер
     */
    public Dimension getSize() {
        
        return new Dimension(_uniqSpriteubiq.getWidth(), _uniqSpriteubiq.getHeight());
    }

    /**
     * Клонировтаь спрайт 
     * 
     * @return клон спрайта
     */
    public PublishingSprite clone() {
        
        UniqSprite cloneSprite = _uniqSpriteubiq.clone();
        cloneSprite.setX(_uniqSpriteubiq.getX());
        cloneSprite.setY(_uniqSpriteubiq.getY());
        cloneSprite.setVerticalSpeed(_uniqSpriteubiq.getVerticalSpeed());
        cloneSprite.setHorizontalSpeed(_uniqSpriteubiq.getHorizontalSpeed());
        PublishingSprite clone = new PublishingSprite(cloneSprite);
        return clone;
    }

    /** 
     * Удалить спрайт из спрайт группы
     * 
     * @param spriteGroup спрайт группа
     */
    public void removeFromSpriteGroup(SpriteGroup spriteGroup) {
        
        spriteGroup.remove(_uniqSpriteubiq);
    }

}
