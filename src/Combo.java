/*
 *  Name: Combo.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The class that illustrate a set of cards that can be played
 */
import java.util.Vector;


public class Combo extends DeckClass
{	
	private int comboType; //type of combo in Big 2

	/*
	 * Combo -> initializes basic components of Combo
	 */
	public Combo()
	{
		super();
		comboType = 0;
	}
	
	public Combo( Vector<CardClass> deck )
	{
		super( deck );
	}
	
	/*
	 * setDeck -> overrides setDeck in DeckClass
	 */
	public void setDeck( Vector<CardClass> deck )
	{
		super.setDeck( deck );
    	arrange();
		setArrangeOrder( true );
		setComboType();
	}
	
	/*
	 * setComboType -> sets type of combo this is
	 */
	public void setComboType()
	{
		if( isSingle() )
		{
			comboType = 1;
		}
		else if( getSize() == 2 && isDouble( 0, 2 ) )
		{
			comboType = 2;
		}
		else if( getSize() == 3 && isTriple( 0, 3 ) )
		{
			comboType = 3;
		}
		else if( getSize() == 5 )
		{
			if( isStraightFlush() )
			{
				comboType = 8;
			}
			else if( isStraight() )
			{
				comboType = 4;
			}
			else if( isFlush() )
			{
				comboType = 5;
			}
			else if( isFullHouse() )
			{
				comboType = 6;
			}
			else if( isFourKind() )
			{
				comboType = 7;
			}
			else
			{
				comboType = 0;
			}
		}
		else
		{
			comboType = 0;
		}
	}
	
	/*
	 * getComboType -> gets type of combo
	 * @return int -> combo type in terms of hiearchy
	 */
	public int getComboType()
	{
		return comboType;
	}
	
	/*
	 * isStraightFlush -> checks if combo is straightFlush
	 * @return boolean -> if combo is straight flush
	 */
	private boolean isStraightFlush() {
		// TODO Auto-generated method stub
		return isFlush() && isStraight();
	}

	/*
	 * isFourKind -> checks if combo is four of a kind
	 * @return boolean -> if combo is four of a kind
	 */
	private boolean isFourKind() {
		// TODO Auto-generated method stub
		return ( isTriple( 0, 3 ) && isDouble( 2, 4 ) ) || ( isTriple( 1, 4 ) && isDouble( 3, 5 ) );
	}

	/*
	 * isFullHouse -> checks if combo is full house
	 * @return boolean -> if combo is full house
	 */
	private boolean isFullHouse() {
		// TODO Auto-generated method stub
		return ( isTriple( 0, 3 ) && isDouble( 3, 5 ) ) || ( isDouble( 0, 2 )
			   && isTriple( 2, 5 ) );
	}

	/*
	 * isFlush -> checks if combo is flush
	 * @return boolean -> if combo is flush
	 */
	private boolean isFlush() {
		// TODO Auto-generated method stub

		for( int i = 0; i < getSize() - 1; i = i + 1 )
		{
			if( dealCard( i ).getSuitAsInt() != dealCard( i + 1 ).getSuitAsInt() )
			{
				return false;
			}
		}
		
		return true;
	}

	/*
	 * isStraight -> checks if combo is straight
	 * @return boolean -> if combo is straight
	 */
	private boolean isStraight() {
		// TODO Auto-generated method stub
		DeckClass temp = new DeckClass( getDeck() );
		temp.sort();
		
		for( int i = 0; i < temp.getSize() - 1; i = i + 1 )
		{
			if( temp.dealCard( i ).getValue() + 1 != temp.dealCard( i + 1 ).getValue() )
			{
				for( int j = 0; j < getSize() - 1; j = j + 1 )
				{
					if( ( dealCard( j ).getValue() + 11 ) % 13 != ( dealCard( j ).getValue() + 10 ) % 13 )
					{
						return false;
					}
				}
				return true;
				
			}
		}

		return true;
	}

	/*
	 * isTriple -> checks if combo is triple
	 * @return boolean -> if combo is triple
	 */
	private boolean isTriple( int low, int high ) {
		// TODO Auto-generated method stub
		return isDouble( low, low + 2 ) && isDouble( high - 2, high );
	}

	/*
	 * isDouble -> checks if combo is double
	 * @return boolean -> if combo is double
	 */
	private boolean isDouble( int low, int high ) {
		// TODO Auto-generated method stub
		Vector<CardClass> d = getDeck( low, high );
		return d.get( 0 ).getValue() == ( d.get( 1 ) ).getValue();
	}

	/*
	 * isSingle -> checks if combo is single
	 * @return boolean -> if combo is single
	 */
	private boolean isSingle()
	{
		// TODO Auto-generated method stub
		return getSize() == 1;
	}
	
	/*
	 * isPlayable -> checks if combo can be played
	 * @param c2 -> the combo ( should be play deck ) to compare the combo being played
	 * @return boolean -> if combo is playable
	 */
	public boolean isPlayable( Combo c, boolean is3D )
	{
		setComboType();
		return isValid( c ) && ( ( is3D && hasCard( new CardClass( 3, 1 ) ) ) || ( !is3D && isHigher( c ) ) );
	}

	//isHigher -> checks which combo is higher
	//@param a -> first combo
	//@param b -> second combo
	//@return boolean -> return true if a is higher than b. Otherwise, return false.
	public boolean isHigher( Combo b )
	{
		//instance where a & b are both valid and same type
		if( b == null || b.getSize() == 0 || getComboType() > b.getComboType() )
		{
			return true;
		}
		else if( getComboType() < b.getComboType() )
		{
			return false;
		}
		else if( getSize() == 1 )
		{
			return dealCard( 0 ).isHigher( b.dealCard( 0 ) );
		}
		else if( getSize() == 2 )
		{
			return dealCard( 1 ).isHigher( b.dealCard( 1 ) );
		}
		else if( getSize() == 3 || getComboType() == 6 || getComboType() == 7 )
		{
			return dealCard( 2 ).isHigher( b.dealCard( 2 ) );
		}
		else if( getComboType() == 5 )
		{
			return dealCard( 4 ).isHigher( b.dealCard( 4 ) );
		}
		
		//instance where combo is 5 cards
		
		return dealCard( 4 ).isHigher( b.dealCard( 4 ) );
	}
	
	//isValid -> checks if combo is valid
	//@return boolean -> return value for whether c is a valid combo
	public boolean isValid( Combo c )
	{
		if( c != null && c.getComboType() == 3 )
		{
			return getComboType() == 3;
		}
		return getComboType() != 0;
	}

}
