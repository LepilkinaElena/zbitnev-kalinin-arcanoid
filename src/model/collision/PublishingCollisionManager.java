package model.collision;

import java.util.ArrayList;
import java.util.HashMap;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import java.util.Map;
import java.util.Set;
import model.GameField;
import model.collisionProcessing.IngameObject;
import model.interaction.CollisionEvent;
import model.interaction.CollisionListener;
import model.paddle.Paddle;
import service.UniqSprite;

/**
 * Менеджер столкновений, сообщающий о коллизиях между объектами
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingCollisionManager extends AdvanceCollisionGroup {

    private HashMap<IngameObject, ArrayList<IngameObject>> _storage = new HashMap<>();
    private HashMap<IngameObject, ArrayList<IngameObject>> _oldStorage = null;

    /**
     * Список слушателей события
     */
    private ArrayList<CollisionListener> _collisionListener = new ArrayList<>();
    private GameField _gameField;

    public PublishingCollisionManager(GameField gameField) {
        super();
        _gameField = gameField;
        pixelPerfectCollision = false;
    }

    @Override
    public void collided(Sprite arg0, Sprite arg1) {
        _storage.clear();
        Map map = getStorage();

        createIngameObjectMap(map);
        if (!_storage.equals(_oldStorage))
        {
            _oldStorage = (HashMap<IngameObject, ArrayList<IngameObject>>) _storage.clone();
            fireIngameObjectCollided();
            
        } else {
            boolean isFind = false;
            if (_oldStorage.size() == 2) {
                for (IngameObject object:_oldStorage.keySet()) {
                    if (object != null &&  object.getClass() == Paddle.class) {
                        isFind = true;
                    }
                }
                if (isFind) {
                    _oldStorage = null;
                }
                
            }
        }
    }
    
    private void createIngameObjectMap(Map storage) {
        Set keySet = storage.keySet();
        for (Object keySprite : keySet) {
            // Нет такого ключа
            if (keySprite != null) {
                
                Sprite[] valueSprites = (Sprite[]) storage.get(keySprite);
                ArrayList<IngameObject> list = new ArrayList<>();
                for (Sprite value : valueSprites) {
                    // Добавляется найденный в игровом поле объект
                    list.add(_gameField.getObject(((UniqSprite)value).getId()));
                }
                _storage.put(_gameField.getObject(((UniqSprite)keySprite).getId()), list);
            }
        }
    }

    /*public HashMap<IngameObject, ArrayList<IngameObject>> getStorage() {
        return (HashMap<IngameObject, ArrayList<IngameObject>>) _storage.clone();
    }*/

    //-------------------------------------
    /**
     * Добавление слушателей события о том, что столкнулись игровые объекты
     *
     * @param element слушатель
     */
    public void addCollisionListener(CollisionListener element) {
        _collisionListener.add(element);
    }

    /**
     * Испустить событие о том, что столкнулись игровые объекты
     */
    private void fireIngameObjectCollided() {
        for (CollisionListener listener : _collisionListener) {
            listener.collisionOccured(new CollisionEvent(this, _storage));
        }
    }
}
