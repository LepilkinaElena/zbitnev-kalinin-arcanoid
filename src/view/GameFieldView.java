package view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.IngameObject;
import model.ball.Ball;
import model.brick.Brick;
import model.collision.CollidedObject;
import model.interaction.CollisionListener;
import model.paddle.Paddle;

import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import model.interaction.GenericEventListener;
import model.collision.PublishingCollisionManager;

/**
 * Игровое поле арканоида. Содержит все обекты игры, ответственнен за обновление, рендеринг и
 * проверку стоклновений 
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameFieldView extends PlayField {
	
	private ArrayList<IngameObjectView> _objectViews = new ArrayList<>();
        private SpriteGroup _spriteGroup = new SpriteGroup("objects");
	
	public GameFieldView() {
		
		this.addGroup(this._spriteGroup);
		
		// Добавить на поле менеджеры коллизий для обработки столкновений
		this.addCollisionGroup(this._spriteGroup, this._spriteGroup, new PublishingCollisionManager());
	}

	/**
	 * Добавляет представление объекта на это поле. Этот метод добавляет объект в соответствующую группу спрайтов.
	 * @param ov Представление.
	 */
	public void addObjectView(IngameObjectView ov) {
	    
	    _objectViews.add(ov);
	}
	
	/**
	 * Удаляет представление объекта с этого представления поля и из группы спрайтов.
	 * @param ov Представление.
	 */
	public void removeObjectView(IngameObjectView ov) {
	    
	    _objectViews.remove(ov);
	}
        
        public void addToSpriteGroup(Sprite sprite) {
            _spriteGroup.add(sprite);
        }
	
	/**
	 * Возвращает список представлений объектов на этом поле.
	 * @return Список.
	 */
	public ArrayList<IngameObjectView> getObjectViews() {
	    
	    return (ArrayList<IngameObjectView>) _objectViews.clone();
	}
	
    /**
     * Добавить слушателя событий о произошедших на поле столкновениях
     * @param l Добавляемый слушатель
     */
    public void addCollisionListener(CollisionListener l) {
    	_collisionListners.add(l);
    }
    
    /**
     * Удалить слушателя событий о произошедших на поле столкновениях
     * @param l Удаляемый слушатель
     */
    public void removeCollisionListener(CollisionListener l) {
    	_collisionListners.remove(l);
    }
    
    /**
     * Копирует сообщения о столкновениях из одного словаря в другой
     * @param to Словарь, который будет дополнен новыми сообщениями
     * @param from Словарь, из которого будут скопированы сообщения
     */
    private void attachStorage(HashMap<CollidedObject, ArrayList<CollidedObject>> to,
    		HashMap<CollidedObject, ArrayList<CollidedObject>> from) {
    	
    	for (CollidedObject obj : from.keySet()) {
    		
    		// Если такого ключа не содержится -- просто добавляем новую запись в словарь
        	// Если такой ключ есть -- копируем значения из списка
    		if (!to.containsKey(obj)) {
    			to.put(obj, from.get(obj));
    		}
    		else {
    			
    			for (CollidedObject listobj : from.get(obj)) {
    				
    				if (!to.get(obj).contains(listobj)) {
    					to.get(obj).add(listobj);
    				}
    			}
    		}
    	}
    }
    
    /**
     * Просеять словарь столкновений и удалить дублирующиеся ассоциации
     * @param st Словарь столкновений
     */
    private HashMap<CollidedObject, ArrayList<CollidedObject>> 
    	removeCouplingFromStorage(HashMap<CollidedObject, ArrayList<CollidedObject>> st) {
    	
    	HashMap<CollidedObject, ArrayList<CollidedObject>> newst = new HashMap<>();
    	
    	for (CollidedObject key : st.keySet()) {	
    		for (CollidedObject val : st.get(key)) {
    			
    			// Если в словарь уже не добавлена "обратная" ассоциация
    			if (!newst.containsKey(val) || !newst.get(val).contains(key)) {
    				
    				if (!newst.containsKey(key)) {
    					newst.put(key, new ArrayList<CollidedObject>());
    				}
    				newst.get(key).add(val);
    			}
    		}
    	}
    	
    	return newst;
    }

    private class ObjectGenericListener implements GenericEventListener{

        @Override
        public void destroyed() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
