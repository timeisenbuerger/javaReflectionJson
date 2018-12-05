package features;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

public class GsonParser
{
   public static JsonObject parseFromFile(String file) throws IOException
   {
      JsonObject object = null;

      JsonStreamParser parser = new JsonStreamParser(new FileReader(file));
      if( parser.hasNext() )
      {
         object = parser.next().getAsJsonObject();
      }
      return object;
   }
}
