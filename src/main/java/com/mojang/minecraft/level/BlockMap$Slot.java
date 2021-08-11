package com.mojang.minecraft.level;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.level.BlockMap;
import java.io.Serializable;

class BlockMap$Slot implements Serializable {

   public static final long serialVersionUID = 0L;
   private int xSlot;
   private int ySlot;
   private int zSlot;
   private BlockMap blockMap;


   public BlockMap$Slot(BlockMap var1) {
      this.blockMap = var1;
   }

   public BlockMap$Slot init(float var1, float var2, float var3) {
      this.xSlot = (int)(var1 / 16.0F);
      this.ySlot = (int)(var2 / 16.0F);
      this.zSlot = (int)(var3 / 16.0F);
      if(this.xSlot < 0) {
         this.xSlot = 0;
      }

      if(this.ySlot < 0) {
         this.ySlot = 0;
      }

      if(this.zSlot < 0) {
         this.zSlot = 0;
      }

      if(this.xSlot >= this.blockMap.getWidth()) {
         this.xSlot = this.blockMap.getWidth() - 1;
      }

      if(this.ySlot >= this.blockMap.getDepth()) {
         this.ySlot = this.blockMap.getDepth() - 1;
      }

      if(this.zSlot >= this.blockMap.getHeight()) {
         this.zSlot = this.blockMap.getHeight() - 1;
      }

      return this;
   }

   public void add(Entity var1) {
      if(this.xSlot >= 0 && this.ySlot >= 0 && this.zSlot >= 0) {
         this.blockMap.entityGrid[(this.zSlot * this.blockMap.getDepth() + this.ySlot) * this.blockMap.getWidth() + this.xSlot].add(var1);
      }

   }

   public void remove(Entity var1) {
      if(this.xSlot >= 0 && this.ySlot >= 0 && this.zSlot >= 0) {
         this.blockMap.entityGrid[(this.zSlot * this.blockMap.getDepth() + this.ySlot) * this.blockMap.getWidth() + this.xSlot].remove(var1);
      }

   }

   public int getXSlot() {
      return this.xSlot;
   }

   public int getYSlot() {
      return this.ySlot;
   }

   public int getZSlot() {
      return this.zSlot;
   }
}
