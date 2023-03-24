package Practice;

import java.util.*;
import java.lang.reflect.*;

public class Contact implements Comparable<Contact>{
  private String lastName;
  private String firstName;
  private String streetName;
  private String phoneNum;

  public Contact(String ...info)
  {
    this.lastName=info[0];
    this.firstName=info[1];
    this.streetName=info[2];
    this.phoneNum=info[3];
  }
  public boolean equals(Object other)
  {
    Field[] fields = this.getClass().getDeclaredFields();
    try {
      for (Field field: fields) {
        if(!(field.get(this)).equals(field.get(other))) return false;
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return true;
  }

  public int compareTo(Contact o)
  {
    return this.toString().compareTo(o.toString());
  }

  public String toString()
  {
    return String.format("%s, %s: %s, %s",lastName,firstName,streetName,phoneNum);
  }
}
