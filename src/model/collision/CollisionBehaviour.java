package model.collision;

import model.collisionProcessing.IngameObject;

/**
 * Поведение столкнувшегося объекта; синглтон.
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class CollisionBehaviour {

    /**
     * Экзмепляр синглтона.
     */
    private static CollisionBehaviour _instance = null;

    protected CollisionBehaviour() {
    }

    /**
     * Возвращает экзмепляр поведения объектов. Обязательно переопределяется в
     * классах-наследниках.
     *
     * @return Экземпляр поведения.
     */
    public static CollisionBehaviour getInstance() {

        if (_instance == null) {
            _instance = new CollisionBehaviour();
        }

        return _instance;
    }

    /**
     * Осуществить поведение активного объекта в ответ на столкновение.
     *
     * @param active активный объект столкновения
     * @param passive пассивный объект столкновения
     */
    public void invoke(IngameObject active, IngameObject passive) {
        
    }
}
