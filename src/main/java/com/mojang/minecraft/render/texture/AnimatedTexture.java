package com.mojang.minecraft.render.texture;


public class AnimatedTexture {

   public byte[] textureData = new byte[1024];
   public int textureId;
   public boolean anaglyph = false;


   public AnimatedTexture(int var1) {
      this.textureId = var1;
   }

   public void animate() {}
}
