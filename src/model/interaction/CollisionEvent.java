/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interaction;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import model.collisionProcessing.IngameObject;

/**
 *
 * @author Елена
 */
public class CollisionEvent extends EventObject {

    private HashMap<IngameObject, ArrayList<IngameObject>> _storage;
    /** Сторона столкновения*/
    private int _side;
    /** Граница по оси*/
    private int _xBound;
    
    public CollisionEvent(Object source, int side, int xBound, HashMap<IngameObject, ArrayList<IngameObject>> storage) {
        super(source);
        _storage = storage;
        _side = side;
        _xBound = xBound;
    }
    
    public CollisionEvent(Object source, HashMap<IngameObject, ArrayList<IngameObject>> storage) {
        super(source);
        _storage = storage;
    }

    public HashMap<IngameObject, ArrayList<IngameObject>> getStorage() {
        return (HashMap<IngameObject, ArrayList<IngameObject>>) _storage.clone();
    }
    
    /**
     * Получить сторону столкновения
     * @return сторона столкновения
     */
    public int side() {
        return _side;
    }
    
    /**
     * Получит границу по оси
     * @return граница по оси
     */
    public int xBound() {
        return _xBound;
    }

    public void clearStorage() {
        _storage.clear();
    }
}
