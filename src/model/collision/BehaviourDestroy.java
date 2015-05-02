package model.collision;

import model.collisionProcessing.IngameObject;

/**
 * Поведение разрушения при столкновении.
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class BehaviourDestroy extends CollisionBehaviour {

    /**
     * Экзмепляр синглтона.
     */
    private static BehaviourDestroy _instance = null;

    private BehaviourDestroy() {
    }

    /**
     * Возвращает экземпляр поведения разрушения
     *
     * @return
     */
    public static BehaviourDestroy getInstance() {

        if (_instance == null) {
            _instance = new BehaviourDestroy();
        }

        return _instance;
    }

    @Override
    public void invoke(IngameObject active, IngameObject passive) {
        active.destroy();
    }
}
