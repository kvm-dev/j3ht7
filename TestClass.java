package ru.geekbrains.j3.ht7;

import java.lang.reflect.Method;
import java.util.*;

public class Test1lass   {
     int Bflag = 0;
     int Aflag = 0;

   public static void start(Class obj) throws Exception {
       Test1lass t1 = new Test1lass();

       Method[] methods = obj.getDeclaredMethods();
       ArrayList<Method> arr = new ArrayList<>();
       for (Method m: methods) {
           if(m.isAnnotationPresent(BeforeSuite.class)){
               t1.Bflag++;
           }
           if(m.isAnnotationPresent(AfterSuite.class)){
               t1.Aflag++;
           }
       }
       if ((t1.Aflag | t1.Bflag) > 1) throw new RuntimeException();

       for (Method m : methods) {

               if (m.isAnnotationPresent(BeforeSuite.class)) {
                       m.invoke(null);
               }

               if (m.isAnnotationPresent(Test.class)) {
                   arr.add(m);
               }
           }


       arr.sort((o1, o2) -> {return o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority(); });

       for (int i = arr.size() - 1; i >= 0; i--) {
           System.out.print("priority: " + arr.get(i).getAnnotation(Test.class).priority() + " TestCase: ");
           arr.get(i).invoke(null);

       }

           for (Method m : methods) {
               if (m.isAnnotationPresent(AfterSuite.class)) {
                       m.invoke(null);
                   }
               }
           }
   }
