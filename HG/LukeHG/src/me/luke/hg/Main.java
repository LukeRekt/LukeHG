
package me.luke.hg;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;

import me.luke.hg.event.HGWorldRegeneration;
import me.luke.hg.manager.Kit;
import me.luke.hg.gen.Feast;
import me.luke.hg.gen.Bolo;

public class Main extends JavaPlugin implements Listener {

	public static PluginManager pm = Bukkit.getPluginManager();
	public static ConsoleCommandSender send = Bukkit.getConsoleSender();
	public static ScoreboardManager sm = Bukkit.getScoreboardManager();
	public static BukkitScheduler sc = Bukkit.getScheduler();
	public static Main plugin;
	

	@Override
	public void onEnable() {
		plugin = this;
		pm.registerEvents(new HGEvents(), this);
		pm = Bukkit.getPluginManager();
		send = Bukkit.getConsoleSender();
		sm = Bukkit.getScoreboardManager();
		sc = Bukkit.getScheduler();
		pm.registerEvents(new HGEvents(), this);
		pm.registerEvents(new HGWorldRegeneration(), this);
		pm.registerEvents(new Kit(), this);
		new HGScoreboard().runTaskTimer(this, 20, 20);
		new HGTimer().runTaskTimer(this, 20, 20);;
		loadArena();
		for (int i = 1; i < 100; i++) {
			if (i<=10) {
				HG.startTimes.add(i);
				HG.gameOverTimes.add(i);
				HG.restartTimes.add(i);
				HG.invunerabilityTimes.add(i);
			}
			if (i%30==0) {
				HG.startTimes.add(i);
				HG.gameOverTimes.add(i);
				HG.restartTimes.add(i);
				HG.invunerabilityTimes.add(i);
			}
			if (i%25==0) {
				HG.startTimes.add(i);
				HG.gameOverTimes.add(i);
				HG.restartTimes.add(i);
				HG.invunerabilityTimes.add(i);
			}
		}
	}
	public static int getMoney(Player p) {
		return 100;
	}
	public static String getKit(Player p) {
		return "Nenhum";
	}
	public void loadArena() {


		World mundo = Bukkit.getWorld("world");
		  Location location = new Location(mundo, 0, 80, 0);
	    //Location location = player.getLocation();
	    WorldEditPlugin worldEditPlugin = (WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit");
	    File schematic = new File(getDataFolder() + File.separator + "/schematics/house.schematic");
	    EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), 10000);
	    try
	    {
	      CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
	      clipboard.rotate2D(90);
	      clipboard.paste(session, new Vector(location.getX(), location.getY(), location.getZ()), false);
	    }
	    catch (MaxChangedBlocksException|DataException|IOException e)
	    {
	      e.printStackTrace();
	    }
	  }


}

