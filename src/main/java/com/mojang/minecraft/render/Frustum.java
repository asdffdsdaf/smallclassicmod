package com.mojang.minecraft.render;


public class Frustum {

   public float[][] frustum = new float[16][16];
   public float[] projection = new float[16];
   public float[] modelview = new float[16];
   public float[] clipping = new float[16];


   public final boolean isBoxInFrustum(float var1, float var2, float var3, float var4, float var5, float var6) {
      for(int var7 = 0; var7 < 6; ++var7) {
         if(this.frustum[var7][0] * var1 + this.frustum[var7][1] * var2 + this.frustum[var7][2] * var3 + this.frustum[var7][3] <= 0.0F && this.frustum[var7][0] * var4 + this.frustum[var7][1] * var2 + this.frustum[var7][2] * var3 + this.frustum[var7][3] <= 0.0F && this.frustum[var7][0] * var1 + this.frustum[var7][1] * var5 + this.frustum[var7][2] * var3 + this.frustum[var7][3] <= 0.0F && this.frustum[var7][0] * var4 + this.frustum[var7][1] * var5 + this.frustum[var7][2] * var3 + this.frustum[var7][3] <= 0.0F && this.frustum[var7][0] * var1 + this.frustum[var7][1] * var2 + this.frustum[var7][2] * var6 + this.frustum[var7][3] <= 0.0F && this.frustum[var7][0] * var4 + this.frustum[var7][1] * var2 + this.frustum[var7][2] * var6 + this.frustum[var7][3] <= 0.0F && this.frustum[var7][0] * var1 + this.frustum[var7][1] * var5 + this.frustum[var7][2] * var6 + this.frustum[var7][3] <= 0.0F && this.frustum[var7][0] * var4 + this.frustum[var7][1] * var5 + this.frustum[var7][2] * var6 + this.frustum[var7][3] <= 0.0F) {
            return false;
         }
      }

      return true;
   }
}
