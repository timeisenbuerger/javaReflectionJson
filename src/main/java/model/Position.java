package model;

import java.util.ArrayList;
import java.util.List;

public class Position
{
   List<Marker> markers;

   public Position()
   {
      markers = new ArrayList<>();
   }

   public Position(List<Marker> markers)
   {
      this.markers = markers;
   }

   public List<Marker> getMarkers()
   {
      return markers;
   }
}
