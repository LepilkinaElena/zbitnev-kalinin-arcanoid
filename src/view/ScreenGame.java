package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import model.GameField;
import model.GameModel;
import model.Player;
import model.Speed2D;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.background.ImageBackground;

import controller.GameController;
import model.ball.Ball;
import model.paddle.Paddle;
import service.IngameObjectFactory;

/**
 * Режим игры
 *
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class ScreenGame extends GameObject {

    GameModel _model;
    GameFieldView _fieldView;
    GameController _controller;

    public ScreenGame(GameEngine arg0) {
        super(arg0);
    }

    @Override
    public void initResources() {

        // Инициализация уровня
        GameField field = new GameField(this.bsGraphics.getSize());

        // Инициализация представления уровня
        _fieldView = new GameFieldView(field);

        // Задать фон уровня.
        BufferedImage fieldBg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //fieldBg.getGraphics().drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), null);
        Graphics g = fieldBg.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        _fieldView.setBackground(new ImageBackground(fieldBg));

        // Фабрика представлений
        IngameObjectFactory factory = new IngameObjectFactory(_fieldView, field);

        _model = new GameModel();
        _model.setField(field);

		// Построение уровня
        // TODO: Загрузка уровня из файла (пока уровень захардкоден)
        

        // Контроллер и игрок.
        
        //_controller = new GameController(player, bsInput);

        // ЭКСПЕРИМЕНТ
       // paddle.addBall(newball);

        // Тестирование столкновения множества шаров
        /*BasicBall ball01 = new BasicBall(field, new Point2D.Float((float) 213.3975, 250), 16, new Speed2D(0.043, -0.025));
         BasicBall ball02 = new BasicBall(field, new Point2D.Float(400, 200), 16, new Speed2D(-0.05, 0));
         ball01.addDefaultCollisionBehaviour(BehaviourRebound.getInstance());
         ball02.addDefaultCollisionBehaviour(BehaviourRebound.getInstance());
        
         IngameObjectView ball01_view = viewfact.newBasicBallView(ball01);
         IngameObjectView ball02_view = viewfact.newBasicBallView(ball02);
         _fieldView.addObjectView(ball01_view);
         _fieldView.addObjectView(ball02_view);*/
        // Инициализация закончена. Спрятать курсор мыши перед началом игры.
        this.hideCursor();
    }

    @Override
    public void render(Graphics2D arg0) {

        _fieldView.render(arg0);

        // TODO: Рендер кол-ва очков, другой инофрмации (сейчас игра на весь экран)
    }

    @Override
    public void update(long arg0) {

        // Апдейтим всё
        _fieldView.update(arg0);
        _controller.update();
    }

}
