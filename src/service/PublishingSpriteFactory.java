/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;
import model.ball.BasicBall;
import model.brick.BreakableBrick;
import model.brick.UnbreakableBrick;
import model.paddle.BasicPaddle;
import view.GameFieldView;
import view.IngameObjectView;
import view.PublishingSprite;

/**
 *
 * @author Елена
 */
public class PublishingSpriteFactory {
    
    GameFieldView _field;
    
    protected BufferedImage _basicBallImage = null;
    protected BufferedImage _breakableBrickImage = null;
    protected BufferedImage _unbreakableBrickImage = null;
    protected BufferedImage _basicPaddleImage = null;

    public PublishingSpriteFactory(GameFieldView field) {
        _field = field;
    }
    
    public void setBasicBallImage(BufferedImage image) {
        _basicBallImage = image;
    }
    
    public  void setBreakableBrickImage (BufferedImage image) {
        _breakableBrickImage = image;
    }
    
    public void setUnbreakableBrickImage(BufferedImage image) {
        _unbreakableBrickImage = image;
    }
    
    public void setBasicPaddleImage(BufferedImage image) {
        _basicPaddleImage = image;
    }
    
    /**
     * Возвращает true, если фабрика настроена и может порождать объекты.
     * @return Валидность фабрики.
     */
    public boolean is_valid() {
        
        boolean valid = true;
        
        valid &= _basicBallImage != null;
        valid &= _breakableBrickImage != null;
        valid &= _unbreakableBrickImage != null;
        valid &= _basicPaddleImage != null;
        
        return valid;
    }
    
 
    private PublishingSprite newPublishingSprite(BufferedImage image) {
        
        PublishingSprite publSprite = null;
        if (this.is_valid()) {
            Sprite sprite = new Sprite(image);
             publSprite = new PublishingSprite(sprite);
            _field.addToSpriteGroup(sprite);
        }
        return publSprite;
        
    }
    
    /**
     * Создает спрайт для простого мяча.
     * @return спрайт мяча.
     */
    public PublishingSprite newBasicBallPublishigSprite() {
        
        return newPublishingSprite(_basicBallImage);
    }
    
    /**
     * Создает спрайт для разрушаемого кирпича.
     * @return спрайт кирпича.
     */
    public PublishingSprite newBreakableBrickPublishingSprite() {

        return newPublishingSprite(_breakableBrickImage);
    }
    
    /**
     * Создает спрайт для неразрушаемого кирпича.
     * @return спрайт неразрушаемого кирпича.
     */
    public PublishingSprite newUnbreakableBrickPublishingSprite() {
        
        return newPublishingSprite(_unbreakableBrickImage);
    }
    
    /**
     * Создает спрайт для простой ракетки.
     * @return спрайт простой ракетки.
     */
    public PublishingSprite newBasicPaddlePublishingSprite() {
        
        return newPublishingSprite(_basicPaddleImage);
    }
    
    
}
