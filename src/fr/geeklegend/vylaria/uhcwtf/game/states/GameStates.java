package fr.geeklegend.vylaria.uhcwtf.game.states;

public enum GameStates
{
	
	WAITING, PREGAME, GAME, FINISH;
	
	private static GameStates current;
	
	public static GameStates getState()
	{
		return current;
	}
	
	public static boolean isState(GameStates state)
	{
		return current == state;
	}
	
	public static void setState(GameStates state)
	{
		current = state;
	}

}
