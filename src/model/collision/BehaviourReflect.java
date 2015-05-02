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
        active.setSpeed(active.getSpeed().reflect(passive.getAxis()));
    }
}
