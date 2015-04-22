package model;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import model.ball.Ball;
import model.ball.BallPositionChangedListener;

/**
 * Модель игрового поля.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameField {
        private HashMap <Integer, IngameObject> _objects;
	private Dimension _dimensions;
	
	
    /**
     * Инициализирует поле заданного размера.
     * @param size Размер поля.
     */
    public GameField(Dimension size) {
    	
    	_objects = new HashMap<>();
    	_dimensions = size;
    }
    
	/**
	 * Добавить объект на поле
	 * @param object Объект для добавления
	 */
	public void addObject(IngameObject object) {
            _objects.put(object.getId(), object);
	}
	
	/**
	 * Убрать объект с поля
	 * @param object Объект для удаления
	 */
	public void removeObject(IngameObject object) {
		_objects.remove(object);
	}
	
	/** 
	 * Получить размеры игрового поля (в пикселях).
	 * @return Размеры поля.
	 */
	public Dimension getSize() {
		
		return _dimensions;
	}
        
        public IngameObject getObject(int id) {
            return _objects.get(id);
        }
}
