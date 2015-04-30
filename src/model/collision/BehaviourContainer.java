package model.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BehaviourContainer implements Cloneable {
    private HashMap<Class<?>, ArrayList<CollisionBehaviour>> _specialColBehaviours 
		= new HashMap<>();
    
    /**
     * Если флаг установлен, то поведение применяется не только при столкновении с объектом данного класса, но и его потомками.
     */
    //public boolean _flagCheckDerived = false;
    
    public BehaviourContainer() {
        
    }
    
    public BehaviourContainer(Class<?> className, CollisionBehaviour behaviour) {
        ArrayList<CollisionBehaviour> list = new ArrayList<>();
        list.add(behaviour);
        _specialColBehaviours.put(className, list);
    }
    
    public void addBehaviour(Class<?> className, CollisionBehaviour behaviour) {
        ArrayList<CollisionBehaviour> list = new ArrayList<>();
        if (_specialColBehaviours.containsKey(className)) {
           _specialColBehaviours.get(className).add(behaviour);
        } else {
            list.add(behaviour);
            _specialColBehaviours.put(className, list);
        }
    }
    
    public void removeBehaviour(Class<?> className, CollisionBehaviour behaviour) {
        if (_specialColBehaviours.containsKey(className)) {
            _specialColBehaviours.get(className).remove(behaviour);
            if (_specialColBehaviours.get(className).isEmpty()) {
                _specialColBehaviours.remove(className);
            }
        }
    }
    
    public void removeBehaviour(Class<?> className) {
        if (_specialColBehaviours.containsKey(className)) {
            _specialColBehaviours.get(className).clear();
        }
    }
    
    // TO DO ВНИМАНИЕ
    public Object clone() {
        return new Object();
    }
    
    public void clear() {
        _specialColBehaviours.clear();
    }

    public Iterator<CollisionBehaviour> iterator(Class<?> className) {
        if (_specialColBehaviours.containsKey(className)) {
            return new BehaviourIterator(_specialColBehaviours.get(className));
        }
        return null;
    }
}
