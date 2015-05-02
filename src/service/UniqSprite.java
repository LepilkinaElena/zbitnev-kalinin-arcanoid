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
    private static int nextid = 0;
    private final int id;
    
    public UniqSprite(BufferedImage image) {
        super(image);
        this.id = nextid;
        nextid++;
    }
    
    public int getId() {
        return id;
    }
}
