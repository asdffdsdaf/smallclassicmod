package com.mojang.minecraft.render;

import com.mojang.minecraft.player.Player;
import java.util.Comparator;

public final class ChunkDistanceComparator implements Comparator<Chunk> {

   private Player player;


   public ChunkDistanceComparator(Player var1) {
      this.player = var1;
   }


   @Override
   public int compare(Chunk o1, Chunk o2) {
	   float sqDist = o1.distanceSquared(this.player);
	   float otherSqDist = o2.distanceSquared(this.player);

	   if(sqDist == otherSqDist) {
		   return 0;
	   } else if (sqDist > otherSqDist) {
		   return -1;
	   } else {
		   return 1;
	   }
   }
}
