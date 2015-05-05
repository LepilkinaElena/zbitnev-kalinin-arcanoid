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

    protected Boolean _isDestroyed = false;

    private PublishingSprite _publPublishingSprite;

    private Speed2D.Axis _axis = null;

    protected ArrayList<CollisionBehaviour> _defaultColBehaviour = new ArrayList<>();
    protected BehaviourContainer _specialColBehaviours = new BehaviourContainer();
    private ArrayList<GenericEventListener> _geneventListeners = new ArrayList<>();
    private double _weight = 0.0;

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
     * @param speed Скорость объекта.
     */
    public IngameObject(PublishingSprite publishingSprite, Speed2D speed) {
        this(publishingSprite);
        setSpeed(speed);
    }

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

    protected void setAxis(Speed2D.Axis axis) {
        if (_publPublishingSprite == null) {
            _axis = axis;
        }
    }
    
    protected void setWeight(double weight) {
        _weight = weight;
    }
    
    public double getWeight() {
        return _weight;
    }

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
     * @return
     */
    public Dimension getSize() {

        return (Dimension) _publPublishingSprite.getSize().clone();
    }

    /**
     * Обрабатывает столкновение с другим объектом.
     *
     * @param with Объект, столкнувшийся с данным.
     * @param specialBehaviours контейнер со специальными поведениями
     */
    private void processCollision(IngameObject with, BehaviourContainer specialBehaviours) {
        Iterator<CollisionBehaviour> iterator = specialBehaviours.iterator(with.getClass());

        while (iterator.hasNext()) {
            CollisionBehaviour currentBehavior = iterator.next();
            currentBehavior.invoke(this, with);
        }
    }

    /**
     * Обрабатывает столкновение с другим объектом.
     *
     * @param with Объект, столкнувшийся с данным.
     */
    void processCollision(IngameObject with) {

        if (_specialColBehaviours.contains(with.getClass())) {
            processCollision(with, _specialColBehaviours);
        } else {
            processCollision(with, _defaultColBehaviour);
        }
    }

    /**
     * Обрабатывает столкновение с другим объектом.
     *
     * @param with Объект, столкнувшийся с данным.
     */
    private void processCollision(IngameObject with, ArrayList<CollisionBehaviour> defaultBehaviours) {

        for (CollisionBehaviour behavior : defaultBehaviours) {
            behavior.invoke(this, with);
        }
    }

    /**
     * Получить словарь специальных поведений при столкновении
     *
     * @return Ключ -- класс объектов, значение -- флаги и список поведений,
     * определённых при столкновении с этим объектом
     */
    public BehaviourContainer getSpecificCollisionBehaviours() {

        return (BehaviourContainer) _specialColBehaviours.clone();
    }

    protected void addDefaultBehaviuor(CollisionBehaviour collisionBehaviour) {
        _defaultColBehaviour.add(collisionBehaviour);
    }

    /**
     * Добавить специальное поведение при столкновении
     *
     * @param className
     * @param behaviour
     */
    public void addSpecificCollisionBehaviour(Class<?> className, CollisionBehaviour behaviour) {

        this._specialColBehaviours.addBehaviour(className, behaviour);
    }

    /**
     * Удалить специальное поведение при столкновении
     *
     * @param c Класс объектов
     * @param cb Поведение, определённое при столкновении с этим классом
     * объектов
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
     * Уничтожает объект. По умолчанию ничего не освобождает.
     */
    public void destroy() {

        for (GenericEventListener genericEventListener : _geneventListeners) {
            genericEventListener.destroyed(new GenericEvent(this, getId()));
        }
    }

    /**
     * Возвращает true, если объект считается уничтоженным.
     *
     * @return Уничтожен ли объект.
     */
    public boolean isDestroyed() {

        return _isDestroyed;
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

    @Override
    public IngameObject clone() {

        IngameObject clone = null;
        try {
            clone = (IngameObject) super.clone();
            clone._publPublishingSprite = this._publPublishingSprite.clone();
            clone._isDestroyed = this._isDestroyed;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(IngameObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clone;
    }

    public int getId() {
        return _publPublishingSprite.getId();
    }



}
