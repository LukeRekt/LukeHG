package me.luke.hg.manager;

import java.util.UUID;

import org.bukkit.event.Listener;

public class PlayerManager implements Listener {

	private UUID uuid;
	private boolean ingame;
	private boolean isdead;

	public PlayerManager(UUID uuid, boolean ingame, boolean isdead) {
		this.setUuid(uuid);
		this.setIngame(ingame);
		this.setIsdead(ingame);
	}

	public UUID getUuid() {
		return uuid;
	}

	public UUID setUuid(UUID uuid) {
		return uuid;
	}

	public UUID isIngame() {
		return uuid;
	}

	public UUID setIngame(boolean ingame) {
		return uuid;
	}

	public UUID isIsdead() {
		return uuid;
	}

	public UUID setIsdead(boolean isdead) {
		return uuid;
	}
}
