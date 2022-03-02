package contacts;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {
    List<contacts.Record> searchingRecords = new LinkedList<contacts.Record>();

    public void search(List<contacts.Record> records, String query){

        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);

        for (contacts.Record record : records) {
            for (String s : record.valuesOfFields()) {
                Matcher matcher = pattern.matcher(s);
                if(matcher.find()){
                    searchingRecords.add(record);
                }
            }
        }
    }

    public void printResults(){
        System.out.println("Found "+searchingRecords.size()+" results:");
        for(contacts.Record record : searchingRecords) {
            System.out.print(searchingRecords.indexOf(record) + 1 + " ");
            System.out.println(record.shortInfo());
        }
    }
}
