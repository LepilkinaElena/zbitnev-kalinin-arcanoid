package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameField;
import model.IngameObject;
import model.Speed2D;
import model.collision.BehaviourDestroy;

/**
 * Модель разрушаемого кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class BreakableBrick extends Brick implements Cloneable{

	public BreakableBrick(GameField field) {
		
	    this(field, new Point2D.Float(0, 0), new Dimension(0, 0));
	}
	

	public BreakableBrick(GameField field, Float pos, Dimension dim, Speed2D speed) {
        
	    super(field, pos, dim, speed);
    }


    public BreakableBrick(GameField field, Float pos, Dimension dim) {
        
        this(field, pos, dim, new Speed2D(0, 0));
    }


    /**
	 * Разрушает кирпич.
	 */
	@Override
	public void destroy() {
	    super.destroy();
	}
	
	@Override
	public Object clone() {
            BreakableBrick clone = null;
            try {
		clone = (BreakableBrick) super.clone();
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(IngameObject.class.getName()).log(Level.SEVERE, null, ex);
            }
            return clone;
	}
}
