package features;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.Company;

public class GsonParser
{
   public static void main(String[] args) throws IOException
   {
      Company company = parseFromFile("inputs\\sampleCompany.json");
      System.out.println();
   }

   public static Company parseFromFile(String file) throws IOException
   {
      Gson gson = new Gson();
      JsonReader reader;

      reader = new JsonReader(new FileReader(file));
      Company company = gson.fromJson(reader, Company.class);
      reader.close();

      return company;
   }
}
