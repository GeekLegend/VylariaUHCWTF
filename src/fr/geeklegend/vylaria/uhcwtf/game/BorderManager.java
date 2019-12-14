package fr.geeklegend.vylaria.uhcwtf.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import fr.geeklegend.vylaria.uhcwtf.config.Config;

public class BorderManager
{
	
	private static WorldBorder worldBorder;
	private static int size = Config.getDefaultConfig().getInt("game.world.size");
	private static boolean isMoved;
	
	public static void load()
	{
		World world = Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name"));
		worldBorder = world.getWorldBorder();
		worldBorder.setCenter(0.0, 0.0);
		worldBorder.setSize(1600);
	}

	public static void retract()
	{
		worldBorder.setSize(worldBorder.getSize() - 1);
		size--;
	}
	
	public static int getSize()
	{
		return size;
	}

	public static void setSize(int size)
	{
		BorderManager.size = size;
	}
	
	public static boolean isMoved()
	{
		return isMoved;
	}
	
	public static void setMoved(boolean isMoved)
	{
		BorderManager.isMoved = isMoved;
	}
	
}
