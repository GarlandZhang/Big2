/*
 *  Name: ShapeClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The super class to illustrate the basic drawing of any shape
 */
import java.awt.*;

public abstract class ShapeClass
{
    // global variables for this class
    // encapsulated data
    private int iWidth;
    private int iHeight;
    private int iCentreX;
    private int iCentreY;
    private Color cColor;
    private boolean isFilled;
    private int iRotation;

    /*
     * ShapeClass -> initializes shape features
     */
    public ShapeClass()
    {
		iWidth = 50;
		iHeight = iWidth;
		iCentreX = 50;
		iCentreY = iCentreX;
		cColor = Color.RED;
		isFilled = true;
		iRotation = 0;
    }
    
    public ShapeClass (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor, boolean isFilled, int iRotation )
    {
    	super();
		iWidth = iNewWidth;
		iHeight = iNewHeight;
		iCentreX = iNewCentreX;
		iCentreY = iNewCentreY;
		cColor = cNewColor;
		this.isFilled = isFilled;
		this.iRotation = iRotation;
    }    
    
    // communicator methods
    public void setWidth (int iNewWidth)
    {
    	iWidth = iNewWidth;
    }

    public void setHeight (int iNewHeight)
    {
    	iHeight = iNewHeight;
    }


    public void setCentre (int iNewCentreX, int iNewCentreY)
    {
    	iCentreX = iNewCentreX;
		iCentreY = iNewCentreY;
    }


    public void setColor (Color cNewColor)
    {
    	cColor = cNewColor;
    }
    
    public void setIsFilled( boolean uState )
    {
    	isFilled = uState;
    }
    
    public boolean getIsFilled()
    {
    	return isFilled;
    }

    public int getWidth ()
    {
    	return iWidth;
    }


    public int getHeight ()
    {
    	return iHeight;
    }


    public int getX ()
    {
    	return iCentreX;
    }


    public int getY ()
    {
    	return iCentreY;
    }


    public Color getColor ()
    {
    	return cColor;
    }
    
    public void setRotation( int iRotation )
    {
    	this.iRotation = iRotation;
    }
    
    public int getRotation()
    {
    	return iRotation;
    }

    public abstract void draw( Graphics g );
    
    public void erase (Graphics g)
    {
		Color cOldColor = getColor ();
		setColor( Color.white );
		draw (g);
		g.setColor(cOldColor);
		setColor( cOldColor );
    }

    
    //isPointInside -> checks whether a point is inside the diamond
    //@param x -> x-coor of point
    //@param y -> y-coor of point
    //@return boolean -> if point is inside
    public boolean isPointInside( int x, int y )
    {
    	return x >= getX() - getWidth() / 2 && x <= getX() + getWidth() / 2
    		&& y >= getY() - getHeight() / 2 && y <= getY() + getHeight() / 2;
    }
}
