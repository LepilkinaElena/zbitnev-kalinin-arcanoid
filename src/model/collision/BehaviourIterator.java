/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Класс итератора для кнтйнера с поведениями
 * 
 * @author Елена
 */
public class BehaviourIterator implements Iterator<CollisionBehaviour> {

    /** Итератор для внутреннего массива*/
    private Iterator<CollisionBehaviour> _collisionBehaviourIterator;

    /**
     * Создать итератор
     * 
     * @param list список с поведениями
     */
    public BehaviourIterator(ArrayList<CollisionBehaviour> list) {
        
        _collisionBehaviourIterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        
        return _collisionBehaviourIterator.hasNext();
    }

    @Override
    public CollisionBehaviour next() {
        
        return _collisionBehaviourIterator.next();
    }

    /**
     * Удалить элемент по итератору
     * 
     */
    public void remove() {
        
        _collisionBehaviourIterator.remove();
    }
}
