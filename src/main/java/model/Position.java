package model;

import java.util.HashSet;

public class Position
{
   HashSet<Marker> markers;

   public Position()
   {
      markers = new HashSet<>();
   }

   public Position(HashSet<Marker> markers)
   {
      this.markers = markers;
   }

   public HashSet<Marker> getMarkers()
   {
      return markers;
   }
}
