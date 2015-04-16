package view;

import com.golden.gamedev.object.Sprite;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import model.Speed2D;

/**
 * Спрайт, знающий о том, частью какого игрового объекта (IngameObjectView) он является
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingSprite implements Cloneable{
    
    private final Sprite sprite;
    
    public PublishingSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public int getId() {
        if (sprite != null) {
            return sprite.getID();
        }
        return -1;
    }
	
    public void setSpeed(Speed2D speed) {
        sprite.setSpeed(speed.x(), speed.y());
    }
    
    public Speed2D getSpeed() {
        return new Speed2D(sprite.getHorizontalSpeed(), sprite.getVerticalSpeed());
    }
    
    public void setPosition(Point2D.Float point) {
        sprite.setX(point.x);
        sprite.setY(point.y);
    }
    
    public Point2D.Float getPosition() {
        return new Point2D.Float((float)sprite.getX(), (float)sprite.getY());
    }
    
    public Dimension getSize() {
        return new Dimension(sprite.getWidth(), sprite.getHeight());
    }
    
    @Override
    public Object clone() {
        Sprite cloneSprite = new Sprite(sprite.getImage());
        cloneSprite.setX(sprite.getX());
        cloneSprite.setY(sprite.getY());
        cloneSprite.setVerticalSpeed(sprite.getVerticalSpeed());
        cloneSprite.setHorizontalSpeed(sprite.getHorizontalSpeed());
        PublishingSprite clone = new PublishingSprite(cloneSprite);
        return clone;
    }
}
