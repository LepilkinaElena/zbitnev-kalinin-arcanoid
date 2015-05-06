package model;

import model.paddle.Paddle;

/**
 * Модель игрока, управляющего ракеткой.
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Player {

    /** Ракетка */
    private Paddle _paddle;

    /**
     * Инициализировать игрока
     *
     * @param paddle Подконтрольная игроку ракетка
     */
    public Player(Paddle paddle) {
        
        _paddle = paddle;
    }

    public Player() {

    }

    /**
     * Клонировать игрока
     * @return клон игрока
     */
    public Player clone() {
        
        Player clonePlaer = new Player((Paddle) _paddle.clone());
        return clonePlaer;
    }

    /**
     * Установить ракетку для контроля игрока
     *
     * @param paddle Ракетка для добавления
     */
    public void setPaddle(Paddle paddle) {
        
        _paddle = paddle;
    }

    /**
     * Начать попытку
     */
    void startAttempt() {
        
        _paddle.pushBalls();
    }

    /**
     * Обработать действия игрока
     * 
     * @param speed2D скорость, которую игрок задал ракетке
     */
    void processAction(Speed2D speed2D) {
        
        _paddle.setSpeed(speed2D);
    }

    /**
     * Обработать действия игрока
     * 
     * @param direction Направление, которое игрок задал ракетке
     */
    void processAction(Direction direction) {
        
        if (direction.equals(Direction.west())) {
            _paddle.setSpeed(new Speed2D(-0.4, 0));
        } else {
            _paddle.setSpeed(new Speed2D(0.4, 0));
        }
    }
    
    /**
     * Обработать действия игрока
     */
    void processAction() {
        
        _paddle.setSpeed(new Speed2D());
    }
}
