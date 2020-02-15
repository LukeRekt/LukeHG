
package me.luke.hg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class HGScoreboard extends BukkitRunnable {

	@Override
	@SuppressWarnings("deprecation")
	public void run() {

		Scoreboard score = Main.sm.getNewScoreboard();
		for (Player p : Bukkit.getOnlinePlayers()) {
			Objective sc = score.registerNewObjective(p.getName() + "", "dummy");
			sc.setDisplayName("     �6[�lHG�6]     ");
			sc.getScore(Bukkit.getOfflinePlayer("�a")).setScore(12);
			;
			if (HG.state == HGState.STARTING) {
				sc.getScore(Bukkit.getOfflinePlayer("�aCome�ando em")).setScore(11);
				;
				sc.getScore(Bukkit.getOfflinePlayer("�e" + HGMessages.getTime2()))
					.setScore(10);
				;

			}
			// if (HGUtils.state == HGState.INVUNERABLE) {
			// sc.getScore(Bukkit.getOfflinePlayer("�aInvuneravel"))
			// .setScore(11);
			// ;
			// sc.getScore(
			// Bukkit.getOfflinePlayer("�e" + HGMessages.getTime2()))
			// .setScore(10);
			// ;
			// }
			else {
				sc.getScore(Bukkit.getOfflinePlayer("�aSeja o Melhor")).setScore(11);
				;
			}
			sc.getScore(Bukkit.getOfflinePlayer("�b")).setScore(9);
			;
			sc.getScore(
				Bukkit.getOfflinePlayer("�e" + HG.players.size() + " �aJogadores"))
				.setScore(8);
			;
			sc.getScore(Bukkit.getOfflinePlayer("�c")).setScore(7);
			;
			sc.getScore(Bukkit.getOfflinePlayer("�aSeu Dinheiro")).setScore(6);
			;
			sc.getScore(Bukkit.getOfflinePlayer("�e" + Main.getMoney(p)))
				.setScore(5);
			;
			sc.getScore(Bukkit.getOfflinePlayer("�f")).setScore(4);
			;
			sc.getScore(Bukkit.getOfflinePlayer("�aSeu Kit")).setScore(3);
			;
			sc.getScore(Bukkit.getOfflinePlayer("�e" + Main.getKit(p))).setScore(2);
			;
			sc.getScore(Bukkit.getOfflinePlayer("�e")).setScore(1);
			;
			sc.setDisplaySlot(DisplaySlot.SIDEBAR);
			p.setScoreboard(score);
		}

	}

}
