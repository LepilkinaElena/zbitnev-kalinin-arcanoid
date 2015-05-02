package model.collision;

import model.collisionProcessing.IngameObject;

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
