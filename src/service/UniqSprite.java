/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.golden.gamedev.object.Sprite;
import java.awt.image.BufferedImage;

/**
 *
 * @author Елена
 */
public class UniqSprite extends Sprite {
    private static int _nextid = 0;
    private int _id;
    
    public UniqSprite(BufferedImage image) {
        super(image);
        _id = _nextid;
        _nextid++;
    }
    
    public int getId() {
        return _id;
    }
    
    public UniqSprite clone() {
        UniqSprite sprite = new UniqSprite(getImage());
        sprite._id = _id;
        _nextid--;
        return sprite;
    }
}
