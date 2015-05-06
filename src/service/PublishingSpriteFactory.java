/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.awt.image.BufferedImage;
import view.GameFieldView;

/**
 * Класс фабрики спрайтов
 * 
 * @author Елена
 */
public class PublishingSpriteFactory {

    /** Представление игрового поля */
    private GameFieldView _gameFieldView;

    /** Изображение мяча */
    private BufferedImage _basicBallImage = null;
    /** Изображение разрушаемого кирпича */
    private BufferedImage _breakableBrickImage = null;
    /** Изображение неразрушаемого кирпича */
    private BufferedImage _unbreakableBrickImage = null;
    /** Изображение ракетки */
    private BufferedImage _basicPaddleImage = null;

    /**
     * Создать фабрику
     * 
     * @param gameFieldView представление игрового поля
     */
    public PublishingSpriteFactory(GameFieldView gameFieldView) {
        
        _gameFieldView = gameFieldView;
    }

    /**
     * Установить изображение мяча
     * 
     * @param image изображение мяча
     */
    public void setBasicBallImage(BufferedImage image) {
        
        _basicBallImage = image;
    }

    /**
     * Установить изображение разрушаемого кирпича
     * 
     * @param image изображение разрушаемого кирпича
     */
    public void setBreakableBrickImage(BufferedImage image) {
        
        _breakableBrickImage = image;
    }

    /**
     * Установить изображение неразрушаемого кирпича
     * 
     * @param image изображение неразрушаемого кирпича
     */
    public void setUnbreakableBrickImage(BufferedImage image) {
        
        _unbreakableBrickImage = image;
    }

    /**
     * Установить изображение ракетки
     * 
     * @param image изображение ракетки
     */
    public void setBasicPaddleImage(BufferedImage image) {
        
        _basicPaddleImage = image;
    }

    /**
     * Возвращает true, если фабрика настроена и может порождать объекты.
     *
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

    /**
     * Создать новый спрайт
     * 
     * @param image изображение
     * @return спрайт
     */
    private PublishingSprite newPublishingSprite(BufferedImage image) {

        PublishingSprite publSprite = null;
        if (this.is_valid()) {
            UniqSprite sprite = new UniqSprite(image);
            publSprite = new PublishingSprite(sprite);
            _gameFieldView.addToSpriteGroup(sprite);
        }
        return publSprite;

    }

    /**
     * Создает спрайт для мяча.
     *
     * @return спрайт мяча.
     */
    public PublishingSprite newBasicBallPublishigSprite() {

        return newPublishingSprite(_basicBallImage);
    }

    /**
     * Создает спрайт для разрушаемого кирпича.
     *
     * @return спрайт кирпича.
     */
    public PublishingSprite newBreakableBrickPublishingSprite() {

        return newPublishingSprite(_breakableBrickImage);
    }

    /**
     * Создает спрайт для неразрушаемого кирпича.
     *
     * @return спрайт неразрушаемого кирпича.
     */
    public PublishingSprite newUnbreakableBrickPublishingSprite() {

        return newPublishingSprite(_unbreakableBrickImage);
    }

    /**
     * Создает спрайт для простой ракетки.
     *
     * @return спрайт простой ракетки.
     */
    public PublishingSprite newBasicPaddlePublishingSprite() {

        return newPublishingSprite(_basicPaddleImage);
    }

}
