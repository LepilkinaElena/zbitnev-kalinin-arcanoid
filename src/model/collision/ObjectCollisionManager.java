/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import model.GameField;
import model.interaction.CollisionEvent;
import model.interaction.CollisionListener;


/**
 *
 * @author Елена
 */
public class ObjectCollisionManager {
    private PublishingCollisionManager _objectManager;
    private BoundaryCollisionManager _boundaManager;
    
    public ObjectCollisionManager(GameField field) {
        CollisionOccuredListener listener = new CollisionOccuredListener();
        _objectManager = new PublishingCollisionManager(field);
        _boundaManager = new BoundaryCollisionManager(0, 0, field.getSize().width, field.getSize().height, field);
        _objectManager.addCollisionListener(listener);
        _boundaManager.addCollisionListener(listener);
    }
    
    private class CollisionOccuredListener implements CollisionListener {
        @Override
        public void collisionOccured(CollisionEvent event) {
            // TODO обработка коллизии
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
