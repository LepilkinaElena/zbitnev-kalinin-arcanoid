package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.paddle.Paddle;

/**
 * Модель игрока, управляющего ракеткой.
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class Player {

    protected Paddle _paddle;

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
     * Заставляет подконтрольные ракетки задать скорость мячам.
     */
    public void pushBalls() {
        _paddle.pushBalls();
    }

    void startAttempt() {
        _paddle.pushBalls();
    }

    void processAction(Speed2D speed2D) {
        _paddle.setSpeed(speed2D);
    }

    void processAction(Direction direction) {
        long delta = Math.round(_paddle.getSize().width / 3.0 * 2.0);
        delta = direction.equals(Direction.west()) ? -delta : delta;

        _paddle.setPosition(new Point2D.Float((float) (_paddle.getPosition().getX() + delta), (float) _paddle.getPosition().getY()));
    }
}
