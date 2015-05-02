package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D.Float;

import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.engine.input.AWTInput;

import model.Direction;
import model.GameModel;
import model.Player;
import model.Speed2D;

/**
 * Слушает ввод и управляет игрой.
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameController {

    private BaseInput _input;
    private GameModel _model;

    /**
     * Создаёт контроллер ввода
     *
     * @param player Модель игрока, которой будет управлять контроллер.
     * @param input Менеджер ввода.
     */
    public GameController(GameModel model, BaseInput input) {
        _model = model;
        _input = input;
    }

    /**
     * Проверяет состояние ввода и управляет моделью игрока.
     */
    public void update(long l) {

        // Управление мышью.
        if (_input.getMouseDX() != 0) {
            _model.proccessPlayerAction(new Speed2D(_input.getMouseDX()/l, 0));
        }

        if (_input.isMousePressed(MouseEvent.BUTTON1)) {
            _model.startGame();
        }

        // Управление с клавиатуры.
        if (_input.isKeyDown(KeyEvent.VK_LEFT)) {
            _model.proccessPlayerAction(Direction.west());
        } 

        else if (_input.isKeyDown(KeyEvent.VK_RIGHT)) {
            _model.proccessPlayerAction(Direction.east());
        } else {
            _model.proccessPlayerAction();
        }

        if (_input.isKeyPressed(KeyEvent.VK_SPACE)) {
            _model.startGame();
        }
    }

}
