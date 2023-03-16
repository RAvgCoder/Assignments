package Practice;

public class Main{
    public static void main(String[] args) {
        LinkedListM<String> contactLinkedListM = new LinkedListM<>("Hello");
        QueueM<Contact> contactQueueM = new QueueM<>(new Contact("Egbor","Oses","Chadwick","506-2714691"));
        for (int i = 0; i < 3; i++) {
            contactLinkedListM.append(i+"");
            contactQueueM.append(new Contact("nfksdks","Bashd","Maknd","326-543-2726"));
        }
        System.out.println(contactLinkedListM);
        System.out.println(contactQueueM);
        System.out.println("_".repeat(60));
        contactLinkedListM.reverse();
        contactQueueM.reverse();
        System.out.println(contactLinkedListM);
        System.out.println(contactQueueM);
    }
}