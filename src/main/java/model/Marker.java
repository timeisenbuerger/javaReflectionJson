package model;

import java.util.List;

public class Marker
{
   String name;
   List<Double> location;

   public Marker(String name, List<Double> location)
   {
      this.name = name;
      this.location = location;
   }

   public String getName()
   {
      return name;
   }

   public List<Double> getLocation()
   {
      return location;
   }
}
