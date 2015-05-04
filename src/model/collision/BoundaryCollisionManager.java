/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import model.GameField;
import model.collisionProcessing.IngameObject;
import model.interaction.CollisionEvent;
import model.interaction.CollisionListener;
import service.UniqSprite;

/**
 *
 * @author Елена
 */
public class BoundaryCollisionManager extends CollisionBounds {

    private HashMap<IngameObject, ArrayList<IngameObject>> _storage = new HashMap<>();
    private GameField _field;
    private Point2D.Float position;
    /**
     * Список слушателей события
     */
    private ArrayList<CollisionListener> _collisionListener = new ArrayList<>();

    public BoundaryCollisionManager(int x, int y, int width, int height, GameField field) {
        super(x, y, width, height);
        _field = field;
        
    }

    @Override
    public void collided(Sprite sprite) {
        _storage.clear();
        position = new Point2D.Float((float)sprite.getOldX(), (float) sprite.getOldY());
        _storage.put(_field.getObject(((UniqSprite)sprite).getId()), null);
        fireIngameObjectCollided();
    }

    //-------------------------------------
    /**
     * Добавление слушателей события о том, что столкнулись игровые объекты
     *
     * @param element слушатель
     */
    public void addCollisionListener(CollisionListener element) {
        _collisionListener.add(element);
    }

    /**
     * Испустить событие о том, что столкнулись игровые объекты
     */
    private void fireIngameObjectCollided() {
        for (CollisionListener listener : _collisionListener) {
            listener.collisionOccured(new CollisionEvent(this, getSide(), position, _storage));
        }
    }
    
    private int getSide() {
        int side;
        if(isCollisionSide(TOP_COLLISION)) {
            side = TOP_COLLISION;
        } else if(isCollisionSide(BOTTOM_COLLISION)) {
            side = BOTTOM_COLLISION;
        } else if(isCollisionSide(RIGHT_COLLISION)) {
            side = RIGHT_COLLISION;
        } else {
            side = LEFT_COLLISION;
        }
        return side;
    }
    
    
}
