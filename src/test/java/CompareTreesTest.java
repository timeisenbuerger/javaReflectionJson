import java.io.IOException;

import features.GsonParser;
import features.ObjTreeComparator;
import model.Company;
import model.Position;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CompareTreesTest
{
   private static final String FILE_PATH_COMPANY = "inputs\\sampleCompany.json";
   private static final String FILE_PATH_POSITION = "inputs\\position.json";

   private Company company;
   private Position position;

   @Before
   public void setUp() throws Exception
   {
      company = GsonParser.parseToCompany(FILE_PATH_COMPANY);
      position = GsonParser.parseToPosition(FILE_PATH_POSITION);
   }

   @Test
   public void testCompareTreesCompany() throws IOException, IllegalAccessException
   {
      assertTrue(ObjTreeComparator.validate(company, FILE_PATH_COMPANY));
   }

   @Test
   public void testCompareTreesPosition() throws IOException, IllegalAccessException
   {
      assertTrue(ObjTreeComparator.validate(position, FILE_PATH_POSITION));
   }
}
