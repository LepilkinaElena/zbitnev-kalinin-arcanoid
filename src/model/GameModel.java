package model;

import java.util.ArrayList;
import model.interaction.CollisionEvent;

import model.interaction.CollisionListener;

/**
 * Модель игры.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class GameModel implements CollisionListener {

    protected GameField _field = null;
    protected ArrayList<Player> _players = new ArrayList<>();
    
	

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
	 * Добавить модели нового игрока
	 * @param player
	 */
	public void addPlayer(Player player) {
		
		if (player == null) {
		    throw new NullPointerException();
		}
		_players.add(player);
	}
	
	/**
	 * Убрать игрока из модели
	 * @param player
	 */
	public void removePlayer(Player player) {
		
		_players.remove(player);
	}
	
	/**
	 * Полуить игроков модели
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		
		return (ArrayList<Player>) _players.clone();
	}
	
	/**
	 * Обновляет модель. В реализации данного класса ничего не делает.
	 * @param arg Аргумент.
	 */
	public void update(Object arg) {
	    
	}

    @Override
    public void collisionOccured(CollisionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
