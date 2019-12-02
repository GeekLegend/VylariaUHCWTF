package fr.geeklegend.vylaria.uhcwtf.listeners.other;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.geeklegend.vylaria.uhcwtf.game.manager.GameManager;
import fr.geeklegend.vylaria.uhcwtf.game.states.GameStates;

public class BlockBreakListener implements Listener
{

	public BlockBreakListener()
	{
		GameManager.loadItems();
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Block block = event.getBlock();

		if (!GameStates.isState(GameStates.GAME))
		{
			event.setCancelled(true);
		} else
		{
			block.setType(Material.AIR);
			block.getWorld().dropItemNaturally(block.getLocation(), GameManager.getRandomItem());
		}
	}

}
