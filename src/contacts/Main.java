package contacts;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactsBook book = new ContactsBook(args[0]);
        Scanner scanner = new Scanner(System.in);

        book.deserializeFromFile();
        String action = "menu";
        do {
            switch(action) {
                case "menu": {
                    System.out.println("[menu] Enter action (add, list, search, count, exit):");
                    action = scanner.nextLine();
                    break;
                }
                case "add": { //ready
                    book.addRecord();
                    action = "menu";
                    break;
                }
                case "search": { //ready
                    action = book.search();
                    break;
                }
                case "count": { //ready
                    book.countRecords();
                    action = "menu";
                    break;
                }
                case "list":{
                    action = book.displayListOfRecords();
                    break;
                }
            }
            System.out.println();
        } while(!action.toLowerCase(Locale.ROOT).equals("exit"));
        book.serializeToFile();

    }
}