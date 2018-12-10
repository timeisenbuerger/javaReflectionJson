#Repository: javaReflectionJson

##Instructions to install own examples

- store the example JSON file in the inputs directory
- add the Java Object model corresponding to the JSON file in the directory src/main/java/model
- then open the class GsonParser under the directory src/main/java/features
- there you can copy one of the methods parseToCompany or parseToPosition
- after that you can change the return value to your root class type and
 the inner of the method like in the following snippet

```
public static YourClassType parseToYourClassType(String file) throws IOException
   {
      Gson gson = new Gson();
      JsonReader reader;
      YourClassType yourObject = new YourClassType();

      reader = new JsonReader(new FileReader(file));

      yourObject = gson.fromJson(reader, yourObject.getClass());
      reader.close();

      return yourObject;
   }
```

- now you can open the test class CompareTreesTest under the directory src/test/java
- there you can create a own constant with your path to the JSON file
- then initialize an root object of your Java object model like in the following snippet

```
private YourClassType yourObject;

@Before
public void setUp() throws Exception
{
     yourObject = GsonParser.parseToYourClassType(FILE_PATH_YOUR_FILE);
}
```

- create a new test method with the code line like below
```
assertTrue(ObjTreeComparator.validate(yourObject, FILE_PATH_YOUR_FILE));
```

- then you can run this method to test your own example