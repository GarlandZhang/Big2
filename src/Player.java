/*
 *  Name: Player.java
 *  Date: 1/1/2016
 *  Created By: Zhang, Garland
 *  Description: Used to build a player in game of big 2
 */
public class Player
{

	//encapsulated data
	//has a deck(hand)
	private Hand hand;
	private Combo combo;
	//has a name(or player#)
	private String name;
	
	private boolean isTurn;
	private boolean isPassed;
	private boolean isWinner;
	
	/*
	 * Player -> initializes default parameters of a player
	 */
	public Player()
	{
		hand = new Hand();
		combo = new Combo();
		name = "DEFAULT_NAME";
		isTurn = false;
		isPassed = false;
	}
	
	public Player( String name )
	{
		this();
		setName( name );
	}
	
	//pass -> when a player skips his/her turn
	public void pass()
	{
		setPlayerTurn( false );
		setIsPassed( true );
	}
	
	//play -> when a player plays a combo
	public void play()
	{
		hand.removeDeck( combo );
		combo.removeAll();
		combo.setComboType();
		setIsPassed( false );
		setPlayerTurn( false );
		hand.setCentre( hand.getX(), hand.getY() );
	}
	
	//select -> allow player to select a card
	//@param card -> the card to select
	public void select( CardClass card )
	{
		if( !card.isSelected() )
		{
			combo.addCard( card );
			card.setSelected( true );
			card.setCentre( card.getX(), card.getY() - card.getHeight() );
		}
		else
		{
			combo.removeCard( card );
			card.setSelected( false );
			card.setCentre( card.getX(), getHand().getY() );
		}
	}
	
	//win -> sets the play to win
	public void win()
	{
		isWinner = true;
	}
	
	//setName -> sets name of Player
	//@param name -> the name to set
	public void setName( String name )
	{
		this.name = name;
	}
	
	//getName -> gets name of Player
	//@return String -> returns the name of Player
	public String getName()
	{
		return name;
	}
	
	//setIsPassed -> sets the action of the player to be "pass"
	//@param isPassed -> sets whether player passes 
	public void setIsPassed( boolean isPassed )
	{
		this.isPassed = isPassed;
	}
	
	//getIsPassed -> gets the action of the player if he/she "pass"
	//@return boolean -> checks if player passed
	public boolean getIsPassed()
	{
		return isPassed;
	}
	
	//getHand -> gets the hand of the player
	//@return Hand -> hand of player
	public Hand getHand()
	{
		return hand;
	}
	
	//getCombo -> gets the combo of the player
	//@return Combo -> gets the combo selected
	public Combo getCombo()
	{
		return combo;
	}

	
	//setPlayerTurn -> when player's turn to make a move
	//@param isTurn -> input value
	public void setPlayerTurn( boolean isTurn )
	{
		this.isTurn = isTurn;
	}
	
	//getPlayerTurn -> checks if player's turn
	//@return boolean -> returns if it's the player's turn
	public boolean getPlayerTurn()
	{
		return isTurn;
	}

	//isWinner -> checks if player wins
	//@return boolean -> returns if player wins
	public boolean isWinner() {
		// TODO Auto-generated method stub
		return isWinner;
	}
}
