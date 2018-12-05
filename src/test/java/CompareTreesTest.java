import java.io.File;

import com.google.gson.JsonObject;
import features.GsonParser;
import features.ObjTreeComparator;
import features.ReflectionObjCollector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CompareTreesTest
{
   private static final String FILE_PATH_COMPANY = "inputs\\sampleCompany.json";

   private JsonObject object;

   @Before
   public void setUp() throws Exception
   {
      object = GsonParser.parseFromFile(FILE_PATH_COMPANY);
   }

   @Test
   public void testCompareTrees()
   {
      ReflectionObjCollector reflectionObjCollector = new ReflectionObjCollector(object);
      assertTrue(ObjTreeComparator.isEqual(reflectionObjCollector.getElements(), new File(FILE_PATH_COMPANY)));
   }
}
