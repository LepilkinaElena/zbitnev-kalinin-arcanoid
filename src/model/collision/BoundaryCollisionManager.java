/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;
import java.util.ArrayList;
import java.util.HashMap;
import model.GameField;
import model.collisionProcessing.IngameObject;
import model.interaction.CollisionEvent;
import model.interaction.CollisionListener;

/**
 *
 * @author Елена
 */
public class BoundaryCollisionManager extends CollisionBounds {
    private HashMap<IngameObject, ArrayList<IngameObject>> _storage = new HashMap<>();
    private GameField _field;
    /**
     * Список слушателей события
     */
    private ArrayList<CollisionListener> _collisionListener = new ArrayList<>();

    public BoundaryCollisionManager (int x, int y, int width, int height, GameField field) {
        super(x, y, width, height);
        _field = field;
    }
    
    @Override
    public void collided(Sprite sprite) {
        _storage.clear();
        _storage.put(_field.getObject(sprite.getID()), null);
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
        for (CollisionListener listener: _collisionListener) {
            listener.collisionOccured(new CollisionEvent(this, _storage));
        }
    }
}