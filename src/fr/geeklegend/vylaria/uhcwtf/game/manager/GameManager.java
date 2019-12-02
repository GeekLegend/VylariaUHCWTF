package fr.geeklegend.vylaria.uhcwtf.game.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.google.common.collect.Lists;

import fr.geeklegend.vylaria.api.cuboid.Cuboid;
import fr.geeklegend.vylaria.uhcwtf.UHCWTF;
import fr.geeklegend.vylaria.uhcwtf.utils.world.WorldUtils;

public class GameManager
{

	public static Cuboid WAITING_CAGE_CUBOID = new Cuboid(new Location(Bukkit.getWorlds().get(0), 27, 94, 16),
			new Location(Bukkit.getWorlds().get(0), 6, 87, -5));

	private static List<Location> cages = new ArrayList<Location>();
	private static List<ItemStack> items = new ArrayList<ItemStack>();

	public static void load()
	{
		for (World worlds : UHCWTF.getInstance().getServer().getWorlds())
		{
			worlds.setStorm(false);
			worlds.setThundering(false);
			worlds.setDifficulty(Difficulty.NORMAL);
			worlds.setTime(1000L);
			worlds.setGameRuleValue("doDaylightCycle", "false");
			worlds.setGameRuleValue("doMobSpawning", "false");

			WorldBorder worldBorder = worlds.getWorldBorder();
			worldBorder.setCenter(0, 0);
			worldBorder.setSize(750);

			clearEntities(worlds);
		}
	}

	public static void clearEntities(World world)
	{
		for (Entity entity : world.getEntities())
		{
			entity.remove();
		}
	}

	public static void setHealthTabList()
	{
		ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		Objective health = scoreboard.registerNewObjective("Health", "health");
		health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

		
		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setScoreboard(scoreboard);
			
			Score score = health.getScore(players);
			score.setScore((int) (players.getHealth() * 5));
		}
	}

	public static void preGameSetup()
	{
		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.getInventory().clear();
			players.setGameMode(GameMode.SURVIVAL);
		}
	}

	public static void loadCages()
	{
		cages.add(new Location(Bukkit.getWorlds().get(0), 177.5, 150.0, 146.5, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), 33.5, 150.0, 268.5, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), -159.5, 150.0, 151.5, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
		cages.add(new Location(Bukkit.getWorlds().get(0), x, y, z, -180, 0));
	}
	public static void generateCages()
	{
		WorldUtils.loadSchematic(player, name, true);
	}

	public static void destroyWaitingCage()
	{
		for (Block blocks : WAITING_CAGE_CUBOID)
		{
			blocks.setType(Material.AIR);
		}
	}
	
	public static void loadItems()
	{
		items.add(new ItemStack(Material.STONE));
		items.add(new ItemStack(Material.STONE, 4, (byte) 1));
		items.add(new ItemStack(Material.STONE, 4, (byte) 2));
		items.add(new ItemStack(Material.STONE, 4, (byte) 3));
		items.add(new ItemStack(Material.STONE, 4, (byte) 4));
		items.add(new ItemStack(Material.STONE, 4, (byte) 5));
		items.add(new ItemStack(Material.STONE, 4, (byte) 6));
		items.add(new ItemStack(Material.COBBLESTONE, 4));
		items.add(new ItemStack(Material.WOOD));
		items.add(new ItemStack(Material.WOOD, 4, (byte) 1));
		items.add(new ItemStack(Material.WOOD, 4, (byte) 2));
		items.add(new ItemStack(Material.WOOD, 4, (byte) 3));
		items.add(new ItemStack(Material.WOOD, 4, (byte) 4));
		items.add(new ItemStack(Material.WOOD, 4, (byte) 5));
		items.add(new ItemStack(Material.WOOD, 4, (byte) 1));
		items.add(new ItemStack(Material.PISTON_STICKY_BASE));
		items.add(new ItemStack(Material.PISTON_BASE));
		items.add(new ItemStack(Material.WEB));
		items.add(new ItemStack(Material.TNT, 4));
		items.add(new ItemStack(Material.BOOK));
		items.add(new ItemStack(Material.CHEST));
		items.add(new ItemStack(Material.REDSTONE));
		items.add(new ItemStack(Material.WORKBENCH));
		items.add(new ItemStack(Material.FURNACE));
		items.add(new ItemStack(Material.SIGN));
		items.add(new ItemStack(Material.LEVER));
		items.add(new ItemStack(Material.STONE_PLATE));
		items.add(new ItemStack(Material.WOOD_PLATE));
		items.add(new ItemStack(Material.REDSTONE_TORCH_ON));
		items.add(new ItemStack(Material.STONE_BUTTON));
		items.add(new ItemStack(Material.WOOD_BUTTON));
		items.add(new ItemStack(Material.SUGAR_CANE_BLOCK));
		items.add(new ItemStack(Material.GLOWSTONE));
		items.add(new ItemStack(Material.MELON_BLOCK));
		items.add(new ItemStack(Material.NETHER_WARTS));
		items.add(new ItemStack(Material.ENCHANTMENT_TABLE));
		items.add(new ItemStack(Material.BREWING_STAND_ITEM));
		items.add(new ItemStack(Material.ANVIL));
		items.add(new ItemStack(Material.QUARTZ_ORE));
		items.add(new ItemStack(Material.IRON_SPADE));
		items.add(new ItemStack(Material.IRON_PICKAXE));
		items.add(new ItemStack(Material.IRON_AXE));
		items.add(new ItemStack(Material.FLINT_AND_STEEL));
		items.add(new ItemStack(Material.APPLE, 4));
		items.add(new ItemStack(Material.BOW));
		items.add(new ItemStack(Material.ARROW, 4));
		items.add(new ItemStack(Material.COAL));
		items.add(new ItemStack(Material.DIAMOND));
		items.add(new ItemStack(Material.IRON_INGOT));
		items.add(new ItemStack(Material.GOLD_INGOT));
		items.add(new ItemStack(Material.IRON_SWORD));
		items.add(new ItemStack(Material.WOOD_SWORD));
		items.add(new ItemStack(Material.WOOD_SPADE));
		items.add(new ItemStack(Material.WOOD_PICKAXE));
		items.add(new ItemStack(Material.WOOD_AXE));
		items.add(new ItemStack(Material.STONE_SWORD));
		items.add(new ItemStack(Material.STONE_SPADE));
		items.add(new ItemStack(Material.STONE_PICKAXE));
		items.add(new ItemStack(Material.STONE_AXE));
		items.add(new ItemStack(Material.DIAMOND_SWORD));
		items.add(new ItemStack(Material.DIAMOND_SPADE));
		items.add(new ItemStack(Material.DIAMOND_PICKAXE));
		items.add(new ItemStack(Material.DIAMOND_AXE));
		items.add(new ItemStack(Material.MUSHROOM_SOUP));
		items.add(new ItemStack(Material.GOLD_SWORD));
		items.add(new ItemStack(Material.GOLD_SPADE));
		items.add(new ItemStack(Material.GOLD_PICKAXE));
		items.add(new ItemStack(Material.GOLD_AXE));
		items.add(new ItemStack(Material.WOOD_HOE));
		items.add(new ItemStack(Material.STONE_HOE));
		items.add(new ItemStack(Material.IRON_HOE));
		items.add(new ItemStack(Material.DIAMOND_HOE));
		items.add(new ItemStack(Material.GOLD_HOE));
		items.add(new ItemStack(Material.BREAD, 4));
		items.add(new ItemStack(Material.LEATHER_HELMET));
		items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
		items.add(new ItemStack(Material.LEATHER_LEGGINGS));
		items.add(new ItemStack(Material.LEATHER_BOOTS));
		items.add(new ItemStack(Material.CHAINMAIL_HELMET));
		items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		items.add(new ItemStack(Material.CHAINMAIL_BOOTS));
		items.add(new ItemStack(Material.IRON_HELMET));
		items.add(new ItemStack(Material.IRON_CHESTPLATE));
		items.add(new ItemStack(Material.IRON_LEGGINGS));
		items.add(new ItemStack(Material.IRON_BOOTS));
		items.add(new ItemStack(Material.DIAMOND_HELMET));
		items.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
		items.add(new ItemStack(Material.DIAMOND_LEGGINGS));
		items.add(new ItemStack(Material.DIAMOND_BOOTS));
		items.add(new ItemStack(Material.GOLD_HELMET));
		items.add(new ItemStack(Material.GOLD_CHESTPLATE));
		items.add(new ItemStack(Material.GOLD_LEGGINGS));
		items.add(new ItemStack(Material.GOLD_BOOTS));
		items.add(new ItemStack(Material.COOKED_BEEF, 4));
		items.add(new ItemStack(Material.GOLDEN_APPLE, 4));
		items.add(new ItemStack(Material.WATER_BUCKET));
		items.add(new ItemStack(Material.LAVA_BUCKET));
		items.add(new ItemStack(Material.SADDLE));
		items.add(new ItemStack(Material.REDSTONE));
		items.add(new ItemStack(Material.BOAT));
		items.add(new ItemStack(Material.MILK_BUCKET));
		items.add(new ItemStack(Material.SUGAR_CANE_BLOCK));
		items.add(new ItemStack(Material.PAPER));
		items.add(new ItemStack(Material.BOOK));
		items.add(new ItemStack(Material.FISHING_ROD));
		items.add(new ItemStack(Material.GLOWSTONE_DUST));
		items.add(new ItemStack(Material.COOKED_FISH, 4));
		items.add(new ItemStack(Material.MAP));
		items.add(new ItemStack(Material.MELON));
		items.add(new ItemStack(Material.COOKED_BEEF));
		items.add(new ItemStack(Material.ENDER_PEARL));
		items.add(new ItemStack(Material.BLAZE_ROD));
		items.add(new ItemStack(Material.GHAST_TEAR));
		items.add(new ItemStack(Material.GOLD_NUGGET));
		items.add(new ItemStack(Material.NETHER_WARTS));
		items.add(new ItemStack(Material.GLASS_BOTTLE));
		items.add(new ItemStack(Material.SPIDER_EYE));
		items.add(new ItemStack(Material.BLAZE_POWDER));
		items.add(new ItemStack(Material.MAGMA_CREAM));
		items.add(new ItemStack(Material.SPECKLED_MELON));
		items.add(new ItemStack(Material.EXP_BOTTLE, 4));
		items.add(new ItemStack(Material.GOLDEN_CARROT, 4));
		items.add(new ItemStack(Material.EXPLOSIVE_MINECART));
		items.add(new ItemStack(Material.POWERED_RAIL));
		items.add(new ItemStack(Material.GOLD_BARDING));
		items.add(new ItemStack(Material.LEASH));
		items.add(new ItemStack(Material.SAPLING));
		items.add(new ItemStack(Material.SAPLING, 1, (byte) 2));
		items.add(new ItemStack(Material.LEAVES, 1, (byte) 1));
		items.add(new ItemStack(Material.SPONGE));
		items.add(new ItemStack(Material.LONG_GRASS));
		items.add(new ItemStack(Material.WOOL, 4, (byte) 9));
		items.add(new ItemStack(Material.WOOL, 4, (byte) 15));
		items.add(new ItemStack(Material.WOOD_STAIRS));
		items.add(new ItemStack(Material.WOODEN_DOOR));
		items.add(new ItemStack(Material.LADDER));
		items.add(new ItemStack(Material.FENCE));
		items.add(new ItemStack(Material.JUKEBOX));
		items.add(new ItemStack(Material.STAINED_GLASS, 1, (byte) 9));
		items.add(new ItemStack(Material.STAINED_GLASS, 1, (byte) 13));
		items.add(new ItemStack(Material.STAINED_GLASS, 1, (byte) 15));
		items.add(new ItemStack(Material.SMOOTH_BRICK));
		items.add(new ItemStack(Material.WOOD_STEP));
		items.add(new ItemStack(Material.WOOD_STEP, 1, (byte) 5));
		items.add(new ItemStack(Material.WOOD_STEP, 1, (byte) 1));
		items.add(new ItemStack(Material.WOOD_STEP, 1, (byte) 2));
		items.add(new ItemStack(Material.WOOD_STEP, 1, (byte) 3));
		items.add(new ItemStack(Material.WOOD_STEP, 1, (byte) 4));
		items.add(new ItemStack(Material.DOUBLE_PLANT, 1, (byte) 1));
		items.add(new ItemStack(Material.CARPET, 1, (byte) 6));
		items.add(new ItemStack(Material.CARPET, 1, (byte) 7));
		items.add(new ItemStack(Material.CARPET, 1, (byte) 8));
		items.add(new ItemStack(Material.CARPET, 1, (byte) 9));
		items.add(new ItemStack(Material.STAINED_CLAY, 1, (byte) 5));
		items.add(new ItemStack(Material.STAINED_CLAY, 1, (byte) 7));
		items.add(new ItemStack(Material.STAINED_CLAY, 1, (byte) 9));
		items.add(new ItemStack(Material.SPRUCE_FENCE));
		items.add(new ItemStack(Material.RED_SANDSTONE));
		items.add(new ItemStack(Material.DARK_OAK_FENCE_GATE));
		items.add(new ItemStack(Material.RECORD_3));
		items.add(new ItemStack(Material.RECORD_10));
		items.add(new ItemStack(Material.RECORD_5));
		items.add(new ItemStack(Material.BANNER, 1, (byte) 13));
		items.add(new ItemStack(Material.BANNER, 1, (byte) 8));
		items.add(new ItemStack(Material.NAME_TAG));
		items.add(new ItemStack(Material.ITEM_FRAME));
		items.add(new ItemStack(Material.FLOWER_POT_ITEM));
		items.add(new ItemStack(Material.INK_SACK, 1, (byte) 7));
		items.add(new ItemStack(Material.BRICK_STAIRS));
		items.add(new ItemStack(Material.SMOOTH_STAIRS));
	}

	public static ItemStack getRandomItem()
	{
		List<ItemStack> givenList = Lists.newArrayList(items);

		int randomIndex = new Random().nextInt(givenList.size());
		ItemStack randomItem = givenList.get(randomIndex);
		givenList.remove(randomIndex);

		return randomItem;
	}

}
