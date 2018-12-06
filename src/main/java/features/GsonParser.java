package features;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonReader;
import model.Company;
import model.Position;

public class GsonParser
{
   /**
    *
    * Method to generate generic object tree of a JSON file
    *
    * @param file
    * @return generic object tree
    * @throws IOException
    */

   public static JsonObject parseToTree(String file) throws IOException
   {
      JsonObject object = null;

      JsonStreamParser parser = new JsonStreamParser(new FileReader(file));
      if( parser.hasNext() )
      {
         object = parser.next().getAsJsonObject();
      }
      return object;
   }

   /**
    *
    * Method to generate a Company object by a JSON file
    *
    * @param file
    * @return Company object
    * @throws IOException
    */

   public static Company parseToCompany(String file) throws IOException
   {
      Gson gson = new Gson();
      JsonReader reader;
      Company company = new Company("");

      reader = new JsonReader(new FileReader(file));

      company = gson.fromJson(reader, company.getClass());
      reader.close();

      return company;
   }

   /**
    *
    * Method to generate a Position object by a JSON file
    *
    * @param file
    * @return Position object
    * @throws IOException
    */

   public static Position parseToPosition(String file) throws IOException
   {
      Gson gson = new Gson();
      JsonReader reader;
      Position pos = new Position();

      reader = new JsonReader(new FileReader(file));

      pos = gson.fromJson(reader, pos.getClass());
      reader.close();

      return pos;
   }

}
