package view;

import integrationGTGE.GameFieldView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameField;
import model.GameModel;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.background.ImageBackground;

import controller.GameController;
import service.IngameObjectFactory;

/**
 * Режим игры
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class ScreenGame extends GameObject {

    /** Модель игры */
    private GameModel _gameModel;
    /** Представление игрового поля */
    private GameFieldView _gameFieldView;
    /** Контроллер */
    private GameController _gameController;
    
    
    public ScreenGame(GameEngine arg0) {
        
        super(arg0);
    }

    @Override
    public void initResources() {

        // Инициализация уровня
        GameField field = new GameField(bsGraphics.getSize());

        // Инициализация представления уровня
        _gameFieldView = new GameFieldView(field);

        // Задать фон уровня.
        BufferedImage fieldBg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        //fieldBg.getGraphics().drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
        Graphics g = fieldBg.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        _gameFieldView.setBackground(new ImageBackground(fieldBg));

        // Фабрика представлений
        IngameObjectFactory factory = new IngameObjectFactory(_gameFieldView, field);

        _gameModel = new GameModel();
        _gameModel.setField(field);
        // Построение уровня
        // TODO: Загрузка уровня из файла (пока уровень захардкоден)
        _gameModel.initLevel(factory);

        // Контроллер и игрок.
        _gameController = new GameController(_gameModel, bsInput);

        // Инициализация закончена. Спрятать курсор мыши перед началом игры.
        hideCursor();
    }

    @Override
    public void render(Graphics2D arg0) {

        _gameFieldView.render(arg0);

        // TODO: Рендер кол-ва очков, другой инофрмации (сейчас игра на весь экран)
    }

    @Override
    public void update(long l) {
        
        // Апдейтим всё
        _gameFieldView.update(l);
        _gameController.update(l);
    }

}
