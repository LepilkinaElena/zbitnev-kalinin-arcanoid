package integrationGTGE;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import model.GameField;
import model.Speed2D;

/**
 * Спрайт игрового объекта
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingSprite {
    
    /** Следующий идентификатор */
    private static int _nextid = 0;
    /** Идентификатор спрайта */
    private int _id;

    /** Спрайт GTGE (заменить в случае смены библиотеки) */
    private final Sprite _sprite;

    /**
     * Создать спрайт
     * 
     * @param uniqSprite спрайт
     */
    public PublishingSprite(BufferedImage bufferedImage) {
        
        _sprite = new Sprite(bufferedImage);
        _id = _nextid;
        _nextid++;
        _sprite.setID(_id);
    }
    
    private PublishingSprite (BufferedImage bufferedImage, int id) {
        
        _sprite = new Sprite(bufferedImage);
        
        _sprite.setID(id);
    }

    /**
     * Получить идентификатор 
     * 
     * @return идентификатор спрайта
     */
    public int getId() {
              
        return _sprite.getID();
    }

    /**
     * Утановить скорость
     * 
     * @param speed новая скорость
     */
    public void setSpeed(Speed2D speed) {
        
        _sprite.setSpeed(speed.x(), speed.y());
    }

    /**
     * Получить скорость
     * 
     * @return скорость
     */
    public Speed2D getSpeed() {
        
        return new Speed2D(_sprite.getHorizontalSpeed(), _sprite.getVerticalSpeed());
    }

    /**
     * Установить позицию
     * 
     * @param point новая позиция
     */
    public void setPosition(Point2D.Float point) {
        
        _sprite.setX(point.x);
        _sprite.setY(point.y);
    }

    /**
     * Получить позицию
     * 
     * @return позиция
     */
    public Point2D.Float getPosition() {
        
        return new Point2D.Float((float) _sprite.getX(), (float) _sprite.getY());
    }

    /**
     * Получить размер
     * 
     * @return размер
     */
    public Dimension getSize() {
        
        return new Dimension(_sprite.getWidth(), _sprite.getHeight());
    }

    /**
     * Клонировтаь спрайт 
     * 
     * @return клон спрайта
     */
    public PublishingSprite clone() {
        
        PublishingSprite clone = new PublishingSprite(_sprite.getImage(), getId());
        clone.setPosition(new Point2D.Float((float)_sprite.getX(), (float)_sprite.getY()));
        clone.setSpeed(new Speed2D(_sprite.getHorizontalSpeed(), _sprite.getVerticalSpeed()));
        
        return clone;
    }

    /** 
     * Удалить спрайт из спрайт группы
     * 
     * @param spriteGroup спрайт группа
     */
    void removeFromField(GameFieldView gameFieldView) {
        
        gameFieldView.removeFromSpriteGroup(_sprite);
    }
    
    void addToField(GameFieldView gameFieldView) {
        gameFieldView.addToSpriteGroup(_sprite);
    }

}
