package Practice;

import java.util.*;

public class Demo {
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    AddressBook addressBook = new AddressBook();

    int contactLen = in.nextInt();
    ArrayList<String> contactArr = new ArrayList<>(List.of(in.nextLine().trim().split("\\s+")));

    for(int i=0; i<contactLen; i++){
      addressBook.addContact(
        new Contact(contactArr.remove(0),contactArr.remove(0),contactArr.remove(0),contactArr.remove(0))
      );
    }

    System.out.println(addressBook+"\n");

    Contact newContact = new Contact(
      contactArr.remove(contactArr.size()-4),contactArr.remove(contactArr.size()-3),contactArr.remove(contactArr.size()-2),contactArr.remove(contactArr.size()-1)
    );

    int deletedAcc=0;
    while (contactArr.size()!=0) {
      deletedAcc+=addressBook.deleteContact(contactArr.remove(0));
    }

    System.out.println("Deleted contacts: "+deletedAcc);
    System.out.println(addressBook);

    addressBook.addContact(newContact);
    System.out.println("\n"+addressBook);

  }
}

