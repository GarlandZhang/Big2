/*
 *  Name: ClubClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The class for a creating a club suit
 */
import java.awt.*;

class ClubClass extends SuitClass
{
	/*
	 * ClubClass -> initializes basic components of a club
	 */
    public ClubClass ()
    {
    	super();
    	setColor( Color.BLACK );
    }


    public ClubClass (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor, boolean isFilled, int rotation )
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
		iPointsY [2] = getY() -  getHeight() /  2;
		iPointsX [3] = getX() -  getWidth() /  2;
		iPointsY [3] = getY() -  getHeight() /  4;
		iPointsX [4] = getX();
		iPointsY [4] = getY() -  getHeight() /  4;
	
		int triPointsX[] = new int [3];
		int triPointsY[] = new int [3];
	
		triPointsX [0] = getX();
		triPointsY [0] = getY() -  getHeight() /  4;
		triPointsX [1] = getX() -  getWidth() /  8;
		triPointsY [1] = getY() + getHeight() /  2;
		triPointsX [2] = getX() + getWidth() /  8;
		triPointsY [2] = getY() + getHeight() /  2;
	
		g.setColor ( getColor() );
	
		g.fillOval (iPointsX [3], iPointsY [3], getWidth() /  2, getHeight() /  2);
		g.fillOval (iPointsX [4], iPointsY [4], getWidth() /  2, getHeight() /  2);
		g.fillOval (getX() -  getWidth() /  4, getY() -  4 * (getHeight() /  7), getWidth() /  2, getHeight() /  2);
		g.fillPolygon (triPointsX, triPointsY, 3);
    }
}
