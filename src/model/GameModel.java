package model;

import java.util.ArrayList;
import model.interaction.CollisionEvent;

import model.interaction.CollisionListener;

/**
 * Модель игры.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameModel {

    protected GameField _field = null;
    protected Player _player = new Player();
    
	

	/**
	 * Назначить игровое поле
	 * @param field
	 */
	public void setField(GameField field) {
		
		if (field == null) {
		    throw new NullPointerException();
		}
		_field = field;
	}
	
	/**
	 * Получить игровое поле
	 * @return Текущее поле
	 */
	public GameField getField() {
		
		return _field;
	}
	
	/**
	 * Установить модели нового игрока
	 * @param player
	 */
	public void setPlayer(Player player) {
            _player = player;
	}
	
	/**
	 * Полуить игроков модели
	 * @return
	 */
	public Player getPlayer() {
		
		return (Player) _player.clone();
	}
	
	/**
	 * Обновляет модель. В реализации данного класса ничего не делает.
	 * @param arg Аргумент.
	 */
	public void update(Object arg) {
	    
	}
}
