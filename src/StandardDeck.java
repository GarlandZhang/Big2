/*
 *  Name: CardClass.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: The standardize deck
 */
public class StandardDeck extends DeckClass
{
	/*
	 * StandardDeck -> initializes a standard playing deck
	 */
	public StandardDeck()
	{
		super();
		
		CardClass g = null;
		
	    // generate 52 cards (2 nested loops) and add them to the deck

	    //value
	    for (int i = 0 ; i < 13 ; i = i + 1)
	    {
			//suit
			for (int j = 0 ; j < 4; j = j + 1 )
			{
			    g = new CardClass( getHeight(), getX(), getY(), getColor() , i + 1, j + 1 );
			    addCard( g );
			}
	    }
	}

}
