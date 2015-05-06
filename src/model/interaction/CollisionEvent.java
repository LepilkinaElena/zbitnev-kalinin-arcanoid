/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.interaction;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import model.collisionProcessing.IngameObject;

/**
 * Событие коллизии
 * 
 * @author Елена
 */
public class CollisionEvent extends EventObject {

    /** Карта со столкнувшимися объектами */ 
    private HashMap<IngameObject, ArrayList<IngameObject>> _storage;
    /** Сторона столкновения*/
    private int _side;
    /** Граница по оси*/
    private Point2D.Float _xBound;
    
    /**
     * Создать событие
     * 
     * @param source объект источник
     * @param side сторона столкновения
     * @param xBound граница по оси х
     * @param storage карта солкнушихся объектов
     */
    public CollisionEvent(Object source, int side, Point2D.Float xBound, HashMap<IngameObject, ArrayList<IngameObject>> storage) {
        
        super(source);
        _storage = storage;
        _side = side;
        _xBound = xBound;
    }
    
    /**
     * Создать событие
     * 
     * @param source объект источник
     * @param storage карта солкнушихся объектов
     */
    public CollisionEvent(Object source, HashMap<IngameObject, ArrayList<IngameObject>> storage) {
        
        super(source);
        _storage = storage;
    }

    /**
     * Получит карту солкнушихся объектов
     * @return карта солкнушихся объектов
     */
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
    public Point2D.Float xBound() 
    {
        return _xBound;
    }

}
