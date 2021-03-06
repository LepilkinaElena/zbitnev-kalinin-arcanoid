package model.collision;

import java.util.ArrayList;
import model.Speed2D;
import model.collisionProcessing.IngameObject;

/**
 * Поведение упрогого отскока при столкновении.
 *
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BehaviourBallRebound extends CollisionBehaviour {

    /**
     * Экзмепляр синглтона.
     */
    private static BehaviourBallRebound _instance = null;

    private BehaviourBallRebound() {
    }

    /**
     * Возвращает экземпляр поведения упрогого отскока.
     *
     * @return
     */
    public static BehaviourBallRebound getInstance() {

        if (_instance == null) {
            _instance = new BehaviourBallRebound();
        }

        return _instance;
    }

    @Override
    public void invoke(IngameObject active, IngameObject passive) {
        
        if (passive.getSpeed().y() != 0.0) {
            ArrayList<Speed2D> countSpeed = countSpeed(active, passive);
            active.setSpeed(countSpeed.get(0));
        } else {
            active.setSpeed(active.getSpeed().flipHorizontal().flipVertical());
        }
    }
    
    /**
     * Высчитать скорость после столкновения двух элементов
     * @param active первый элемент
     * @param passive второй элемент
     * 
     * @return массив скоростей для двух элементов
     */
    private ArrayList<Speed2D> countSpeed(IngameObject active, IngameObject passive) {
        
        ArrayList<Speed2D> result = new ArrayList();
        double speedX1;
        double speedY1;
        double speedX2;
        double speedY2;
        // Подсчет новых значений скоростей
        speedX1 = (2*passive.getWeight()*passive.getSpeed().x() + (active.getWeight() - passive.getWeight()) * active.getSpeed().x())/(active.getWeight() + passive.getWeight());
        speedY1 = (2*passive.getWeight()*passive.getSpeed().y() + (active.getWeight() - passive.getWeight()) * active.getSpeed().y())/(active.getWeight() + passive.getWeight());
        speedX2 = (2*active.getWeight()* active.getSpeed().x() + (passive.getWeight() - active.getWeight())*passive.getSpeed().x())/(active.getWeight() + passive.getWeight());
        speedY2 = (2*active.getWeight()* active.getSpeed().y() + (passive.getWeight() - active.getWeight())*passive.getSpeed().y())/(active.getWeight() + passive.getWeight());
        result.add(new Speed2D(speedX1, speedY1));
        result.add(new Speed2D(speedX2, speedY2));
        return result;
    }
}
