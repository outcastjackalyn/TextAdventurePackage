package jjcard.textGames.game.parser.impl;

import jjcard.textGames.game.parser.ITextTokenType;

public enum SpaceTextTokenType implements ITextTokenType {
	
	ENEMY(true), TALK(false), LOOK(false), GET(false), DROP(false), WEAPON(true), 
	ARMOR(true), MOVE(false), SHIP(true), MOVE_SHIP(false), ITEM(true), PLAYER(true), 
	WORDS(true), UNKOWN;

	private boolean isObject;
	
	
	SpaceTextTokenType(boolean isObject){
		this.isObject = isObject;
	}
	SpaceTextTokenType(){
		this(true);
	}
	@Override
	public boolean isObject() {
		return isObject;
	}

	@Override
	public boolean isVerb() {
		return !isObject;
	}

}
