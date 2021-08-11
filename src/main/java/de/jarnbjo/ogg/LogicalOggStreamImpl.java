package de.jarnbjo.ogg;

import de.jarnbjo.ogg.EndOfOggStreamException;
import de.jarnbjo.ogg.OggPage;
import de.jarnbjo.ogg.OnDemandUrlStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class LogicalOggStreamImpl {

   private OnDemandUrlStream source;
   private OggPage currentPage;
   private int currentSegmentIndex;


   public LogicalOggStreamImpl(OnDemandUrlStream var1) {
      this.source = var1;
   }

   private synchronized OggPage getNextOggPage() throws IOException {
      this.currentPage = this.source.getOggPage();
      return this.currentPage;
   }

   public final synchronized byte[] getNextOggPacket() throws IOException {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      boolean var2 = false;
      if(this.currentPage == null) {
         this.currentPage = this.getNextOggPage();
      }

      int var4 = 255;
      while(var4 == 255) {
         if(this.currentSegmentIndex >= this.currentPage.segmentOffsets.length) {
            this.currentSegmentIndex = 0;
            if(this.currentPage.eos) {
               throw new EndOfOggStreamException();
            }

            this.currentPage = this.getNextOggPage();
            if(var1.size() == 0) {
               if(this.currentPage.continued) {
                  var2 = false;

                  while(!var2) {
                     if(this.currentPage.segmentLengths[this.currentSegmentIndex++] != 255) {
                        var2 = true;
                     }

                     if(this.currentSegmentIndex > this.currentPage.segmentTable.length) {
                        this.currentPage = this.source.getOggPage();
                     }
                  }
               }
            }
         }

         if(this.currentPage.segmentLengths.length == 0) {
            break;
         }

         var4 = this.currentPage.segmentLengths[this.currentSegmentIndex];
         var1.write(this.currentPage.data, this.currentPage.segmentOffsets[this.currentSegmentIndex], var4);
         ++this.currentSegmentIndex;
      }

      return var1.toByteArray();
   }
}
