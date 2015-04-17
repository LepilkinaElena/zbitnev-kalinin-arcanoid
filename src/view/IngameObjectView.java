package view;



import com.golden.gamedev.object.SpriteGroup;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import model.IngameObject;
import model.Speed2D;
import model.interaction.GenericEventListener;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;

/**
 * Представление отдельного игрового объекта
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class IngameObjectView {

    protected final IngameObject ingameObject;
    
	protected PublishingSprite _sprite = null;
	
	/**
	 * Создает представление объекта на основе его модели и спрайта.
	 * Этот метод автоматически согласует слушателей и связывает спрайт с объектом представления, которому он принадлежит.
	 * @param obj Модель игрового объекта.
	 * @param sprite Спрайт, которым он будет отображен.
	 */
	public IngameObjectView(IngameObject obj, PublishingSprite sprite) {
	    	    
	    this.ingameObject = obj;
	    this._sprite       = sprite;
	}
        
        public void addToSpriteGroup(SpriteGroup group) {
            _sprite.addToSpriteGroup(group);
        }
        
        public void removeFromSpriteGroup(SpriteGroup group) {
            _sprite.removeFromSpriteGroup(group);
        }
}
