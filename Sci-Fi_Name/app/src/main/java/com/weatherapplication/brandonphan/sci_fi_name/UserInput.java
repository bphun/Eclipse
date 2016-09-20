package com.weatherapplication.brandonphan.sci_fi_name;

import java.util.Scanner;

/**
 * Convenience class for getting input from a user
 */
public class UserInput   
{
    public static String getString() {
        
      Scanner in = new Scanner(System.in);
      return in.next();
    
   }
}
