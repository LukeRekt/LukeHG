package me.luke.hg;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;

import me.luke.hg.comandos.ForceIniciar;
import me.luke.hg.comandos.TesteKit;
import me.luke.hg.kit.KitSelector;
import me.luke.hg.listener.EntityListener;
import me.luke.hg.listener.PlayerListener;
import me.luke.hg.listener.WorldListener;
import me.luke.hg.manager.PlayerManager;
import me.luke.hg.task.GamerTaskTimer;
import me.luke.hg.utils.ServerStage;

public class Main extends JavaPlugin {

	private static Main plugin;

	public int TEMPO_INICIANDO = 500;
	public int TEMPO_INVENCIBILIDADE = 10;
	public int TEMPO_JOGO = 0;
	public int TEMPO_FIM = 0;

	public int MINIMO_JOGADORES = 1;
	
	public ArrayList<PlayerManager> playermanager = new ArrayList<PlayerManager>();
	public ArrayList<UUID> playersInGame = new ArrayList<UUID>();
	public ArrayList<Player> playersLeftGame = new ArrayList<Player>();

	private ServerStage serverStage;
	public void onLoad() {
		plugin = this;
		serverStage = ServerStage.INICIANDO;
		deletarMundo(new File("world"));
	}

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new WorldListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
		Bukkit.getPluginManager().registerEvents(new EntityListener(this), this);
		getCommand("finiciar").setExecutor(new ForceIniciar());
		getCommand("kits").setExecutor(new TesteKit());
		
		
		loadSchematic();

		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new GamerTaskTimer(this), 0L, 20L);
	}

	public void onDisable() {

	}

	public static Main getPlugin() {
		return plugin;
	}

	public ServerStage getServerStage() {
		return serverStage;
	}
	public ServerStage setServerStage(ServerStage serverStage) {
		return this.serverStage = serverStage;
	}
	public void forceServerStage() {
		if(serverStage == getServerStage().INICIANDO) {
			setServerStage(ServerStage.INVENCIBILIDADE);
		}
	}
	public boolean hasServerStage(ServerStage serverStage) {

		return this.serverStage == serverStage ? true : false;
	}

	private void deletarMundo(File arquivo) {
		if (arquivo.isDirectory()) {
			String[] lista = arquivo.list();

			for (int i = 0; i < lista.length; i++) {
				deletarMundo(new File(arquivo, lista[i]));
			}
		}

		arquivo.delete();
	}

	
	
	public String timerFormat(int i) {
		int millis = i * 1000;
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
		
		
		
		return dateFormat.format(Integer.valueOf(millis));
		
		
		
	}
	
	
	public void updateScoreboard() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			
          Scoreboard scoreboard = p.getScoreboard();
          Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
  		
  		objective.getScore("Jogadores").setScore(Bukkit.getOnlinePlayers().size());
  		
  		if(plugin.hasServerStage(ServerStage.INICIANDO)){
  			objective.setDisplayName("§cIniciando em " + timerFormat(TEMPO_INICIANDO));
  		}else if (plugin.hasServerStage(ServerStage.INVENCIBILIDADE)) {
  			objective.setDisplayName("§cInvencibilidade " + timerFormat(TEMPO_INVENCIBILIDADE));
  		}else if (plugin.hasServerStage(ServerStage.ANDAMENTO)) {
  			objective.setDisplayName("§cComecou " + timerFormat(TEMPO_JOGO));
  		}else if (plugin.hasServerStage(ServerStage.ACABOU)) {
  			objective.setDisplayName("§cAcabou " + timerFormat(TEMPO_FIM));
			
  		
  		
          /*Score score2 = objective.getScore("§a" + (hasServerStage(serverStage.ANDAMENTO) ? TEMPO_JOGO
  		        : hasServerStage(ServerStage.INVENCIBILIDADE) ? TEMPO_INVENCIBILIDADE : TEMPO_INICIANDO));
  		
  		  score2.setScore(15);
  		  
  		  */
		}
		}
	}

	public void sendScoreboard(Player p) {
		ScoreboardManager SBManager = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = SBManager.getNewScoreboard();
		Objective objective = scoreboard.registerNewObjective("board", "dummy");
		objective.setDisplayName("§cIniciando em " + timerFormat(TEMPO_INICIANDO));
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.getScore("Jogadores").setScore(Bukkit.getOnlinePlayers().size());;

	/*	Score score1 = objective.getScore("§c" + serverStage.getStageName());
		score1.setScore(16);
		
		Score score2 = objective.getScore("§a" + (hasServerStage(serverStage.ANDAMENTO) ? TEMPO_JOGO
		        : hasServerStage(ServerStage.INVENCIBILIDADE) ? TEMPO_INVENCIBILIDADE : TEMPO_INICIANDO));
		
		score2.setScore(15); */

		p.setScoreboard(scoreboard);
	}
	
/*	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("finiciar")) {
			if(hasServerStage(ServerStage.INICIANDO)) {
				Bukkit.broadcastMessage("O Op " + p.getName() + " forcou o inicio da partida!");
				setServerStage(ServerStage.INVENCIBILIDADE);
			}else {
				p.sendMessage("nao pode");
			}

			
		}else {
			sender.sendMessage("seila");
			return true;
		}
		return false;
	}
*/

	private void loadSchematic()
	  {
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

