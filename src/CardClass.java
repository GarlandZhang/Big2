/*
 *  Name: CardClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The class for a playing card
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CardClass extends ShapeClass
{
    //has a rectangular body -> only used in draw
    //has a value -> sets and gets
    private int iValue;
    
    //has a suit -> sets and gets
    private SuitClass iSuit;
    
    //has a isToggle -> sets and gets
    private boolean isToggle;
    
    private boolean isSelected;
    
    private BufferedImage cardPic,
    					  cardBack;
    
    public CardClass()
    {
		iValue = 0;
		iSuit = null;
		isToggle = false;
		isSelected = false;
		cardPic = null;
		try {
			cardBack = ImageIO.read( getClass().getResource( "ImagesFiles/cards/cardBack.jpg" ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public CardClass( int iValue, int iSuit )
    {
    	this();
    	setValue( iValue );
    	setSuit( iSuit );
    	this.iSuit.setHeight( getHeight() / 2 );
    	try {
			cardPic = ImageIO.read( getClass().getResource( "/ImagesFiles/cards/" + getValueAsString().toLowerCase() + getSuitAsString().toLowerCase() + ".gif" ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public CardClass( int uHeight, int uX, int uY,
		      Color cColor, int iValue, int iSuit ) 
    {
		this();
		setSuit( iSuit );
		setHeight( uHeight );
		setCentre( uX, uY );
		setColor( cColor );
		setValue( iValue );
    	try {
			cardPic = ImageIO.read( getClass().getResource( "/ImagesFiles/cards/" + getValueAsString().toLowerCase() + getSuitAsString().toLowerCase() + ".gif" ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }                   
    
    
    //setHeight -> overrides setHeight in Skyshape to make card proportional
    //@param iNewHeight -> height input
    public void setHeight( int iNewHeight )
    {
		super.setHeight( iNewHeight );
		super.setWidth( 4 * iNewHeight / 5 );
		
		iSuit.setHeight( iNewHeight / 2 );
    }
    
    //setWidth -> overrides setWidth in Skyshape to make card proportional
    //@param iNewWidth -> width input
    public void setWidth( int iNewWidth )
    {
		super.setWidth( iNewWidth );
		super.setHeight( 5 * iNewWidth / 4 );
		
		iSuit.setWidth( iNewWidth / 2 );
    }
    
    //setCentre -> overrides setCentre in Skyshape to make suit proportional to card
    public void setCentre( int uX, int uY )
    {
		super.setCentre( uX, uY );
		
		iSuit.setCentre( uX, uY );
    }
    
    //setValue -> sets value of card
    //@param iValue -> value input
    public void setValue( int iValue )
    {
		this.iValue = iValue;
    }
    
    //getValue -> gets value of card
    //@return int -> value output
    public int getValue()
    {
    	return iValue;
    }
    
    //getValueAsString -> gets value of card as a string
    //@return String -> value output
    public String getValueAsString()
    {
    	if( iValue == 1 )
    	{
    		return "A";
    	}
    	else if( iValue == 11 )
    	{
    		return "J";
    	}
    	else if( iValue == 12 )
    	{
    		return "Q";
    	}
    	else if( iValue == 13 )
    	{
    		return "K";
    	}
    	else
    	{
    		return "" + iValue;
    	}
    }
    
    //setSuit -> sets suit of card
    //@param uSuit -> suit input
    public void setSuit( int uSuit )
    {
		if( uSuit == 1 )
		{
		    iSuit = new DiamondClass();
		}
		else if( uSuit == 2 )
		{
		    iSuit = new ClubClass();
		}
		else if( uSuit == 3 )
		{
		    iSuit = new HeartClass();
		} 
		else if( uSuit == 4 )
		{
		    iSuit = new SpadeClass();
		}
		else
		{
		    System.out.println( "INVALID SUIT INPUT!" );
		}
    }
    
    //getSuit -> gets suit of card
    //@return SuitClass -> suit output
    public SuitClass getSuit()
    {
    	return iSuit;
    }
    
    //getSuitAsInt -> gets suit of card as int
    //@return int -> suit output
    public int getSuitAsInt()
    {
    	if( iSuit instanceof DiamondClass )
        {
            return 1;
        }
        else if( iSuit instanceof ClubClass )
        {
            return 2;
        }
        else if( iSuit instanceof HeartClass )
        {
            return 3;
        }
        else if( iSuit instanceof SpadeClass )
        {
            return 4;
        }
    	
    	return -1;
    }
    
    //getSuitAsString -> gets suit of card as String
    //@return String -> suit output
    public String getSuitAsString()
    {
    	if( getSuitAsInt() == 1 )
    	{
    		return "D";
    	}
    	else if( getSuitAsInt() == 2 )
    	{
    		return "C";
    	}
    	else if( getSuitAsInt() == 3 )
    	{
    		return "H";
    	}
    	else if( getSuitAsInt() == 4 )
    	{
    		return "S";
    	}
    	
    	return "";
    }
    
    //toggle -> toggle card to show back or front
    public void toggle()
    {
    	isToggle = !isToggle;
    }
    
    //getToggle -> gets toggle position
    public boolean getToggle()
    {
    	return isToggle;
    }
    
    //setSelected -> sets isSelected
    //@param isSelected -> input boolean
    public void setSelected( boolean isSelected )
    {
    	this.isSelected = isSelected;
    }
    
    //isSelected -> gets isSelected
    //@return boolean -> if isSelected is true, return true
    public boolean isSelected()
    {
    	return isSelected;
    }
    
    //draw -> draws card
    //@param g -> console
    public void draw( Graphics g )
    {    
		if( !isToggle )
		{
			/*
		    //box
		    g.setColor( Color.WHITE );
		    g.fillRect( getX() -  getWidth() /  2, getY() -  getHeight() /  2, getWidth(), getHeight() );
		    
		    g.setColor( Color.BLACK );
		    g.drawRect( getX() -  getWidth() /  2, getY() -  getHeight() /  2, getWidth(), getHeight() );
	    
		    g.setColor( getColor() );
		    //suit
		    iSuit.draw( g );
			
		    Font f1 = new Font( "SanSerif", Font.PLAIN, getHeight() /  5 );
		    g.setFont( f1 );
		    g.drawString( getValueAsString(), getX() -  getWidth() /  2, getY() -  getHeight() /  3 );
			 */
			
			g.drawImage( cardPic, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight(), null );
		}
		else
		{
			g.drawImage( cardBack, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight(), null );
		
		    g.setColor( Color.BLACK );
		    
		    g.drawRect( getX() -  getWidth() /  2, getY() -  getHeight() /  2, getWidth(), getHeight() );
			
		}
    }

	//isHigher -> checks which card is higher
	//@param a -> first card
	//@param b -> second card
	//@return boolean -> return value for whether a is higher than b
	public boolean isHigher( CardClass b )
	{
		//return true if value is higher. . . if equal, return higher suit
		if( ( getValue() + 10 ) % 13 > ( b.getValue() + 10 ) % 13 )
		{
			return true;
		}
		else if( ( getValue() + 10 ) % 13 < ( b.getValue() + 10 ) % 13 )
		{
			return false;
		}
		
		//when values are same
		
		return getSuitAsInt() > b.getSuitAsInt();
	}
    
    //isEqual -> checks if two cards have the same value and suit
    //@param -> card to check
    //@return boolean -> if card has same value and suit, return true
    public boolean isEqual( CardClass card )
    {
    	return iValue == card.getValue() && getSuitAsInt() == card.getSuitAsInt();
    }
    
    /*
     * printInfo -> prints the suit and value of a card
     */
    public void printInfo()
    {
    	System.out.print( getValue() + " " + getSuitAsInt() + " | " );
    }

} // CardClass class
