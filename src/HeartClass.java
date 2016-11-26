/*
 *  Name: HeartClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The class for a suit of Heart
 */
import java.awt.*;

class HeartClass extends SuitClass
{
    /*
     * HeartClass -> initializes basic components of Heart
     */
    public HeartClass ()
    {
		super();
		setColor( Color.RED );
    }

    public HeartClass (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor, boolean isFilled, int rotation )
    {
    	super( iNewWidth, iNewHeight, iNewCentreX, iNewCentreY, cNewColor, isFilled, rotation );
    }


    /*
     * draw -> inherits from ShapeClass
     */
    public void draw (Graphics g)
    {
		int iPointsX[] = new int [5];
		int iPointsY[] = new int [5];
	
		iPointsX [0] = getX() -  getWidth() /  2;
		iPointsY [0] = getY();
		iPointsX [1] = getX() + getWidth() /  2;
		iPointsY [1] = getY();
		iPointsX [2] = getX();
		iPointsY [2] = getY() + getHeight() /  2;
		iPointsX [3] = getX() -  getWidth() /  2;
		iPointsY [3] = getY() -  getHeight() /  2;
		iPointsX [4] = getX();
		iPointsY [4] = getY() -  getHeight() /  2;
	
		g.setColor ( getColor() );
	
		g.fillArc (iPointsX [3], iPointsY [3], getWidth() / 2, getHeight(), 0, 180);
		g.fillArc (iPointsX [4], iPointsY [4], getWidth() / 2, getHeight(), 0, 180);
		g.fillPolygon (iPointsX, iPointsY, 3);

    }
}
