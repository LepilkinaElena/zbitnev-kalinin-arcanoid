package model.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BehaviourContainer {

    private HashMap<Class<?>, ArrayList<CollisionBehaviour>> _specialColBehaviours
            = new HashMap<>();

    public BehaviourContainer() {

    }

    public boolean isEmpty() {
        return _specialColBehaviours.isEmpty();
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
    public BehaviourContainer clone() {
        BehaviourContainer cloneContainer = new BehaviourContainer();
        cloneContainer._specialColBehaviours = (HashMap<Class<?>, ArrayList<CollisionBehaviour>>) _specialColBehaviours.clone();
        return cloneContainer;
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
    
    public boolean contains(Class<?> className) {
        return _specialColBehaviours.containsKey(className);
    }
}
