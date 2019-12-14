package fr.geeklegend.vylaria.uhcwtf.tablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Tablist
{
	
	private static ScoreboardManager scoreboardManager;
	private static Scoreboard scoreboard;
	private static Objective objective;
	
	public static void setHealth()
	{
		scoreboardManager = Bukkit.getScoreboardManager();
		scoreboard = scoreboardManager.getNewScoreboard();
		objective = scoreboard.registerNewObjective("health", "health");
		objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
	}
	
	public static void setHealthScore(Player player)
	{
		Score score = objective.getScore(player);
		score.setScore((int) (player.getHealth() * 5));
		
		player.setScoreboard(scoreboard);
	}

	public static ScoreboardManager getScoreboardManager()
	{
		return scoreboardManager;
	}

	public static Scoreboard getScoreboard()
	{
		return scoreboard;
	}

	public static Objective getObjective()
	{
		return objective;
	}

}
