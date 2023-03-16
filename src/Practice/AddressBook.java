package Practice;
import java.util.*;
import java.util.stream.*;

//Lab 9
public class AddressBook {
  private ArrayList<Contact> list;

  public AddressBook(){this.list = new ArrayList<>();}

  public ArrayList<Contact> getList(){return this.list;}

  public void addContact(Contact c){
    list.stream()
//            .filter(contact -> contact.getPhoneNum().equals(c.getPhoneNum()))
            .filter(contact -> contact.equals(c))
            .findFirst()
            .orElseGet(() -> {
              list.add(c);
              return null;
            });
  }

  public int deleteContact(String otherLastName){
      int size = list.size();
      list.removeIf(contact -> contact.getLastName().equals(otherLastName));
      return size- list.size();
  }

  public String toString()
  {
    return list.stream()
            .sorted(Contact::compareTo)
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
  }
}    

