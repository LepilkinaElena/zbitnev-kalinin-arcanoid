/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

/**
 * Класс-наследник GTGE спрайта, потому что GTGE всем спрайтам id = 0
 * 
 * @author Елена
 */
public class UniqSprite extends Sprite {
    /** Следующий идентификатор */
    private static int _nextid = 0;
    /** Идентификатор спрайта */
    private int _id;
    
    /**
     * Создать спрайт
     * 
     * @param image изображение
     */
    public UniqSprite(BufferedImage image) {
        
        super(image);
        _id = _nextid;
        _nextid++;
    }
    
    /**
     * Получить идентификатор
     * 
     * @return идентификатор
     */
    public int getId() {
        
        return _id;
    }
    
    /**
     * Кллонировать спрайт
     * 
     * @return спрайт
     */
    public UniqSprite clone() {
        
        UniqSprite sprite = new UniqSprite(getImage());
        sprite._id = _id;
        _nextid--;
        return sprite;
    }
}
