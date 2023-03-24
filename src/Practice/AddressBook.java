package Practice;

import java.util.*;

//Lab 9
public class AddressBook {
  private TreeMap<String,Contact> list;

  public AddressBook(){this.list = new TreeMap<>();}

  public void addContact(Contact c){
    list.put(c.toString(),c);
  }

  public int deleteContact(String otherLastName){
    ArrayList<String> tempContacts = new ArrayList<>();
    int size = list.size();

    for (String key : list.keySet())
        if (key.split(",")[0].equals(otherLastName)) tempContacts.add(key);

    for (String s: tempContacts)
      list.remove(s);

    return size- list.size();
  }

  public String toString()
  {
    return String.join("\n", list.keySet());
  }
}    

