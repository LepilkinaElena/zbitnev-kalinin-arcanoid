package service;

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

    private static int _nextid = 0;
    private final UniqSprite _uniqSpriteubiq;
    private final int _id;

    public PublishingSprite(UniqSprite uniqSprite) {
        _uniqSpriteubiq = uniqSprite;
        _id = _nextid;
        _nextid++;
    }

    public int getId() {
        if (_uniqSpriteubiq != null) {
            return _uniqSpriteubiq.getId();
        }
        return -1;
    }

    public void setSpeed(Speed2D speed) {
        _uniqSpriteubiq.setSpeed(speed.x(), speed.y());
    }

    public Speed2D getSpeed() {
        return new Speed2D(_uniqSpriteubiq.getHorizontalSpeed(), _uniqSpriteubiq.getVerticalSpeed());
    }

    public void setPosition(Point2D.Float point) {
        _uniqSpriteubiq.setX(point.x);
        _uniqSpriteubiq.setY(point.y);
    }

    public Point2D.Float getPosition() {
        return new Point2D.Float((float) _uniqSpriteubiq.getX(), (float) _uniqSpriteubiq.getY());
    }

    public Dimension getSize() {
        return new Dimension(_uniqSpriteubiq.getWidth(), _uniqSpriteubiq.getHeight());
    }

    public PublishingSprite clone() {
        UniqSprite cloneSprite = _uniqSpriteubiq.clone();
        cloneSprite.setX(_uniqSpriteubiq.getX());
        cloneSprite.setY(_uniqSpriteubiq.getY());
        cloneSprite.setVerticalSpeed(_uniqSpriteubiq.getVerticalSpeed());
        cloneSprite.setHorizontalSpeed(_uniqSpriteubiq.getHorizontalSpeed());
        PublishingSprite clone = new PublishingSprite(cloneSprite);
        return clone;
    }

    public void removeFromSpriteGroup(SpriteGroup spriteGroup) {
        spriteGroup.remove(_uniqSpriteubiq);
    }

}
