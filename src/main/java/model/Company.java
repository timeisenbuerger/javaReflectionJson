package model;

import java.util.ArrayList;
import java.util.List;

public class Company
{
   private String name;
   private List<Department> departments = new ArrayList<Department>();

   public Company(String name)
   {
      super();
      this.name = name;

   }

   public Company(String name, List<Department> departments)
   {
      super();
      this.name = name;
      this.departments = departments;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public List<Department> getDepartments()
   {
      return departments;
   }

   public void addDepartment(Department d)
   {
      this.departments.add(d);
   }

}
