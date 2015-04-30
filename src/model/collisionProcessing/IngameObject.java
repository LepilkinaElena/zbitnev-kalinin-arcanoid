package model.collisionProcessing;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Speed2D;

import model.collision.CollisionBehaviour;
import model.collision.BehaviourContainer;
import model.interaction.GenericEvent;
import model.interaction.GenericEventListener;
import service.PublishingSprite;

/**
 * Класс игрового объекта.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class IngameObject {
    
    protected Boolean _isDestroyed = false;
    
    private PublishingSprite sprite;
    
    protected ArrayList<CollisionBehaviour> _defaultColBehaviour = new ArrayList<>();
    protected BehaviourContainer _specialColBehaviours = new BehaviourContainer();
    private ArrayList<GenericEventListener> _geneventListeners = new ArrayList<>();
	
	/**
	 * Создает игровой объект 
	 */
	public IngameObject(PublishingSprite sprite) {
	    this.sprite = sprite;
	}
	
	/**
	 * Создает игровой объект.
	 * @param speed Скорость объекта.
	 */
	public IngameObject(PublishingSprite sprite, Speed2D speed) {
	    this(sprite);
            this.setSpeed(speed);
	}
	
	/**
	 * Получить скорость.
	 * @return Текущая скорость.
	 */
	public Speed2D getSpeed() {

            return (Speed2D) sprite.getSpeed().clone();
	}
	
	/**
	 * Установить скорость.
	 * @param speed Новая скорость.
	 */
	public void setSpeed(Speed2D speed) {
            sprite.setSpeed(speed);
	}
	
	/**
	 * Получить позицию.
	 * @return Текущая позиция.
	 */
	public Point2D.Float getPosition() {

            return (Point2D.Float) sprite.getPosition().clone();
	}
	
	/**
	 * Задать позицию.
	 * @param pos Новая позиция.
	 */
	public void setPosition(Point2D.Float pos) {
            sprite.setPosition(pos);
	}	
	
	/**
	 * Возвращает размер объекта в пикселях.
	 * @return
	 */
	public Dimension getSize() {
	    
	    return (Dimension) sprite.getSize().clone();
	}
	
	/** 
	 * Обрабатывает столкновение с другим объектом.
	 * @param with Объект, столкнувшийся с данным.
         * @param specialBehaviours контейнер со специальными поведениями
	 */
	private void processCollision(IngameObject with, BehaviourContainer specialBehaviours) {
            Iterator<CollisionBehaviour> iterator = specialBehaviours.iterator(with.getClass());
	    
	    while (iterator.hasNext()) {
	        CollisionBehaviour currentBehavior = iterator.next();
                currentBehavior.invoke(with);
	    }
	}
	
        /** 
	 * Обрабатывает столкновение с другим объектом.
	 * @param with Объект, столкнувшийся с данным.
	 */
	void processCollision(IngameObject with) {
            
            if (!_specialColBehaviours.isEmpty()) {
                processCollision(with, _specialColBehaviours);
            } else {
                processCollision(with, _defaultColBehaviour);
            }
        }
        
        /** 
	 * Обрабатывает столкновение с другим объектом.
	 * @param with Объект, столкнувшийся с данным.
	 */
	private void processCollision(IngameObject with, ArrayList<CollisionBehaviour> defaultBehaviours) {
            
            for (CollisionBehaviour behavior : defaultBehaviours) {
                behavior.invoke(with);
            }
        }
	
	/**
	 * Получить словарь специальных поведений при столкновении
	 * @return Ключ -- класс объектов, значение -- флаги и список поведений, определённых при столкновении 
	 *         с этим объектом
	 */
	public BehaviourContainer getSpecificCollisionBehaviours() {
		
		return (BehaviourContainer) _specialColBehaviours.clone();
	}
	
        protected void addDefaultBehaviuor(CollisionBehaviour cb) {
            _defaultColBehaviour.add(cb);
        }
	/**
	 * Добавить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определяемое при столкновении с этим классом объектов
	 * @param checkDerived Если true, то будут также проверяться наследники класса объектов.
	 *                     Игнорируется, если для класса уже задано какое-либо поведение.
	 TO DO ПЕРЕНЕСТИ В КЛАССЫ*/
	/*public void addSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb, boolean checkDerived) {
		
		if (!c.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (!_specialColBehaviours.containsKey(c)) {
			
			BehaviourContainer newsb = new BehaviourContainer(cb);
			newsb._flagCheckDerived = checkDerived;
			_specialColBehaviours.put(c, newsb);
		}
		else {
			_specialColBehaviours.get(c)._behaviours.add(cb);
		}
	}*/
	
    /**
     * Добавить специальное поведение при столкновении
     * @param c Класс объектов
     * @param cb Поведение, определяемое при столкновении с этим классом объектов
     *TO DO/
	/*public void addSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
	    
	    this.addSpecificCollisionBehaviour(c, cb, false);
	}*/
	
	/**
	 * Удалить специальное поведение при столкновении
	 * @param c Класс объектов
	 * @param cb Поведение, определённое при столкновении с этим классом объектов
	 *TO DO/
	/*public void removeSpecificCollisionBehaviour(Class<?> c, CollisionBehaviour cb) {
		
		if (!c.isInstance(IngameObject.class)) {
			// TODO: Выброс исключения, ибо нечего
		}
		
		if (_specialColBehaviours.containsKey(c)) {
			_specialColBehaviours.get(c)._behaviours.remove(cb);
			if (_specialColBehaviours.get(c)._behaviours.isEmpty()) {
			    _specialColBehaviours.remove(c);
			}
		}
	}*/
	
	/**
	 * Очистить список поведений при столкновении по умолчанию
	 */
	private void cleanDefaultCollisionBehaviours() {
		
		_defaultColBehaviour.clear();
	}
	
	/**
	 * Очистить списки специальных поведений при столкновении для всех классов объектов
	 */
	protected void cleanAllSpecificCollisionBehaviours() {
		
		_specialColBehaviours.clear();
	}
	
	/**
	 * Очистить список специальных поведений при столкновений для класса объектов
	 * @param cl Класс объектов, для которых очищается список поведений
	 */
	protected void cleanSpecificCollisionBehaviours(Class<?> cl) {
		
		_specialColBehaviours.removeBehaviour(cl);
	}
	
	/**
	 * Уничтожает объект.
	 * По умолчанию ничего не освобождает.
	 */
	public void destroy() {
		
	    for (GenericEventListener l : _geneventListeners) {
	    	l.destroyed(new GenericEvent(this, getId()));
	    }
	}
	
	/**
	 * Возвращает true, если объект считается уничтоженным.
	 * @return Уничтожен ли объект.
	 */
	public boolean isDestroyed() {
	    
	    return _isDestroyed;
	}
	
	/**
	 * Добавить слушателя событий жизни объекта.
	 * @param l Добавляемый слушатель.
	 */
	public void addGenericEventListener(GenericEventListener l) {

		_geneventListeners.add(l);
	}
	
	/**
	 * Удалить слушателя событий жизни объекта.
	 * @param l Удаляемый слушатель.
	 */
	public void removeGenericEventListener(GenericEventListener l) {
		_geneventListeners.remove(l);
	}
	
	public IngameObject clone() {
		
		IngameObject clone = new IngameObject(sprite.clone());
            clone._isDestroyed = this._isDestroyed;
	
		return clone;
	}
        
        public int getId() {
            return sprite.getId();
        }
        
        
}