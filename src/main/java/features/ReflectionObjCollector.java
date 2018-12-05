package features;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReflectionObjCollector
{
   private List<Object> elements = new ArrayList<>();

   public ReflectionObjCollector(Object root)
   {
      collect(root);
   }

   private void collect(Object obj)
   {
      try
      {
         if( obj instanceof JsonObject )
         {
            Class<?> jsonObjClass = obj.getClass();
            Field field = jsonObjClass.getDeclaredField("members");
            if( field != null )
            {
               Set<Map.Entry<String, JsonElement>> entrySet = (Set<Map.Entry<String, JsonElement>>) findMethodAndExecute(
                     jsonObjClass, obj, "entrySet");
               if( entrySet != null && !entrySet.isEmpty() )
               {
                  fillObjectMap(entrySet);
               }
            }
         }
      }
      catch(NoSuchFieldException | IllegalAccessException e)
      {
         e.printStackTrace();
      }
   }

   private void fillObjectMap(Set<Map.Entry<String, JsonElement>> entrySet) throws NoSuchFieldException, IllegalAccessException
   {
      for( Map.Entry<String, JsonElement> entry : entrySet )
      {
         Class<?> keyClass = entry.getKey().getClass();
         Class<?> valClass = entry.getValue().getClass();

         Object key = findMethodAndExecute(keyClass, entry.getKey(), "toString");

         if( valClass.getSimpleName().equals("JsonPrimitive") )
         {
            Object val = findMethodAndExecute(valClass, entry.getValue(), "toString");
            elements.add(key);
            elements.add(val);
         }
         else if( valClass.getSimpleName().equals("JsonObject") )
         {
            elements.add(key);
            collect(entry.getValue());
         }
         else if( valClass.getSimpleName().equals("JsonArray") )
         {
            Field field = valClass.getDeclaredField("elements");
            field.setAccessible(true);

            List elements = (List) field.get(entry.getValue());
            this.elements.add(key);
            for( Object element : elements )
            {
               collect(element);
            }
         }
      }
   }

   private Object findMethodAndExecute(Class<?> classType, Object obj, String methodName)
   {
      for( Method method : classType.getMethods() )
      {
         if( (method.getName().startsWith(methodName)) )
         {
            //Method found, run it
            try
            {
               return method.invoke(obj);
            }
            catch(IllegalAccessException e)
            {
               Logger.getAnonymousLogger().info("Could not determine method: " + method.getName());
            }
            catch(InvocationTargetException e)
            {
               Logger.getAnonymousLogger().info("Could not determine method: " + method.getName());
            }
         }
      }
      return null;
   }

   public List<Object> getElements()
   {
      return elements;
   }
}
