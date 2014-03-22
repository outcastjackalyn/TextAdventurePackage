package jjcard.textGames.game;

import jjcard.textGames.game.impl.ItemUse;

public interface IItem extends IGameElement{
	
	public boolean isHidden();
	
	public boolean isMovable();
	
	public ItemUse getUse();
	
	public void setUse(ItemUse change);
	
	/**
	 * returns true if item can be retrieved by the player
	 * @return
	 */
	public boolean canGet();
	
	public void setHidden(boolean change);
	
	public void setMovable(boolean change);
	
	public int getCost();
	
	public String getInfo();
	
	public int getLevel();
	
	
	

}
