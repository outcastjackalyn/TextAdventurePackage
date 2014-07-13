package jjcard.textGames.game;

import java.util.List;

import jjcard.textGames.game.impl.Status;

public interface IMob extends IGameElement{

	public String getDescription();
	public int getMaxHealth();
	public int getHealth();
	public int getMoney();
	public IGameElementMap<IItem> getInventory();
	public IItem getItem(String key);
	public IArmour getArmour();
	public IWeapon getWeapon();
	/**
	 * returns defense only. Does not add Armour bonus.
	 * @return
	 */
	public int getBasicDefense();
	
	/**
	 * returns attack only. Does not add weapon bonus.
	 * @return
	 */
	public int getBasicAttack();
	public boolean isHostile();
	public List<Status> getStatusList();
	public boolean containsStatus(Status s);
	public boolean removeStatus(Status s);
	/**
	 * Sets the weapon to Weapon in the inventory with given name
	 * @param a
	 * @return
	 */
	public IWeapon setWeapon(String weaponName);
	/**
	 * Sets the armour to armour in the inventory with given name
	 * @param a
	 * @return
	 */
	public IArmour setArmour(String armourName);
	
	public String inventoryToString();
	public String getStandardWeaponKey();
	public boolean isKeyForWeapon(String key);
	public boolean isKeyforArmour(String key);
	public String getStandardArmourKey();
	
	/**
	 * Removes the weapon and returns the result
	 * @return
	 */
	public IWeapon removeWeapon();
	
	public boolean isDead();
	
	public boolean containsItem(String key);
	/**
	 * attack this mob for this amount of damage minus any defenses of the Mob. 
	 * Returns new health
	 * @param attack
	 * @return
	 */
	public int attackMob(int damage);
	
	public IItem removeItem(String key);
	
	public IArmour removeArmour();
	
	public IItem addItem(IItem add);
	/**
	 * gets attack plus any attack bonuses
	 * @return
	 */
	public int getFullAttack();
	
	public IWeapon setWeapon(IWeapon w);
	public IArmour setArmour( IArmour a);
	public void addStatus(Status s);
	/**
	 * Removes the Inventory from the mob and returns the result
	 * @return
	 */
	public IGameElementMap<IItem> removeInventory();
}
