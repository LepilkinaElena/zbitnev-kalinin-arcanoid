package model.interaction;



/**
 * Интерфейс слушателя событий о столкновениях игровых объектов.
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public interface CollisionListener {

	/**
	 * Произошла коллизия между игровыми объектами.
	 * @param event событие столкновения игровых объектов.
	 */
	void collisionOccured(CollisionEvent event);
}
