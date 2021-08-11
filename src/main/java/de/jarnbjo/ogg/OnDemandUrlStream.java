package de.jarnbjo.ogg;

import de.jarnbjo.ogg.LogicalOggStreamImpl;
import de.jarnbjo.ogg.OggPage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public final class OnDemandUrlStream {

   private URLConnection source;
   private InputStream sourceStream;
   public HashMap<Integer, LogicalOggStreamImpl> logicalStreams;
   private OggPage firstPage;

   public OnDemandUrlStream(URL var1) throws IOException {
      this.logicalStreams = new HashMap<Integer, LogicalOggStreamImpl>();
      this.source = var1.openConnection();
      this.sourceStream = this.source.getInputStream();
      this.firstPage = OggPage.create(this.sourceStream);
      LogicalOggStreamImpl var3 = new LogicalOggStreamImpl(this);
      this.logicalStreams.put(this.firstPage.streamSerialNumber, var3);
   }

   public final OggPage getOggPage() throws IOException {
      OggPage var1 = null;
      if(this.firstPage != null) {
         var1 = this.firstPage;
         this.firstPage = null;
      } else {
         var1 = OggPage.create(this.sourceStream);
      }
      
      return var1;
   }
}
