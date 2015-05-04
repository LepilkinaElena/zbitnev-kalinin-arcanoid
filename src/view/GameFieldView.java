package view;

import java.util.HashMap;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import java.util.ArrayList;
import java.util.Collection;
import model.GameField;
import model.collisionProcessing.IngameObject;
import model.interaction.GenericEventListener;
import model.collisionProcessing.ObjectCollisionManager;
import model.interaction.GenericEvent;

/**
 * Игровое поле арканоида. Содержит все обекты игры, ответственнен за
 * обновление, рендеринг и проверку стоклновений
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField {

    private HashMap<Integer, IngameObjectView> _ingameObjectsView;
    private SpriteGroup _spriteGroup;
    private ObjectGenericListener _objGenericListener;
    private ObjectCollisionManager _objCollisionManager;

    public GameFieldView(GameField field) {
        _spriteGroup = new SpriteGroup("objects");
        _ingameObjectsView = new HashMap<>();
        _objGenericListener = new ObjectGenericListener();
        addGroup(_spriteGroup);
        _objCollisionManager = new ObjectCollisionManager(field);
        // Добавить на поле менеджеры коллизий для обработки столкновений
        addCollisionGroup(_spriteGroup, _spriteGroup, _objCollisionManager.getObjectManager());
        addCollisionGroup(_spriteGroup, null, _objCollisionManager.getBoundaryManager());
    }

    /**
     * Добавляет представление объекта на это поле. Этот метод добавляет объект
     * в соответствующую группу спрайтов.
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
     * @param ingameObjectViewID Представление.
     */
    private void removeObjectView(int ingameObjectViewID) {
        SpriteGroup clone = new SpriteGroup("clone");
        ArrayList<Sprite> list = new ArrayList<Sprite>();
        for (Sprite sprite:_spriteGroup.getSprites()) {
            if (sprite != null) {
                
                if (!list.contains(sprite)) {
                    list.add(sprite);
                    clone.add(sprite);
                }
            }
        }
        _ingameObjectsView.get(ingameObjectViewID).removeFromSpriteGroup(clone);
        _spriteGroup.clear();
        for (Sprite sprite:clone.getSprites()) {
            if (sprite != null) {
                addToSpriteGroup(sprite);
            }
        }
        _ingameObjectsView.remove(ingameObjectViewID);
    }

    public void addToSpriteGroup(Sprite sprite) {
        _spriteGroup.add(sprite);
    }

    /**
     * Возвращает список представлений объектов на этом поле.
     *
     * @return Список.
     */
    public HashMap<Integer, IngameObjectView> getObjectViews() {

        return (HashMap<Integer, IngameObjectView>) _ingameObjectsView.clone();
    }

    public void addListenerToObject(IngameObject object) {
        object.addGenericEventListener(_objGenericListener);
    }

    private class ObjectGenericListener implements GenericEventListener {

        @Override
        public void destroyed(GenericEvent event) {
            removeObjectView(event.getElementId());
        }

    }
}
