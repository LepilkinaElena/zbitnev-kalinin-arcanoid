/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import model.Speed2D;
import model.collisionProcessing.IngameObject;

/**
 * Поведение отражения
 *
 * @author Елена
 */
public class BehaviourReflect extends CollisionBehaviour {

    /**
     * Экзмепляр синглтона.
     */
    private static BehaviourReflect _instance = null;

    private BehaviourReflect() {
    }

    /**
     * Возвращает экземпляр поведения отражения.
     *
     * @return
     */
    public static BehaviourReflect getInstance() {

        if (_instance == null) {
            _instance = new BehaviourReflect();
        }

        return _instance;
    }

    @Override
    public void invoke(IngameObject active, IngameObject passive) {
        
        // Ось отражения
        Speed2D.Axis axis;
        
        //Если ось не задана
        if (passive.getAxis() == null) {
            // Получаем ось по позициям столкнувшихся объектов
            if ((active.getPosition().getY() + active.getSize().getHeight()) > passive.getPosition().getY() && (active.getPosition().getY()+ active.getSize().getHeight()) < passive.getPosition().getY() + passive.getSize().getHeight() + active.getSize().getHeight()
                    && (active.getPosition().getX() < passive.getPosition().getX() || active.getPosition().getX() > passive.getPosition().getX() + passive.getSize().getWidth())) {
                axis = Speed2D.Axis.Y;
            } else {
                axis = Speed2D.Axis.X;
            }
        } else {
            // Получаем ось объекта
            axis = passive.getAxis();
        }
        
        // Отражаем скорость
        active.setSpeed(active.getSpeed().reflect(axis));
    }
}
