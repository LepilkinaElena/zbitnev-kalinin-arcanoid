package controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.golden.gamedev.engine.BaseInput;

import model.GameModel;
import model.Speed2D;

/**
 * Слушает ввод и управляет игрой.
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class GameController {
    
    /** Ввод действий пользователя с помощью клавиатуры и мыши*/
    private BaseInput _baseInput;
    /** Модель игры */
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
           _gameModel.getPlayer().processAction(new Speed2D(3*_baseInput.getMouseDX()/l, 0));       
        }

        // Управление с клавиатуры.
        else if (_baseInput.isKeyDown(KeyEvent.VK_LEFT)) {
            _gameModel.getPlayer().processAction(new Speed2D(-0.4, 0));
        } else if (_baseInput.isKeyDown(KeyEvent.VK_RIGHT)) {
            _gameModel.getPlayer().processAction(new Speed2D(0.4, 0));
        } else {
            _gameModel.getPlayer().processAction(new Speed2D());
        }
        // Запуск игры.
        if (_baseInput.isMousePressed(MouseEvent.BUTTON1)) {
            _gameModel.startGame();
        }
        if (_baseInput.isKeyPressed(KeyEvent.VK_SPACE)) {
            _gameModel.startGame();
        }
    }

}
