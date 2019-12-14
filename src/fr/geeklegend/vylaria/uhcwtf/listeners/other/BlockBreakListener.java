package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.geeklegend.vylaria.uhcwtf.game.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.GameState;

public class BlockBreakListener implements Listener
{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		
		if (GameState.isState(GameState.WAITING))
		{
			event.setCancelled(true);
		} else if (GameState.isState(GameState.GAME))
		{
			if (block != null)
			{
				block.setType(Material.AIR);
				block.getWorld().dropItemNaturally(block.getLocation(), GameManager.getRandomItem());
			}
		}
	}

}
