package fr.geeklegend.vylaria.uhcwtf.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.geeklegend.vylaria.api.cuboid.Cuboid;
import fr.geeklegend.vylaria.uhcwtf.config.Config;

public class CageManager
{

	public static final Cuboid WAITING_CAGE_CUBOID = new Cuboid(
			new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name")),
					Config.getDefaultConfig().getDouble("setups.join.spawn.x") + 30,
					Config.getDefaultConfig().getDouble("setups.join.spawn.y") + 30,
					Config.getDefaultConfig().getDouble("setups.join.spawn.z") + 30),
			new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name")),
					Config.getDefaultConfig().getDouble("setups.join.spawn.x") - 30,
					Config.getDefaultConfig().getDouble("setups.join.spawn.y") - 30,
					Config.getDefaultConfig().getDouble("setups.join.spawn.z") - 30));

	public static Cuboid PREGAME_CAGE_CUBOID = null;

	public static void remove(boolean waiting)
	{
		if (waiting)
		{
			for (Block blocks : WAITING_CAGE_CUBOID.getBlocks())
			{
				if (blocks.getType() == Material.STAINED_GLASS)
				{
					blocks.setType(Material.AIR);
				}
			}
		} else
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				PREGAME_CAGE_CUBOID = new Cuboid(
						new Location(players.getWorld(), players.getLocation().getBlockX() + 10,
								players.getLocation().getBlockY() + 10, players.getLocation().getBlockZ() + 10),
						new Location(players.getWorld(), players.getLocation().getBlockX() - 10,
								players.getLocation().getBlockY() - 10, players.getLocation().getBlockZ() - 10));
				
				for (Block blocks : PREGAME_CAGE_CUBOID.getBlocks())
				{
					if (blocks.getType() == Material.STAINED_GLASS)
					{
						blocks.setType(Material.AIR);
					}
				}
			}
		}
	}

}
