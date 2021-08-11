package com.mojang.minecraft.net;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.ErrorScreen;
import com.mojang.minecraft.net.NetworkHandler;
import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.net.PacketType;
import java.io.IOException;

final class ServerConnectThread extends Thread {

   private String server;
   private int port;
   private String username;
   private String key;
   private Minecraft minecraft;
   private NetworkManager netManager;


   ServerConnectThread(NetworkManager var1, String var2, int var3, String var4, String var5, Minecraft var6) {
      this.netManager = var1;
      this.server = var2;
      this.port = var3;
      this.username = var4;
      this.key = var5;
      this.minecraft = var6;
   }

   public final void run() {
      try {
         NetworkHandler var4 = new NetworkHandler(this.server, this.port);
         this.netManager.netHandler = var4;
         this.netManager.netHandler.netManager = this.netManager;
         this.netManager.netHandler.send(PacketType.IDENTIFICATION, new Object[]{Byte.valueOf((byte)7), this.username, this.key, Integer.valueOf(0)});
         this.netManager.successful = true;
      } catch (IOException var3) {
         this.minecraft.online = false;
         this.minecraft.networkManager = null;
         this.minecraft.setCurrentScreen(new ErrorScreen("Failed to connect", "You failed to connect to the server. It\'s probably down!"));
         this.netManager.successful = false;
      }
   }
}
