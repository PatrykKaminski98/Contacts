package contacts;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ContactsBook {
    Scanner scanner = new Scanner(System.in);

    List<Record> records = new LinkedList<Record>();
    private String pathfile;

    public ContactsBook(String pathfile) {
      this.pathfile = pathfile;
    }

    public ContactsBook() {

    }

    public String search(){
        System.out.println("Enter search query:");
        String query = scanner.nextLine();
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.search(records, query);
        searchEngine.printResults();
        System.out.println("[search] Enter action ([number], back, again):");
        String action = scanner.nextLine();
        if (action.equalsIgnoreCase("back")){
            return "menu";
        }
        else if(action.equalsIgnoreCase("again")) {
            return "search";
        }
        else if(Integer.parseInt(action) > 0 && Integer.parseInt(action) <= searchEngine.searchingRecords.size()){
            return  recordsAction(searchEngine.searchingRecords.get(Integer.parseInt(action) - 1));
        } else {
            System.out.println("Wrong input");
            return "menu";
        }
    }

    public String recordsAction(Record record){
        while(true) {
            System.out.println("[record] Enter action (edit, delete, menu):");
            String action = scanner.nextLine();
            switch (action.toLowerCase()) {
                case "edit": {
                    editingRecords(record);
                    break;
                }
                case "delete": {
                    removeRecord(record);
                    break;
                }
                case "menu": {
                    return "menu";

                }
                default: {
                    System.out.println("Wrong input");
                    return "menu";
                }
            }
        }

    }

    public void addRecord() {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();
        if(type.equalsIgnoreCase("person")) addPerson();
        else if (type.equalsIgnoreCase("organization")) addOrganization();
        else System.out.println("Wrong input!. Let's try again");

    }

    private void addPerson(){
        System.out.println("Enter the name:");
        String name = scanner.nextLine();
        System.out.println("Enter the surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter a birth date(YYYY-MM-dd):");
        String date = scanner.nextLine();
        System.out.println("Enter the gender(M, F):");
        String gender = scanner.nextLine();
        if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")){
        } else {
            System.out.println("Bad gender!");
            gender = "[no data]";
        }
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        if(Record.checkValidityOfNumber(number)) {
            records.add(new Person(name, surname, date, gender, number));
        } else{
            System.out.println("Wrong number format!");
            records.add(new Person(name, surname, date, gender, "[no number]"));
        }
        System.out.println("A record added!");

    }

    private void addOrganization(){
        System.out.println("Enter the organization name:");
        String name = scanner.nextLine();
        System.out.println("Enter the address:");
        String address = scanner.nextLine();
        System.out.println("Enter the number:");
        String number = scanner.nextLine();
        if(Record.checkValidityOfNumber(number)) {
            records.add(new Organization(name, address, number));
        } else {
            System.out.println("Wrong number format!");
            records.add(new Organization(name, "[no number]",address));
        }
        System.out.println("A record added!");

    }

    public void displayRecordInfo() {
        System.out.println("Enter index to show info:");
        int index = scanner.nextInt() - 1;
        System.out.println(records.get(index).toString());
    }

    public String displayListOfRecords() {
        for(Record record : records) {
            System.out.print(records.indexOf(record) + 1 + " ");
            System.out.println(record.shortInfo());
        }
        System.out.println("[list] Enter action ([number], back):");
        String action = scanner.nextLine();
         if (action.equalsIgnoreCase("back")) {
            return "menu";
        }
        else if(Integer.parseInt(action) > 0 && Integer.parseInt(action) <= records.size()){
            System.out.println(records.get(Integer.parseInt(action) - 1).toString());
            return recordsAction(records.get(Integer.parseInt(action) - 1));
        } else {
            System.out.println("Wrong input");
            return "menu";
        }

    }

    public void removeRecord(Record record){
        for (int i = 0; i < records.size(); i++) {
            if(records.get(i).equals(record)) records.remove(i);
        }
        System.out.println("The record removed!");
    }

    public void editingRecords(Record record){
        System.out.println("[record] Select a field (" + record.fieldsToEdit() + "):");
        String field = scanner.nextLine();
        switch (field.toLowerCase()) {
            case "name": {
                System.out.println("Enter name:");
                record.setName(scanner.nextLine());
                break;
            }
            case "surname": {
                System.out.println("Enter surname:");
                record.setSurname(scanner.nextLine());
                break;
            }
            case "birth": {
                System.out.println("Enter birth date(YYYY-MM-dd):");
                String date = scanner.nextLine();
                record.setDateBirth(date);
                break;
            }
            case "gender":{
                System.out.println("Enter gender:");
                record.setGender(scanner.nextLine());
                break;
            }
            case "number": {
                System.out.println("Enter number:");
                String newNumber = scanner.nextLine();
                record.setPhoneNumber(newNumber);
            }
            case "address": {
                System.out.println("Enter address:");
                record.setAddress(scanner.nextLine());
                break;
            }
            default:{
                System.out.println("Wrong input...");
                break;
            }
        }
        record.setLastEdit(LocalDateTime.now());
        System.out.println("The record updated!");
    }

    public void countRecords() {
        System.out.println("The Phone Book has " + records.size() + " records.");
    }

    public void serializeToFile() {
        try {
            SerializationUtils.serialize(records, pathfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeFromFile(){
        try {
            records = (List<Record>) SerializationUtils.deserialize(pathfile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

