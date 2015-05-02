package model;

import model.collisionProcessing.IngameObject;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

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

    private class ObjectGenericListener implements GenericEventListener {

        @Override
        public void destroyed(GenericEvent event) {
            removeObject(event.getElementId());
        }

    }
}
