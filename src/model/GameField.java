package model;

import model.collisionProcessing.IngameObject;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interaction.GenericEvent;
import model.interaction.GenericEventListener;

/**
 * Модель игрового поля.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameField {

    private HashMap<Integer, IngameObject> _ingameObjects;
    private Dimension _dimensions;
    private ObjectGenericListener _listener;

    /**
     * Инициализирует поле заданного размера.
     *
     * @param size Размер поля.
     */
    public GameField(Dimension size) {

        _ingameObjects = new HashMap<>();
        _dimensions = size;
        _listener = new ObjectGenericListener();
    }

    /**
     * Добавить объект на поле
     *
     * @param object Объект для добавления
     */
    public void addObject(IngameObject object) {
        
        _ingameObjects.put(object.getId(), object);
    }

    /**
     * Убрать объект с поля
     *
     * @param object Объект для удаления
     */
    private void removeObject(int object) {
        
        _ingameObjects.remove(object);
    }

    /**
     * Получить размеры игрового поля (в пикселях).
     *
     * @return Размеры поля.
     */
    public Dimension getSize() {
        
        return _dimensions;
    }

    public IngameObject getObject(int id) {
        
        return _ingameObjects.get(id);
    }

    public void addListenerToObject(IngameObject object) {
        
        object.addGenericEventListener(_listener);
    }

    /**
     * Получить все элементы поля заданного класса
     * 
     * @param className имя класса
     * @return список элементов данного класса
     */
    public ArrayList<IngameObject> getElements(String className) {
        
        Class foundClass;
        ArrayList<IngameObject> elements = new ArrayList<>();
        try {
            Set<Integer> keys = _ingameObjects.keySet();
            foundClass = Class.forName(className);
            for (Integer key : keys) {
                if (_ingameObjects.get(key).getClass() == foundClass) {
                    elements.add(_ingameObjects.get(key));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elements;
    }

    void clear() {
        
        Set<Integer> keys = _ingameObjects.keySet();
        ArrayList<Integer> arrayKeys = new ArrayList<>();
        arrayKeys.addAll(keys);
        for (int i = 0; i < arrayKeys.size(); i++) {    
            if (_ingameObjects.get(arrayKeys.get(i))!= null)
            {
                _ingameObjects.get(arrayKeys.get(i)).destroy();
                i--;
            }
        }
    }
    
    private class ObjectGenericListener implements GenericEventListener {

        @Override
        public void destroyed(GenericEvent event) {
            removeObject(event.getElementId());
        }

    }
}
