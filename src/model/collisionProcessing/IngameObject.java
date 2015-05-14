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
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public abstract class IngameObject implements Cloneable {

    /** Спрайт игрового объекта */
    private PublishingSprite _publPublishingSprite;
    /** Ось игрового объекта */
    private Speed2D.Axis _axis = null;
    /** Поведения по умолчанию игрового объекта */
    protected ArrayList<CollisionBehaviour> _defaultColBehaviour = new ArrayList<>();
    /** Контейнер со специальными поведениями игрового объекта */
    protected BehaviourContainer _specialColBehaviours = new BehaviourContainer();
    /** Вес элемента */
    private double _weight = 0.0;
    /** Слушатели события разрушения элемента */
    private ArrayList<GenericEventListener> _geneventListeners = new ArrayList<>();

    /**
     * Создает игровой объект
     */
    public IngameObject(PublishingSprite publishingSprite) {
        
        _publPublishingSprite = publishingSprite;
        setAxis(_axis);
    }

    /**
     * Создает игровой объект.
     *
     * @param publishingSprite спрайт объекта
     * @param speed Скорость объекта.
     */
    public IngameObject(PublishingSprite publishingSprite, Speed2D speed) {
        
        this(publishingSprite);
        setSpeed(speed);
    }

    /**
     * Создать объект
     */
    protected IngameObject() {
        
        this(null);
    }

    /**
     * Получить скорость.
     *
     * @return Текущая скорость.
     */
    public Speed2D getSpeed() {

        return (Speed2D) _publPublishingSprite.getSpeed().clone();
    }

    /**
     * Установить ось
     * 
     * @param axis ось
     */
    protected void setAxis(Speed2D.Axis axis) {
        
        if (_publPublishingSprite == null) {
            _axis = axis;
        }
    }
    
    /**
     * Установить вес
     * 
     * @param weight вес
     */
    protected void setWeight(double weight) {
        
        _weight = weight;
    }
    
    /**
     * Поучить вес 
     * 
     * @return  вес
     */
    public double getWeight() {
        
        return _weight;
    }

    /**
     * Получить ось
     * 
     * @return ось
     */
    public Speed2D.Axis getAxis() {
        
        return _axis;
    }

    /**
     * Установить скорость.
     *
     * @param speed Новая скорость.
     */
    public void setSpeed(Speed2D speed) {
        
        if (_publPublishingSprite != null) {
            _publPublishingSprite.setSpeed(speed);
        }
    }

    /**
     * Получить позицию.
     *
     * @return Текущая позиция.
     */
    public Point2D.Float getPosition() {

        return (Point2D.Float) _publPublishingSprite.getPosition().clone();
    }

    /**
     * Задать позицию.
     *
     * @param pos Новая позиция.
     */
    public void setPosition(Point2D.Float pos) {
        
        _publPublishingSprite.setPosition(pos);
    }

    /**
     * Возвращает размер объекта в пикселях.
     *
     * @return размер объекта в пикселях
     */
    public Dimension getSize() {

        return (Dimension) _publPublishingSprite.getSize().clone();
    }

    /**
     * Обрабатывает столкновение с другим объектом.
     *
     * @param with Объект, столкнувшийся с данным.
     */
    void processCollision(IngameObject with) {

        // Если есть специальные поведения, выполняются они
        if (_specialColBehaviours.contains(with.getClass())) {
            _specialColBehaviours.invoke(this, with);
        } else {
            for (CollisionBehaviour behavior : _defaultColBehaviour) {
                behavior.invoke(this, with);
            }
        }
    }

    /**
     * Добавить поведение по умолчанию
     * 
     * @param collisionBehaviour поведение
     */
    protected void addDefaultBehaviuor(CollisionBehaviour collisionBehaviour) {
        
        _defaultColBehaviour.add(collisionBehaviour);
    }

    /**
     * Добавить специальное поведение при столкновении
     *
     * @param className класс
     * @param behaviour поведение
     */
    public void addSpecificCollisionBehaviour(Class<?> className, CollisionBehaviour behaviour) {

        this._specialColBehaviours.addBehaviour(className, behaviour);
    }

    /**
     * Удалить специальное поведение при столкновении
     *
     * @param className класс объектов
     * @param behaviour поведение
     */
    public void removeSpecificCollisionBehaviour(Class<?> className, CollisionBehaviour behaviour) {
        this._specialColBehaviours.removeBehaviour(className, behaviour);
    }

    /**
     * Очистить список поведений при столкновении по умолчанию
     */
    private void cleanDefaultCollisionBehaviours() {

        _defaultColBehaviour.clear();
    }

    /**
     * Очистить списки специальных поведений при столкновении для всех классов
     * объектов
     */
    protected void cleanAllSpecificCollisionBehaviours() {

        _specialColBehaviours.clear();
    }

    /**
     * Очистить список специальных поведений при столкновений для класса
     * объектов
     *
     * @param className Класс объектов, для которых очищается список поведений
     */
    protected void cleanSpecificCollisionBehaviours(Class<?> className) {

        _specialColBehaviours.removeBehaviour(className);
    }

    /**
     * Клонировать объект
     * 
     * @return клон объекта
     */
    @Override
    public IngameObject clone() {

        IngameObject clone = null;
        try {
            clone = (IngameObject) super.clone();
            clone._publPublishingSprite = this._publPublishingSprite.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(IngameObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clone;
    }

    /**
     * Получить идентификатор игрового объекта
     * 
     * @return идентификатор
     */
    public int getId() {
        
        return _publPublishingSprite.getId();
    }
    
    //------------------ Работа со слушателями -------------------
    
    /**
     * Уничтожает объект.
     */
    public void destroy() {

        for (GenericEventListener genericEventListener : _geneventListeners) {
            genericEventListener.destroyed(new GenericEvent(this, getId()));
        }
    }

    /**
     * Добавить слушателя событий жизни объекта.
     *
     * @param  genericEventListener Добавляемый слушатель.
     */
    public void addGenericEventListener(GenericEventListener genericEventListener) {

        _geneventListeners.add(genericEventListener);
    }

    /**
     * Удалить слушателя событий жизни объекта.
     *
     * @param genericEventListener Удаляемый слушатель.
     */
    public void removeGenericEventListener(GenericEventListener genericEventListener) {
        
        _geneventListeners.remove(genericEventListener);
    }

}
