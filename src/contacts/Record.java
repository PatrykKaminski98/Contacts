package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Record implements Serializable {
    private String name;
    private String phoneNumber;
    private final LocalDateTime created = LocalDateTime.now();
    private LocalDateTime lastEdit = created;
    private static final long serialVersionUID = 1L;

    public Record(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public String fieldsToEdit(){
        return "";
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }

    public String[] valuesOfFields(){
        return null;
    }

    public LocalDateTime getCreatedDataTime() {
        return created;
    }

    public LocalDateTime getLastEditTime() {
        return lastEdit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkValidityOfNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "[no number]";
        }
        this.phoneNumber = phoneNumber;
    }

    public String shortInfo() {
        return name;
    }

    public void setAddress(String address) {
    }

    public void setSurname(String surname) {

    }

    public void setDateBirth(String dateBirth) {
    }

    public void setGender(String gender) {
    }

    public static boolean checkValidityOfNumber(String number) {
        Matcher matcher1 = Pattern.compile("(\\+?[\\d\\w]+[ -]?)([\\d\\w]{2,}+[ -])*([\\d\\w]{2,})?", Pattern.CASE_INSENSITIVE).matcher(number);
        Matcher matcher2 = Pattern.compile("(\\+?\\([\\d\\w]+\\)[ -]?)([\\d\\w]{2,}+[ -])*([\\d\\w]{2,})?", Pattern.CASE_INSENSITIVE).matcher(number);
        Matcher matcher3 = Pattern.compile("(\\+?[\\d\\w]+[ -])(\\([\\d\\w]{2,}+\\)[ -]?)([\\d\\w]{2,}+[ -])*([\\d\\w]{2,})?", Pattern.CASE_INSENSITIVE).matcher(number);
        if (matcher1.matches() || matcher2.matches() || matcher3.matches()) {
            return true;
        } else {
            return false;
        }
    }
}