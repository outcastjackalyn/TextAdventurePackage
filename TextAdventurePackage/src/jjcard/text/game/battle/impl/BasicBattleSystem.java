package jjcard.text.game.battle.impl;

import jjcard.text.game.IMob;
import jjcard.text.game.battle.IBattleSystem;
import jjcard.text.game.util.Experimental;
@Experimental
public class BasicBattleSystem implements IBattleSystem {

	@Override
	public int attackMob(IMob attacker, IMob defender) {
		
		int damage = Math.max(0, attacker.getFullAttack() - defender.getFullDefense());
		defender.setHealth(defender.getHealth() - damage);
		
		return damage;

	}

}