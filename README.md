#Repository: javaReflectionJson

##How to use the implementation

####Adding the methods
The JSON file is parsed and either a generic object tree or a tree of concrete object types (like the given "company" or "position" class) is created. You can also use your own classes and JSON files to compare their contents with this implementation.
1. store the JSON file you want to deserialize in the inputs directory
2. add the Java Object model corresponding to the JSON file in the "src/main/java/model" directory 
3. open the class GsonParser in the "src/main/java/features" directory
4. copy one of the methods - either parseToCompany or parseToPosition
5. change the return value to your root class type and the method body as in the following snippet

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
####Testing the methods
The generic tree created when applying the "parseToTree" method on a JSON file is compared to the "concrete" tree when deserialize the file to a specific object (e.g. "parseToCompany" or "parseToYourClass"). It is checked whether these two trees contain the same data. 
1. open the test class CompareTreesTest in the "src/test/java" directory 
2. create a new constant with your path to the JSON file
3. initialize a root object of your Java object model as in the following snippet

```
private YourClassType yourObject;

@Before
public void setUp() throws Exception
{
     yourObject = GsonParser.parseToYourClassType(YOUR_FILEPATH);
}
```
4. create a new test method with the code line like below
```
assertTrue(ObjTreeComparator.validate(yourObject, YOUR_FILEPATH));
```
5. run this method to test it on your file