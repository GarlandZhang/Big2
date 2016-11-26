/*
 *  Name: DiamondClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The class for creating a suit diamond
 */

import java.awt.*;

class DiamondClass extends SuitClass
{
    // global variables for this class
    // encapsulated data
	
	/*
	 * DiamondClass -> initializes basic components 
	 */
    public DiamondClass()
    {
		super();
		setColor( Color.RED );
    }

    public DiamondClass (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor, boolean isFilled, int rotation )
    {
    	super( iNewWidth, iNewHeight, iNewCentreX, iNewCentreY, cNewColor, isFilled, rotation );
    }
    
    /*
     * draw -> inherits from ShapeClass
     */
    public void draw ( Graphics g )
    {
		// declare two arrays for X & Y coordinates of Diamond
		int iPointsX[] = new int [4];
		int iPointsY[] = new int [4];
	
		// calculate points on diamond & store in the arrays
		iPointsX [0] = getX() -  getWidth() /  2;
		iPointsY [0] = getY();
		iPointsX [1] = getX();
		iPointsY [1] = getY() -  getHeight() /  2;
		iPointsX [2] = getX() + getWidth() /  2;
		iPointsY [2] = getY();
		iPointsX [3] = getX();
		iPointsY [3] = getY() + getHeight() /  2;
	
		// draw the diamond using methods available from the Console object (g)
		g.setColor ( getColor() );
		
		if( getIsFilled() )
		{
			g.fillPolygon (iPointsX, iPointsY, 4);
		}
		else
		{
			g.drawPolygon( iPointsX, iPointsY, 4 );
		}
		
    }

}
