package jjcard.text.game.leveling;

/**
 * Interface classes need to implement in order to use a LevelingStrategy
 * @author jjcard
 *
 */
public interface HasLeveling {

	public int getLevel();
	
	public void setLevel(int level);
	
	public int getXp();

}
