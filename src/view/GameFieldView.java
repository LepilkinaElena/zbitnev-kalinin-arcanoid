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

    private HashMap<Integer, IngameObjectView> _objects;
    private SpriteGroup _spriteGroup;
    private ObjectGenericListener _listener;
    private ObjectCollisionManager _manager;

    public GameFieldView(GameField field) {
        _spriteGroup = new SpriteGroup("objects");
        _objects = new HashMap<>();
        _listener = new ObjectGenericListener();
        this.addGroup(this._spriteGroup);
        _manager = new ObjectCollisionManager(field);
        // Добавить на поле менеджеры коллизий для обработки столкновений
        this.addCollisionGroup(this._spriteGroup, this._spriteGroup, _manager.getObjectManager());
        addCollisionGroup(_spriteGroup, null, _manager.getBoundaryManager());
    }

    /**
     * Добавляет представление объекта на это поле. Этот метод добавляет объект
     * в соответствующую группу спрайтов.
     *
     * @param ov Представление.
     */
    public void addObject(IngameObjectView ov) {
        _objects.put(ov.getId(), ov);
    }

    /**
     * Удаляет представление объекта с этого представления поля и из группы
     * спрайтов.
     *
     * @param ov Представление.
     */
    private void removeObjectView(int ov) {
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
        _objects.get(ov).removeFromSpriteGroup(clone);
        _spriteGroup.clear();
        for (Sprite sprite:clone.getSprites()) {
            if (sprite != null) {
                addToSpriteGroup(sprite);
            }
        }
        _objects.remove(ov);
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

        return (HashMap<Integer, IngameObjectView>) _objects.clone();
    }

    public void addListenerToObject(IngameObject object) {
        object.addGenericEventListener(_listener);
    }

    private class ObjectGenericListener implements GenericEventListener {

        @Override
        public void destroyed(GenericEvent event) {
            removeObjectView(event.getElementId());
        }

    }
}
