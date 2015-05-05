package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.golden.gamedev.engine.BaseInput;

import model.Direction;
import model.GameModel;
import model.Speed2D;

/**
 * Слушает ввод и управляет игрой.
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameController {

    private BaseInput _baseInput;
    private GameModel _gameModel;

    /**
     * Создаёт контроллер ввода
     *
     * @param gameModel Модель игры, которой будет управлять контроллер.
     * @param baseInput Менеджер ввода.
     */
    public GameController(GameModel gameModel, BaseInput baseInput) {
        _gameModel = gameModel;
        _baseInput = baseInput;
    }

    /**
     * Проверяет состояние ввода и управляет моделью игрока.
     */
    public void update(long l) {

        // Управление мышью.
        if (_baseInput.getMouseDX() != 0) {
           _gameModel.proccessPlayerAction(new Speed2D(3*_baseInput.getMouseDX()/l, 0));
        }

        // Управление с клавиатуры.
        else if (_baseInput.isKeyDown(KeyEvent.VK_LEFT)) {
            _gameModel.proccessPlayerAction(Direction.west());
        } else if (_baseInput.isKeyDown(KeyEvent.VK_RIGHT)) {
            _gameModel.proccessPlayerAction(Direction.east());
        } else {
            _gameModel.proccessPlayerAction();
        }
        
        if (_baseInput.isMousePressed(MouseEvent.BUTTON1)) {
            _gameModel.startGame();
        }
        if (_baseInput.isKeyPressed(KeyEvent.VK_SPACE)) {
            _gameModel.startGame();
        }
    }

}
