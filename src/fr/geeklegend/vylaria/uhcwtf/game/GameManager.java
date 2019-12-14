package fr.geeklegend.vylaria.uhcwtf.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Lists;

import fr.geeklegend.vylaria.uhcwtf.config.Config;
import fr.geeklegend.vylaria.uhcwtf.tablist.Tablist;
import fr.geeklegend.vylaria.uhcwtf.utils.WorldUtils;

public class GameManager
{

	private static List<String> biomes = new ArrayList<String>();
	private static List<Player> spectators = new ArrayList<Player>();
	private static List<Player> players = new ArrayList<Player>();
	private static List<ItemStack> items = new ArrayList<ItemStack>();
	private static Map<Player, Location> previousLocation = new HashMap<Player, Location>();
	private static boolean isPvP, isBorder;

	public static void load()
	{
		loadBiomes();
		
		WorldUtils.changeBiome(GameManager.getRandomBiome());
		WorldCreator worldCreator = new WorldCreator(Config.getDefaultConfig().getString("game.world.name"));
		worldCreator.environment(Environment.NORMAL);
		worldCreator.type(WorldType.LARGE_BIOMES);
		
		Bukkit.createWorld(worldCreator);
		
		World world = Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name"));
		world.setDifficulty(Difficulty.NORMAL);
		world.setTime(1000L);
		world.setStorm(false);
		world.setThundering(false);
		world.setGameRuleValue("doDaylightCycle", "false");

		clearEntities(world);

		loadItems();

		Tablist.setHealth();
	}

	public static void clearEntities(World world)
	{
		for (Entity entity : world.getEntities())
		{
			entity.remove();
		}
	}
	
	public static void loadBiomes()
	{
		biomes.add("jungle");
		biomes.add("moutains");
		biomes.add("desert");
		biomes.add("savanna");
		biomes.add("badlands");
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
		items.add(new ItemStack(Material.INK_SACK, 5, (byte) 4));
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
		items.add(new ItemStack(Material.EXP_BOTTLE, 5));
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

	public static void preGameSetup()
	{
		for (Player players : Bukkit.getOnlinePlayers())
		{
			GameManager.addPlayer(players);
			
			players.setLevel(0);
			players.getInventory().clear();
		}

		teleport();
	}

	public static void gameSetup()
	{
		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setGameMode(
					GameMode.valueOf(Config.getDefaultConfig().getString("setups.game.gamemode").toUpperCase()));
		}
	}

	public static void teleport()
	{
		for (Player players : Bukkit.getOnlinePlayers())
		{
			Random random = new Random();

			int x = players.getLocation().getBlockX() + random.nextInt(200);
			int y = 150;
			int z = players.getLocation().getBlockZ() + random.nextInt(200);

			Location location = new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name")),
					x + 1.5, y, z + 1);

			players.teleport(location);

			addPreviousLocation(players, location);

			WorldUtils.loadSchematic(
					new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world.name")), x, y, z),
					"uhcwtfsolocage", true);
		}
	}
	
	public static String getRandomBiome()
	{
		List<String> givenList = Lists.newArrayList(biomes);
		int randomIndex = new Random().nextInt(givenList.size());
		String randomBiome = givenList.get(randomIndex);
		givenList.remove(randomIndex);
		return randomBiome;
	}

	public static ItemStack getRandomItem()
	{
		List<ItemStack> givenList = Lists.newArrayList(items);
		int randomIndex = new Random().nextInt(givenList.size());
		ItemStack randomItem = givenList.get(randomIndex);
		givenList.remove(randomIndex);
		return randomItem;
	}
	
	public static void addPlayer(Player player)
	{
		players.add(player);
	}

	public static void removePlayer(Player player)
	{
		players.remove(player);
	}

	public static boolean playersContains(Player player)
	{
		return players.contains(player);
	}
	
	public static List<Player> getPlayers()
	{
		return players;
	}
	
	public static void addSpectator(Player player)
	{
		spectators.add(player);
	}

	public static void removeSpectator(Player player)
	{
		spectators.remove(player);
	}

	public static boolean spectatorsContains(Player player)
	{
		return spectators.contains(player);
	}

	public static List<Player> getSpectators()
	{
		return spectators;
	}

	public static Location getPreviousLocation(Player player)
	{
		return previousLocation.get(player);
	}

	public static void addPreviousLocation(Player player, Location location)
	{
		previousLocation.put(player, location);
	}

	public static void removePreviousLocation(Player player)
	{
		previousLocation.remove(player);
	}

	public static boolean previousLocationContains(Player player)
	{
		return previousLocation.containsKey(player);
	}

	public static boolean isPvP()
	{
		return isPvP;
	}

	public static void setPvP(boolean isPvP)
	{
		GameManager.isPvP = isPvP;
	}

	public static boolean isBorder()
	{
		return isBorder;
	}

	public static void setBorder(boolean isBorder)
	{
		GameManager.isBorder = isBorder;
	}

}
