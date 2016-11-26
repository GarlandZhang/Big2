/*
 *  Name: DeckClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The class for a playing deck
 */

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;

public class DeckClass extends ShapeClass
{
	//where deck is stored
    private Vector<CardClass> deck;

    private boolean isAscending; //checks if deck is in ascending order
    
    /*
     * DeckClass -> initializes basic components of a deck 
     */
    public DeckClass ()
    {
    	super();
    	deck = new Vector<CardClass>(0, 1);
    	isAscending = true;
    }
    
    public DeckClass( Vector<CardClass> deck )
    {
    	this();
    	setDeck( deck );
    }
    
    /*
     * setHeight -> overrides setHeight in ShapeClass
     */
    public void setHeight( int uHeight )
    {
		super.setHeight( uHeight );
		
		super.setWidth( 4 * uHeight / 5 );
		
		for( int i = 0; i < getSize(); i++ )
		{
		    dealCard( i ).setHeight( uHeight );
		}
    }
    
    /*
     * setWidth -> overrides setWidth in ShapeClass
     */
    public void setWidth( int uWidth )
    {
		super.setWidth( uWidth );
		
		super.setHeight( 5 * uWidth / 4 );
		
		for( int i = 0; i < getSize(); i++ )
		{
		    dealCard( i ).setWidth( uWidth );
		}
	
    }
    
    /*
     * setCentre -> overrides setCentre in ShapeClass
     */
    public void setCentre( int x, int y )
    {
    	super.setCentre( x, y );
    	
    	for( int i = 0; i < getSize(); i = i + 1 )
    	{
    		if( !dealCard( i ).isSelected() )
    		{
    			dealCard( i ).setCentre( ( x + ( 2 * i - getSize() ) * getWidth() / 3 ), y );
    		}
    		else
    		{
    			dealCard( i ).setCentre( ( x + ( 2 * i - getSize() ) * getWidth() / 3 ), dealCard( i ).getY() );
    		}
    	}
    }
    
    /*
     * overrides setRotation in ShapeClass; used in drawing location of deck
     */
    public void setRotation( int rotation )
    {
    	super.setRotation( rotation );
    	for( int i = 0; i < getSize(); i = i + 1 )
    	{
   			dealCard( i ).setRotation( rotation );
    	}
    }
    
    /*
     * setSelected -> sets the selected state of cards in the deck
     * @param state -> the current state of a card selected
     */
    public void setSelected( boolean state )
    {
    	for( int i = 0; i < getSize(); i = i + 1 )
    	{
    		dealCard( i ).setSelected( state );
    	}
    }
    
    /*
     * setAll -> sets all parameters of the deck
     */
    public void setAll()
    {
    	setSelected( false );
    	setHeight( getHeight() );
    	setWidth( getWidth() );
    	setCentre( getX(), getY() );
    	setRotation( getRotation() );
    }
    
    /*
     * setDeck -> sets deck to the given deck
     * @param d -> user input deck
     */
    public void setDeck( Vector<CardClass> d )
    {
    	removeAll();
    	for( int i = 0; i < d.size(); i = i + 1 )
    	{
    		addCard( d.get( i ) );
    	}
    }
    
    /*
     * addCard -> adds card to the deck
     * @param cardToAdd -> the card to add
     * @param Pos -> the position to put card in
     */
    public void addCard (CardClass cardToAdd, int Pos)
    {
		if (Pos == 0 || getDeck().size () == 0)
		{
		    getDeck().addElement (cardToAdd);
		}
		else if (Pos > getDeck().size ())
		{
		    getDeck().insertElementAt (cardToAdd, getDeck().size ());
		}
		else
		{
		    getDeck().insertElementAt (cardToAdd, Pos);
		}
    }
    
    /*
     * addCard -> adds card to deck
     * @param cardToAdd -> the card to add
     */
    public void addCard( CardClass cardToAdd )
    {
    	getDeck().add( cardToAdd );
    }
    
    /*
     * removeCard -> removes card from deck
     * @param card -> the card to remove
     */
    public void removeCard( CardClass card )
    {
    	getDeck().remove( card );
    }
    
    /*
     * removeCard -> removes card from deck
     * @param Pos -> the position of the card to remove
     */
    public void removeCard( int Pos )
    {
    	getDeck().remove( Pos );
    }
    
    /*
     * removeDeck -> removes subdeck from deck
     * @param d -> the deck to remove
     */
    public void removeDeck( DeckClass d )
    {
    	for( int i = 0; i < d.getSize(); i = i + 1 )
    	{
    		getDeck().remove( d.dealCard( i ) );
    	}
    }
    
    /*
     * removeAll -> removes all cards from deck
     */
    public void removeAll()
    {
    	getDeck().removeAllElements();    	
    }

    /*
     * dealCard -> deals the card in deck
     * @param Pos -> the position to remove
     * @return CardClass -> the card to deal
     */
    public CardClass dealCard (int Pos)
    {
    	return getDeck().get( Pos );
    }

    /*
     * getTopCard -> gets the top card in the deck
     * @return CardClass -> the top card to deal
     */
    public CardClass getTopCard()
    {
    	return dealCard( 0 );
    }
    
    /*
     * getSize -> the size of the deck
     * @return int -> deck size
     */
    public int getSize()
    {
    	return getDeck().size();
    }
    
    /*
     * isEmpty -> checks if deck is empty
     * @return boolean -> is empty deck
     */
    public boolean isEmpty()
    {
    	return getSize() == 0;
    }

    /*
     * shuffle -> shuffles card or rearranges card positions
     */
    public void shuffle ()
    {
		CardClass temp = null;

		int newInd = 0;
		
		for( int i = 0; i < getSize(); i = i + 1 )
		{        
		    //assign values to card pointer and new index
		    temp = (CardClass) dealCard( i );
		    newInd = (int)( Math.random() * getSize() );
		       
		    //set current deck element to element found in new index
		    getDeck().set( i, dealCard( newInd ) );
		    
		    //set new index element to current deck element using temp
		    getDeck().set( newInd, temp );
		}
    }
    
    //getDeck -> returns deck
    //@return Vector<CardClass> -> decj vector
    public Vector<CardClass> getDeck()
    {
    	return deck;
    }
    
    //getDeck -> returns deck range
    //@param low -> low range for deck
    //@param high -> high range for deck
    //@return Vector<CardClass> -> subdeck
    public Vector<CardClass> getDeck( int low, int high )
    {
    	return new Vector<CardClass>( getDeck().subList( low, high ) );
    }
    
    /*
     * overrides draw in ShapeClass
     */
    public void draw( Graphics g )
    {
    	Graphics2D g2 = (Graphics2D)g;
        AffineTransform old = g2.getTransform();
        
    	g2.rotate( Math.toRadians( getRotation() ), getX(), getY() );

        
    	if( getSize() != 0 )
		{
			for( int i = 0; i < getSize(); i = i + 1 )
			{
				dealCard( i ).draw( g2 );
			}
		}
		else
		{
			g2.setColor( Color.BLACK );
			g2.drawRect( getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight() );
			g2.setColor( getColor() );
		}

        g2.setTransform(old);

    }
    
    /*
     * setArrangeOrder -> sets order of deck arrangement
     * @param isAscending -> whether deck should be arranged in ascending or descending
     */
    public void setArrangeOrder( boolean isAscending )
    {
    	this.isAscending = isAscending;
    }
    
    /*
     * isAscending -> checks the arrangement of deck
     * @return boolean -> if deck is ascending
     */
    public boolean isAscending()
    {
    	return isAscending;
    }
    
    //arrange -> arranges deck into descending order
    //@param isAscending -> sorts deck in ascending order if true
    public void arrange()
    {
    	CardClass card,
    			  card2,
    			  temp;
    	int newInd;
    	
    	if( isAscending )
    	{	
	    	for( int i = 0; i < getSize() - 1; i = i + 1 )
	    	{
	    		newInd = i;
	    		card = dealCard( i );
	    		for( int j = i + 1; j < getSize(); j = j + 1 )
	    		{
	    			card2 = dealCard( j );
	    			if( card.isHigher( card2 ) || ( card2.getValue() == card.getValue() && 
	   					card2.getSuitAsInt()
	   					< card.getSuitAsInt() ) )
	    			{
	    				card = card2;
	    				newInd = j;
	    			}
	    				
	    		}
	    		if( i != newInd )
	    		{
	    			temp = dealCard( i );
	    			
				    //set current deck element to element found in new index
				    getDeck().set( i, dealCard( newInd ) );
				    
				    //set new index element to current deck element using temp
				    getDeck().set( newInd, temp );
	    		}
	    	}
    	}
    	else
    	{
    		for( int i = 0; i < getSize() - 1; i = i + 1 )
	    	{
	    		newInd = i;
	    		card = dealCard( i );
	    		for( int j = i + 1; j < getSize(); j = j + 1 )
	    		{
	    			card2 = dealCard( j );
	    			if( card2.isHigher( card ) || ( card2.getValue() == card.getValue() && 
	   					card2.getSuitAsInt()
	   					> card.getSuitAsInt() ) )
	    			{
	    				card = card2;
	    				newInd = j;
	    			}
	    				
	    		}
	    		if( i != newInd )
	    		{
	    			temp = dealCard( i );
	    			
				    //set current deck element to element found in new index
				    getDeck().set( i, dealCard( newInd ) );
				    
				    //set new index element to current deck element using temp
				    getDeck().set( newInd, temp );
	    		}
	    	}
    	}
    	
    	setArrangeOrder( !isAscending() );
    }
    
    /*
     * sort -> sorts deck in ascending order( not in Big 2 Standards )
     */
    public void sort()
    {
    	CardClass card,
		  card2,
		  temp;
    	int newInd;
    	
		for( int i = 0; i < getSize() - 1; i = i + 1 )
    	{
    		newInd = i;
    		card = dealCard( i );
    		for( int j = i + 1; j < getSize(); j = j + 1 )
    		{
    			card2 = dealCard( j );
    			if( card2.getValue() < card.getValue() || ( card2.getValue() == card.getValue() && 
   					card2.getSuitAsInt()
   					< card.getSuitAsInt() ) )
    			{
    				card = card2;
    				newInd = j;
    			}
    				
    		}
    		if( i != newInd )
    		{
    			temp = dealCard( i );
    			
			    //set current deck element to element found in new index
			    getDeck().set( i, dealCard( newInd ) );
			    
			    //set new index element to current deck element using temp
			    getDeck().set( newInd, temp );
    		}
    	}
	}
	
    /*
     * hasCard -> checks if there is a certain card in deck
     * @param card -> the card to check for
     * @return boolean -> if card is in deck
     */
	public boolean hasCard( CardClass card )
	{
		for( int i = 0; i < getSize(); i = i + 1 )
		{
			if( dealCard( i ).isEqual( card ) )
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * printOutDeck -> prints out deck
	 */
	public void printOutDeck()
	{
		for( CardClass card : deck )
		{
			card.printInfo();
		}
		System.out.println();
	}

    /*
     * isPointInside -> overrides isPointInside in ShapeClass
     */
    public boolean isPointInside( int x, int y )
    {
    	for( int i = 0; i < getSize(); i = i + 1 )
    	{
    		if( dealCard( i ).isPointInside( x, y ) && x <= dealCard( i ).getX() + 2 * getWidth() / 3 )
    		{
    			return true;
    		}
    	}
    	
    	return dealCard( getSize() - 1 ).isPointInside( x, y );
    }
    
    /*
     * toggle -> toggles all cards in deck
     */
	public void toggle()
	{
		for( int i = 0; i < getSize(); i = i + 1 )
		{
			dealCard( i ).toggle();
		}
	}
} // DeckClass class
