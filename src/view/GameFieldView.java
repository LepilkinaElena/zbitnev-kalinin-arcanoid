package view;


import java.util.ArrayList;
import java.util.HashMap;

import model.interaction.CollisionListener;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import model.GameField;
import model.IngameObject;
import model.interaction.GenericEventListener;
import model.collision.PublishingCollisionManager;
import model.interaction.GenericEvent;

/**
 * Игровое поле арканоида. Содержит все обекты игры, ответственнен за обновление, рендеринг и
 * проверку стоклновений 
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField {
	
	private HashMap <Integer, IngameObjectView> _objects;
        private SpriteGroup _spriteGroup;
        private ObjectGenericListener _listener;
	
	public GameFieldView(GameField field) {
            _spriteGroup = new SpriteGroup("objects");
            _objects = new HashMap<>();
            _listener = new ObjectGenericListener();
		this.addGroup(this._spriteGroup);
		
		// Добавить на поле менеджеры коллизий для обработки столкновений
		this.addCollisionGroup(this._spriteGroup, this._spriteGroup, new PublishingCollisionManager(field));
	}

	/**
	 * Добавляет представление объекта на это поле. Этот метод добавляет объект в соответствующую группу спрайтов.
	 * @param ov Представление.
	 */
	public void addObject (IngameObjectView ov) {
	    _objects.put(ov.getId(), ov);
	}
	
	/**
	 * Удаляет представление объекта с этого представления поля и из группы спрайтов.
	 * @param ov Представление.
	 */
	private void removeObjectView(int ov) {
	    _objects.remove(ov);
	}
        
        public void addToSpriteGroup(Sprite sprite) {
            _spriteGroup.add(sprite);
        }
	
	/**
	 * Возвращает список представлений объектов на этом поле.
	 * @return Список.
	 */
	public HashMap <Integer, IngameObjectView> getObjectViews() {
	    
	    return (HashMap <Integer, IngameObjectView>) _objects.clone();
	}

        public void addListenerToObject(IngameObject object) {
            object.addGenericEventListener(_listener);
        }
        
    private class ObjectGenericListener implements GenericEventListener{

        @Override
        public void destroyed(GenericEvent event) {
            removeObjectView(event.getElementId());
        }
        
    }
}
