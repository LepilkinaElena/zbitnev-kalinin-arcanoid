/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collision;

import model.Speed2D;
import model.collisionProcessing.IngameObject;

/**
 *
 * @author Елена
 */
public class BehaviourStop extends CollisionBehaviour {
    /**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourStop _instance = null;
	
	private BehaviourStop() {
	}
	
	/**
	 * Возвращает экземпляр поведения поглощения.
	 * @return
	 */
	public static BehaviourStop getInstance() {
		
		if (_instance == null) {
			_instance = new BehaviourStop();
		}
		
		return _instance;
	}
	
	@Override
	public void invoke(IngameObject active, IngameObject passive) {
            if (passive.getAxis() == Speed2D.Axis.Y) {
                active.setSpeed(new Speed2D());
            }
	}
}
