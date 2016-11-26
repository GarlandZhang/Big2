/*
 *  Name: SuitClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The abstract class for all playing card suits
 */
import java.awt.*;

public abstract class SuitClass extends ShapeClass
{
	/*
	 * SuitClass -> initializes shape parameters 
	 */
    public SuitClass()
    {
        super();
        setHeight( getHeight() );
    }
    
    public SuitClass(int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor, boolean isFilled, int rotation )
    {
        super( iNewWidth, iNewHeight, iNewCentreX, iNewCentreY, cNewColor, isFilled, rotation );
    }

    //overrides setHeight in ShapeClass so height and width parameters are proportional
    public void setHeight( int iNewHeight )
    {
        super.setHeight( iNewHeight );
        super.setWidth( 4 * iNewHeight / 5 );
    }
    
    //overides setWidth in ShapeClass so height and width parameters are proportional
    public void setWidth( int iNewWidth )
    {
        super.setWidth( iNewWidth );
        super.setHeight( 5 * iNewWidth / 4 );
    }

}
