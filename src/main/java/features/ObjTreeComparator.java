package features;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class ObjTreeComparator
{
   public static boolean isEqual(List<Object> elements, File jsonFile)
   {
      boolean result = true;

      try
      {
         BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
         List<String> lines = bufferedReader.lines().collect(Collectors.toList());

         int lastElementIndex = 0;

         for( int i = 0; i < lines.size(); i++ )
         {
            String line = lines.get(i).trim();
            if( line.startsWith("{") || line.startsWith("}") )
            {
               continue;
            }
            else
            {
               if( line.contains(":") )
               {
                  String[] parts = line.split(":");

                  String firstItem = parts[0].trim().replace("\"", "");
                  String secondItem = parts[1].trim();

                  String nextElement = (String) elements.get(lastElementIndex);

                  if( !firstItem.equals(nextElement) )
                  {
                     result = false;
                  }

                  lastElementIndex++;

                  if( secondItem.length() > 1 && !secondItem.startsWith("[") && !secondItem.startsWith("{") )
                  {
                     nextElement = (String) elements.get(lastElementIndex);
                     if( secondItem.endsWith(",") )
                     {
                        secondItem = secondItem.substring(0, secondItem.length() - 1);
                     }

                     if( !secondItem.equals(nextElement) )
                     {
                        result = false;
                     }

                     lastElementIndex++;
                  }
               }
            }
         }
      }
      catch(FileNotFoundException e)
      {
         e.printStackTrace();
      }

      return result;
   }
}
