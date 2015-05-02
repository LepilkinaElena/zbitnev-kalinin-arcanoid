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
import service.UniqSprite;

/**
 * Менеджер столкновений, сообщающий о коллизиях между объектами
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingCollisionManager extends AdvanceCollisionGroup {

    private HashMap<IngameObject, ArrayList<IngameObject>> _storage = new HashMap<>();
    /**
     * Список слушателей события
     */
    private ArrayList<CollisionListener> _collisionListener = new ArrayList<>();
    private GameField _field;

    public PublishingCollisionManager(GameField field) {
        super();
        _field = field;
    }

    @Override
    public void collided(Sprite arg0, Sprite arg1) {
        /*Map map = getStorage();
        createIngameObjectMap(map);*/
        ArrayList<IngameObject> array1 = new ArrayList<IngameObject>();
        array1.add(_field.getObject(((UniqSprite)arg1).getId()));
        _storage.put(_field.getObject(((UniqSprite)arg0).getId()), array1);
        
        ArrayList<IngameObject> array2 = new ArrayList<IngameObject>();
        array2.add(_field.getObject(((UniqSprite)arg0).getId()));
        _storage.put(_field.getObject(((UniqSprite)arg1).getId()), array2);
        fireIngameObjectCollided();
    }

    private void createIngameObjectMap(Map storage) {
        Set keySet = storage.keySet();
        for (Object keySprite : keySet) {
            // Нет такого ключа
            if (_storage.get(keySprite) == null) {
                Sprite[] valueSprites = (Sprite[]) storage.get(keySprite);
                ArrayList<IngameObject> list = new ArrayList<>();
                for (Sprite value : valueSprites) {
                    // Добавляется найденный в игровом поле объект
                    list.add(_field.getObject(((UniqSprite)value).getId()));
                }
            }
        }
    }

    public HashMap<IngameObject, ArrayList<IngameObject>> getStorage() {
        return (HashMap<IngameObject, ArrayList<IngameObject>>) _storage.clone();
    }

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
