/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Елена
 */
public class BehaviourIterator implements Iterator<CollisionBehaviour> {

    private Iterator<CollisionBehaviour> _collisionBehaviouriterator;

    public BehaviourIterator(ArrayList<CollisionBehaviour> list) {
        _collisionBehaviouriterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return _collisionBehaviouriterator.hasNext();
    }

    @Override
    public CollisionBehaviour next() {
        return _collisionBehaviouriterator.next();
    }

    public void remove() {
        _collisionBehaviouriterator.remove();
    }
}
