package com.mojang.minecraft.render;

import com.mojang.minecraft.player.Player;
import java.util.Comparator;

public final class ChunkVisibleDistanceComparator implements Comparator<Chunk> {

   private Player player;


   public ChunkVisibleDistanceComparator(Player var1) {
      this.player = var1;
   }


   @Override
   public int compare(Chunk o1, Chunk o2) {
	   if (o1.visible || !o2.visible) {
		   if (o2.visible) {
			   float sqDist = o1.distanceSquared(this.player);
			   float otherSqDist = o2.distanceSquared(this.player);
	
			   if(sqDist == otherSqDist) {
				   return 0;
			   } else if (sqDist > otherSqDist) {
				   return -1;
			   } else {
				   return 1;
			   }
		   } else {
			   return 1;
		   }
	   } else {
		   return -1;
	   }
	}
}
