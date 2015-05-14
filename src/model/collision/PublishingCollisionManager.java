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

/**
 * Менеджер столкновений, сообщающий о коллизиях между объектами (При замене библиотеки заменить)
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class PublishingCollisionManager extends AdvanceCollisionGroup {

    /** Карта со столкнушимися объектами */
    private HashMap<IngameObject, ArrayList<IngameObject>> _storage = new HashMap<>();
    /** Карта со столкновения случившимися в предыдущей коллизии */
    private HashMap<IngameObject, ArrayList<IngameObject>> _oldStorage = null;
    /** Игровое поле, на котором происходят столкновения */
    private GameField _gameField;
    /** Список слушателей события */
    private ArrayList<CollisionListener> _collisionListener = new ArrayList<>();

    /**
     * Создать менеджер столкновений
     * 
     * @param gameField игровое поле
     */
    public PublishingCollisionManager(GameField gameField) {
        
        super();
        _gameField = gameField;
        pixelPerfectCollision = false;
    }

    /**
     * Обработать столкновение спрайтов
     * 
     * @param arg0 первый спрайт
     * @param arg1 второй спрайт
     */
    @Override
    public void collided(Sprite arg0, Sprite arg1) {
        
        _storage.clear();
        // Получить карту столкновений
        Map map = getStorage();

        // Создать по ней карту с игровыми объектами
        createIngameObjectMap(map);
        
        // Проверка на повторное срабатывание уже обработанной коллизии
        if (!_storage.equals(_oldStorage))
        {
            // Обработать коллизию
            _oldStorage = (HashMap<IngameObject, ArrayList<IngameObject>>) _storage.clone();
            fireIngameObjectCollided();
            
        } else {
            boolean isFind = false;
            // Сбросить предыдущию коллизию
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
    
    /**
     * Создать карту столкновений игровых объектов
     * 
     * @param storage карта столкновений спрайтов
     */
    private void createIngameObjectMap(Map storage) {
        
        // Создание карты на основе имеющейся
        Set keySet = storage.keySet();
        for (Object keySprite : keySet) {
            if (keySprite != null) {
                // Получить столкнувшиеся игровые объекта по спрайтам
                Sprite[] valueSprites = (Sprite[]) storage.get(keySprite);
                ArrayList<IngameObject> list = new ArrayList<>();
                for (Sprite value : valueSprites) {
                    // Добавляется найденный в игровом поле объект
                    list.add(_gameField.getObject(value.getID()));
                }
                
                // Записать столкнуышиеся игровые объекты в карту
                _storage.put(_gameField.getObject(((Sprite)keySprite).getID()), list);
            }
        }
    }

    //----------------------------- Работа со слушателями ----------------
    
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
