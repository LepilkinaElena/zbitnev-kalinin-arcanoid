package model.collision;

import java.awt.Point;
import model.collisionProcessing.IngameObject;
import java.awt.geom.Point2D;
import model.Speed2D;
import model.Speed2D.Axis;

/**
 * Поведение отскока от ракетки при столкновении.
 *
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
     *
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
        //с управляемым элементом
        Point2D.Float positionCollision = new Point2D.Float();
        positionCollision.x = active.getPosition().x + (int)active.getSize().width/2;
        positionCollision.y = active.getPosition().y + (int)active.getSize().height;
        Point2D.Float positionMiddleRacket = new Point2D.Float();
        positionMiddleRacket.x = passive.getPosition().x+(int)passive.getSize().width/2;
        positionMiddleRacket.y = passive.getPosition().y;
        double lengthHalfRacket = passive.getSize().width/2.0;

        //Проверка на столкновение с углом элемента        
        boolean collisionRightConer = positionCollision.x > positionMiddleRacket.x + lengthHalfRacket &&
                positionMiddleRacket.y < passive.getPosition().y + active.getSize().height/2;
        boolean collisionLeftConer = positionCollision.x < positionMiddleRacket.x - lengthHalfRacket &&
                positionMiddleRacket.y < passive.getPosition().y + active.getSize().height/2;
        // Мячик летит к ракетке к углу элемента
        if (collisionRightConer && active.getSpeed().x() < 0 || 
                collisionLeftConer && active.getSpeed().x() > 0) {
            active.setSpeed (active.getSpeed().reflect(Axis.Z));
        // Мячик летит вдоль ракетки к углу элемента
        } else if (collisionRightConer && active.getSpeed().x() > 0 || 
                collisionLeftConer && active.getSpeed().x() < 0) { 
            active.setSpeed (active.getSpeed().reflect(Axis.Y));
        // Мячик летит на угол элемента под прямым углом
        } else if ((collisionRightConer || collisionLeftConer) && active.getSpeed().x() == 0) {
            active.setSpeed(active.getSpeed().flipHorizontal().flipVertical());
        // Мячик попадает на поверхность элемента
        } else if (positionCollision.x > passive.getPosition().x &&
                positionCollision.x < passive.getPosition().x + passive.getSize().width) {
            double lengthSpeedVector = active.getSpeed().value();
            double distanceToMiddleRacket = positionCollision.x > positionMiddleRacket.x ?
                    positionCollision.x - positionMiddleRacket.x :
                    positionMiddleRacket.x - positionCollision.x;
            double angleNewSpeedVector = Math.acos(distanceToMiddleRacket/lengthHalfRacket);
            double newSpeedVectorX = positionCollision.x > positionMiddleRacket.x ?
                    Math.cos(angleNewSpeedVector)*lengthSpeedVector :
                    - (Math.cos(angleNewSpeedVector)*lengthSpeedVector);
            double newSpeedVectorY = - (Math.sin(angleNewSpeedVector)*lengthSpeedVector);
            Speed2D newSpeedVector = new Speed2D(newSpeedVectorX, newSpeedVectorY);
            active.setSpeed(newSpeedVector);
        }
    }
}
