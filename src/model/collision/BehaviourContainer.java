package model.collision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import model.collisionProcessing.IngameObject;

/**
 * Класс контейнера специальных поведений
 * 
 * @author Елена
 */
public class BehaviourContainer {

    /** Карта, содержащая информацию о поведении при столкновении с объектом определенного классса */
    private HashMap<Class<?>, ArrayList<CollisionBehaviour>> _specialColBehaviours
            = new HashMap<>();

    /**
     * Проверить контейнер на пустоту
     * 
     * @return признак, пустой ли контейнер
     */
    public boolean isEmpty() {
        
        return _specialColBehaviours.isEmpty();
    }

    /**
     * Добавить поведение
     * 
     * @param className класс, для которого добавляется поведение
     * @param collisionBehaviour поведение
     */
    public void addBehaviour(Class<?> className, CollisionBehaviour collisionBehaviour) {
        
        ArrayList<CollisionBehaviour> list = new ArrayList<>();
        // Если уже есть поведения для данного класса, добавляем к ним еще одно
        if (_specialColBehaviours.containsKey(className)) {
            _specialColBehaviours.get(className).add(collisionBehaviour);
        } else {
            // Иначе создаем новую пару
            list.add(collisionBehaviour);
            _specialColBehaviours.put(className, list);
        }
    }

    /**
     * Удалить поведение
     * 
     * @param className класс, для которого удаляется поведение
     * @param collisionBehaviour удаляемое поведение
     */
    public void removeBehaviour(Class<?> className, CollisionBehaviour collisionBehaviour) {
        
        if (_specialColBehaviours.containsKey(className)) {
            _specialColBehaviours.get(className).remove(collisionBehaviour);
            // Если не осталось ни одного поведения после удаления, удалить сам ключ
            if (_specialColBehaviours.get(className).isEmpty()) {
                _specialColBehaviours.remove(className);
            }
        }
    }

    /**
     * Удалить все поведения для заданного класса
     * 
     * @param className класс
     */
    public void removeBehaviour(Class<?> className) {
        
        if (_specialColBehaviours.containsKey(className)) {
            _specialColBehaviours.get(className).clear();
        }
    }

    /**
     * Клонировать контейнер
     * 
     * @return клон контейнера
     */
    public BehaviourContainer clone() {
        
        BehaviourContainer cloneContainer = new BehaviourContainer();
        cloneContainer._specialColBehaviours = (HashMap<Class<?>, ArrayList<CollisionBehaviour>>) _specialColBehaviours.clone();
        return cloneContainer;
    }

    /**
     * Очистить контейнер
     */
    public void clear() {
        
        _specialColBehaviours.clear();
    }

    /**
     * Вернуть итератор для обхода контйнера в поиске поведения для конкретного класса
     * 
     * @param className класс
     * @return итератор
     */
    public Iterator<CollisionBehaviour> iterator(Class<?> className) {
        
        if (_specialColBehaviours.containsKey(className)) {
            return new BehaviourIterator(_specialColBehaviours.get(className));
        }
        return null;
    }
    
    /**
     * Проверка содержит ли контейнер поведения для заданного класса
     * 
     * @param className класс
     * @return признак содержит ли контейнер поведения для заданного класса
     */
    public boolean contains(Class<?> className) {
        
        return _specialColBehaviours.containsKey(className);
    }
    
    public void invoke(IngameObject collidedObject, IngameObject with) {
        
        Iterator<CollisionBehaviour> iterator = iterator(with.getClass());
        // Выполнение всех специальных поведений
        while (iterator.hasNext()) {
            CollisionBehaviour currentBehavior = iterator.next();
            currentBehavior.invoke(collidedObject, with);
        }
    }
}
