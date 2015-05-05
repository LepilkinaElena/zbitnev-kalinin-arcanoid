package model;

import model.collisionProcessing.IngameObject;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.ball.Ball;
import model.interaction.GenericEvent;
import model.interaction.GenericEventListener;
import view.GameFieldView;

/**
 * Модель игрового поля.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameField {

    private HashMap<Integer, IngameObject> _objects;
    private Dimension _dimensions;
    private ObjectGenericListener _listener;

    /**
     * Инициализирует поле заданного размера.
     *
     * @param size Размер поля.
     */
    public GameField(Dimension size) {

        _objects = new HashMap<>();
        _dimensions = size;
        _listener = new ObjectGenericListener();
    }

    /**
     * Добавить объект на поле
     *
     * @param object Объект для добавления
     */
    public void addObject(IngameObject object) {
        _objects.put(object.getId(), object);
    }

    /**
     * Убрать объект с поля
     *
     * @param object Объект для удаления
     */
    private void removeObject(int object) {
        _objects.remove(object);
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
        return _objects.get(id);
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
            Set<Integer> keys = _objects.keySet();
            foundClass = Class.forName(className);
            for (Integer key : keys) {
                if (_objects.get(key).getClass() == foundClass) {
                    elements.add(_objects.get(key));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameField.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elements;
    }

    void clear() {
        Set<Integer> keys = _objects.keySet();
        ArrayList<Integer> arrayKeys = new ArrayList<>();
        arrayKeys.addAll(keys);
        for (int i = 0; i < arrayKeys.size(); i++) {    
            if (_objects.get(arrayKeys.get(i))!= null)
            {
                _objects.get(arrayKeys.get(i)).destroy();
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
