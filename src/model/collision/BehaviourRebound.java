package model.collision;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import com.golden.gamedev.object.collision.CollisionGroup;

import model.collisionProcessing.IngameObject;
import model.Speed2D;
import model.ball.Ball;
import model.brick.Brick;
import model.paddle.Paddle;
import math.geom2d.Vector2D;

/**
 * Поведение упрогого отскока при столкновении.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourRebound extends CollisionBehaviour {

    /**
     * Экзмепляр синглтона.
     */
    private static BehaviourRebound _instance = null;

    private BehaviourRebound() {
    }

    /**
     * Возвращает экземпляр поведения упрогого отскока.
     *
     * @return
     */
    public static BehaviourRebound getInstance() {

        if (_instance == null) {
            _instance = new BehaviourRebound();
        }

        return _instance;
    }

    @Override
    public void invoke(IngameObject active, IngameObject passive) {

    }
}
