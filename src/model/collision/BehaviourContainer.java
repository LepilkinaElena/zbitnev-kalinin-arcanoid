package model.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BehaviourContainer {
    private HashMap<Class<?>, ArrayList<CollisionBehaviour>> _specialColBehaviours 
		= new HashMap<>();
    
    private  HashMap<Class<?>, Iterator> _iterators = new HashMap<>();
    /**
     * Если флаг установлен, то поведение применяется не только при столкновении с объектом данного класса, но и его потомками.
     */
    public boolean _flagCheckDerived = false;
    
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
    
    public CollisionBehaviour next(Class<?> className) {
        ArrayList<CollisionBehaviour> list;
        Iterator i;
        if (_specialColBehaviours.containsKey(className)) {
            list = _specialColBehaviours.get(className);
            if (_iterators.containsKey(className)) {
                i = _iterators.get(className);
            } else {
                i = list.iterator();
            }
            return (CollisionBehaviour)i.next();
        }
        return null;
    }
    
    public boolean hasNext(Class<?> className) {
        ArrayList<CollisionBehaviour> list;
        Iterator i;
        if (_specialColBehaviours.containsKey(className)) {
            list = _specialColBehaviours.get(className);
            if (_iterators.containsKey(className)) {
                i = _iterators.get(className);
            } else {
                i = list.iterator();
            }
            return i.hasNext();
        }
        return false;
    }
}
