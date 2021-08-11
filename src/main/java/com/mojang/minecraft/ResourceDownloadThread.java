package com.mojang.minecraft;

import com.mojang.minecraft.Minecraft;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class ResourceDownloadThread extends Thread {

   private File dir;
   private Minecraft minecraft;
   boolean stopping = false;


   public ResourceDownloadThread(File var1, Minecraft var2) {
      this.minecraft = var2;
      this.setName("Resource download thread");
      this.setDaemon(true);
      this.dir = new File(var1, "resources/");
      if(!this.dir.exists() && !this.dir.mkdirs()) {
         throw new RuntimeException("The working directory could not be created: " + this.dir);
      }
   }

	public final void run() {
		try {
			List<String> var1 = new ArrayList<String>();
			URL var2 = new URL("http://www.minecraft.net/resources/");
			BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.openStream()));
			String var4 = null;
			while((var4 = var3.readLine()) != null) {
				var1.add(var4);
			}
			
			var3.close();
			for(int var5 = 0; var5 < var1.size(); var5++) {
				String var6 = var1.get(var5);
				try {
					String[] var7 = var6.split(",");
					String var8 = var7[0];
					int var9 = Integer.parseInt(var7[1]);
					File file = new File(this.dir, var8);
					if(!file.exists() || file.length() != var9) {
						file.getParentFile().mkdirs();
						String var10 = var8.replaceAll(" ", "%20");
						this.download(new URL(var2, var10), file);
					} else {
						int j = var8.indexOf("/");
						String var10 = var8.substring(0, j);
						String var11 = var8.substring(j + 1);
						if(var10.equalsIgnoreCase("sound")) {
							this.minecraft.sound.registerSound(file, var11);
						} else if(var10.equalsIgnoreCase("music")) {
							this.minecraft.sound.registerMusic(var11, file);
						}
					}
				} catch (Exception var7) {
					var7.printStackTrace();
				}
				
				if(this.stopping) {
					return;
				}
			}
		} catch (Exception var1) {
			var1.printStackTrace();
		}
	}

	private void download(URL var1, File var2) throws IOException {
		byte[] var3 = new byte[4096];
		DataInputStream var5 = new DataInputStream(var1.openStream());
		DataOutputStream var6 = new DataOutputStream(new FileOutputStream(var2));

		while(!this.stopping) {
			int var7;
			if((var7 = var5.read(var3)) < 0) {
				break;
			}

			var6.write(var3, 0, var7);
		}
		var5.close();
		var6.close();
	}
}
