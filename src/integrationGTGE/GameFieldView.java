package integrationGTGE;

import java.util.HashMap;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.util.ArrayList;
import model.GameField;
import model.collisionProcessing.IngameObject;
import model.interaction.GenericEventListener;
import model.collisionProcessing.ObjectCollisionManager;
import model.interaction.ObjectDestroiedEvent;

/**
 * Игровое поле арканоида. Содержит все обекты игры
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField {

    /** Представления объектов игры */
    private HashMap<Integer, IngameObjectView> _ingameObjectsView;
    /** Группа спрайтов */
    private SpriteGroup _spriteGroup;
    /** Менеджер столкновений объектов игры */
    private ObjectCollisionManager _objCollisionManager;
    /** Слушатель удаления объектов */
    private ObjectGenericListener _objGenericListener;
    
    /**
     * Создать представление игрового поля
     * 
     * @param field модель игрового поля
     */
    public GameFieldView(GameField field) {
        
        _spriteGroup = new SpriteGroup("objects");
        _ingameObjectsView = new HashMap<>();
        _objGenericListener = new ObjectGenericListener();
        addGroup(_spriteGroup);
        _objCollisionManager = new ObjectCollisionManager(field, this);
    }

    /**
     * Добавляет представление объекта на это поле.
     *
     * @param ingameObjectView Представление.
     */
    public void addObject(IngameObjectView ingameObjectView) {
        
        _ingameObjectsView.put(ingameObjectView.getId(), ingameObjectView);
    }

    /**
     * Удаляет представление объекта с этого представления поля и из группы
     * спрайтов.
     *
     * @param ingameObjectViewID идентификатор объекта.
     */
    private void removeObjectView(int ingameObjectViewID) {
        
        
        _ingameObjectsView.get(ingameObjectViewID).removeFromFieldView(this);
        _ingameObjectsView.remove(ingameObjectViewID);
        
    }

    /**
     * Добавить в группу спрайтов
     * 
     * @param sprite спрайт
     */
    void addToSpriteGroup(Sprite sprite) {
        
        _spriteGroup.add(sprite);
    }
    
    void removeFromSpriteGroup(Sprite sprite) {
        SpriteGroup clone = new SpriteGroup("clone");
        ArrayList<Sprite> list = new ArrayList<Sprite>();
        for (Sprite currentSprite:_spriteGroup.getSprites()) {
            if (currentSprite != null) {
                
                if (!list.contains(currentSprite)) {
                    list.add(currentSprite);
                    clone.add(currentSprite);
                }
            }
        }
        clone.remove(sprite);
        _spriteGroup.clear();
        list.clear();
        for (Sprite currentSprite:clone.getSprites()) {
            if (currentSprite != null) {
                if (!list.contains(currentSprite)) {
                    list.add(currentSprite);
                    addToSpriteGroup(currentSprite);
                }
            }
        }
        
        
    }
    
    public void connectWithSpriteGroup(PublishingCollisionManager _objectManager, BoundaryCollisionManager _boundaryManager) {
         
         // Добавить на поле менеджеры коллизий для обработки столкновений
        addCollisionGroup(_spriteGroup, _spriteGroup, _objectManager);
        addCollisionGroup(_spriteGroup, null, _boundaryManager);
    }

    /**
     * Возвращает список представлений объектов на этом поле.
     *
     * @return Список объектов.
     */
    public HashMap<Integer, IngameObjectView> getObjectViews() {

        return (HashMap<Integer, IngameObjectView>) _ingameObjectsView.clone();
    }

    //------------------- Работа со слушателями --------------------
    
    /**
     * Добавить слушателя разрушения объекта
     * 
     * @param object объект
     */
    public void addListenerToObject(IngameObject object) {
        
        object.addGenericEventListener(_objGenericListener);
    }

    /**
     * Класс слушателя разрушения объектов
     * 
     */
    private class ObjectGenericListener implements GenericEventListener {

        @Override
        public void destroyed(ObjectDestroiedEvent event) {
            
            removeObjectView(event.getElementId());
        }

    }
}
