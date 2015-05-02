package model.collision;

import java.awt.geom.Point2D;

import model.collisionProcessing.IngameObject;
import model.ball.Ball;
import model.paddle.Paddle;

/**
 * Поведение отскока от ракетки при столкновении.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourPaddleRebound extends CollisionBehaviour {
	/**
	 * Экзмепляр синглтона.
	 */
	private static BehaviourPaddleRebound _instance = null;
	
	private BehaviourPaddleRebound() {
	}
	
	/**
	 * Возвращает экземпляр поведения отражения от ракетки.
	 * @return
	 */
	public static BehaviourPaddleRebound getInstance() {
		
		if (_instance == null) {
			_instance = new BehaviourPaddleRebound();
		}
		
		return _instance;
	}
	
	@Override
	public void invoke(IngameObject active, IngameObject passive) {
		
	}
}
