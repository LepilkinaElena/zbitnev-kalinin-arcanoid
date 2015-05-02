package service;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import model.Speed2D;

/**
 * Спрайт, знающий о том, частью какого игрового объекта (IngameObjectView) он
 * является
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingSprite {

    private static int nextid = 0;
    private final UniqSprite sprite;
    private final int id;

    public PublishingSprite(UniqSprite sprite) {
        this.sprite = sprite;
        this.id = nextid;
        nextid++;
    }

    public int getId() {
        if (sprite != null) {
            return sprite.getId();
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
        return new Point2D.Float((float) sprite.getX(), (float) sprite.getY());
    }

    public Dimension getSize() {
        return new Dimension(sprite.getWidth(), sprite.getHeight());
    }

    public PublishingSprite clone() {
        UniqSprite cloneSprite = new UniqSprite(sprite.getImage());
        cloneSprite.setX(sprite.getX());
        cloneSprite.setY(sprite.getY());
        cloneSprite.setVerticalSpeed(sprite.getVerticalSpeed());
        cloneSprite.setHorizontalSpeed(sprite.getHorizontalSpeed());
        PublishingSprite clone = new PublishingSprite(cloneSprite);
        return clone;
    }

    public void removeFromSpriteGroup(SpriteGroup spriteGroup) {
        spriteGroup.remove(sprite);
    }

}
