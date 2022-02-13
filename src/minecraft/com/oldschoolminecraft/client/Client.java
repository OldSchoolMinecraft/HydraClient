package com.oldschoolminecraft.client;

import com.oldschoolminecraft.client.perks.PerkChecker;

import net.minecraft.client.Minecraft;

public class Client {
	public static Minecraft mc = Minecraft.getMinecraft();
	
	//API
	public static String apiURL = "https://os-mc.net/api/";
	
	//Client
	public String version = "v0.1";
	public String name = "OSM Client";
	public static String username;
	public static boolean isSupporter = false;
	public boolean isStaff = false;
	
	public static void onEnable() {
		username = mc.session.username;
		com.oldschoolminecraft.client.perks.PerkChecker.run();
		System.out.print(isSupporter);
	}
}