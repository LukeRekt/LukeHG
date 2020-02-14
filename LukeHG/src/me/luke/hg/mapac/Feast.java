package me.luke.hg.mapac;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;

import me.luke.hg.Main;

public class Feast {
	World mundo = Bukkit.getWorld("world");
	Location location = new Location(mundo, 0, 80, 0);
	private Main plugin;
	
	public void loadFeast()
	  {
		
		this.plugin = Main.getPlugin();

		
		
		  
	    //Location location = player.getLocation();
	    WorldEditPlugin worldEditPlugin = (WorldEditPlugin)Bukkit.getPluginManager().getPlugin("WorldEdit");
	    File schematic = new File(plugin.getDataFolder() + File.separator + "/schematics/feast.schematic");
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
	
	
	public Location feastLoc() {
		return location;
		
	}


}
