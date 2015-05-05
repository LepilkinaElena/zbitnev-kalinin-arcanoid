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
        Speed2D.Axis axis;
        if (passive.getAxis() == null) {
            if ((active.getPosition().getY() + active.getSize().getHeight()) > passive.getPosition().getY() && (active.getPosition().getY()+ active.getSize().getHeight()) < passive.getPosition().getY() + passive.getSize().getHeight() + active.getSize().getHeight()
                    && (active.getPosition().getX() < passive.getPosition().getX() || active.getPosition().getX() > passive.getPosition().getX() + passive.getSize().getWidth())) {
                axis = Speed2D.Axis.Y;
            } else {
                axis = Speed2D.Axis.X;
            }
        } else {
            axis = passive.getAxis();
        }
        active.setSpeed(active.getSpeed().reflect(axis));
    }
}
