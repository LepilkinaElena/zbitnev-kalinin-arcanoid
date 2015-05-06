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

    /** Игровые объекты */
    private HashMap<Integer, IngameObject> _ingameObjects;
    /** Размеры поля */
    private Dimension _dimensions;
    /** Слушатель удаления элемента с поля */
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
     * @param object Идентификатор объект для удаления
     */
    private void removeObject(int objectId) {
        
        _ingameObjects.remove(objectId);
    }

    /**
     * Получить размеры игрового поля (в пикселях).
     *
     * @return Размеры поля.
     */
    public Dimension getSize() {
        
        return _dimensions;
    }

    /**
     * Получить игровой объект
     * @param id идентификатор игрового объекта
     * @return игровой объект
     */
    public IngameObject getObject(int id) {
        
        return _ingameObjects.get(id);
    }

    /**
     * Получить все элементы поля заданного класса
     * 
     * @param className имя класса
     * @return список элементов данного класса
     */
    public ArrayList<IngameObject> getObjects(String className) {
        
        // Искомый класс
        Class foundClass;
        // Объекты искомого класса
        ArrayList<IngameObject> elements = new ArrayList<>();
        try {
            // Обход карты для поиска игровых объектов нужного класса.
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

    /**
     * Очистить поле
     */
    void clear() {
        
        // Удаление всех игровых объектов из хранилища.
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
    
    //--------------------- Работа со слушателями --------------------------
    
    /**
     * Добавить себя в качестве слушателя удаления объектов
     * @param object объект, об удалении которого поле должно знать
     */
    public void addListenerToObject(IngameObject object) {
        
        object.addGenericEventListener(_listener);
    }
    
    /**
     * Класс слушателя события разрушения объектов
     */
    private class ObjectGenericListener implements GenericEventListener {

        @Override
        public void destroyed(GenericEvent event) {
            
            removeObject(event.getElementId());
        }

    }
}
