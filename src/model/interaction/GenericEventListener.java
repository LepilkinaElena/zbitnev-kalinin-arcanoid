package model.interaction;

import model.collisionProcessing.IngameObject;

/**
 * Интерфейс слушателя событий создания, удаления.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public interface GenericEventListener {
	
	/**
	 * Объект был уничтожен.
	 */
	void destroyed(GenericEvent event);
}
