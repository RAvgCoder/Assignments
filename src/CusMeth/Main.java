package CusMeth;

import CusMeth.LinkedListM;
import CusMeth.QueueM;
import CusMeth.StacksM;
import Practice.Contact;

public class Main{
    public static void main(String[] args) {
        LinkedListM<String> contactLinkedListM = new LinkedListM<>("Hello");
        QueueM<Contact> contactQueueM = new QueueM<>(new Contact("Egbor","Oses","Chadwick","506-2714691"));
        StacksM<String> stringStacksM = new StacksM<>("First");
        for (int i = 0; i < 3; i++) {
            stringStacksM.add((int)Math.pow(i,2)+"");
            contactLinkedListM.add(i+"");
            contactQueueM.add(new Contact("nfksdks","Bashd","Maknd","326-543-2726"));
        }
        System.out.println(stringStacksM);
        System.out.println(contactLinkedListM);
        System.out.println(contactQueueM);
        System.out.println("_".repeat(60));
        stringStacksM.pop();
        System.out.println(stringStacksM);
        System.out.println(contactLinkedListM);
        System.out.println(contactQueueM);

    }

}