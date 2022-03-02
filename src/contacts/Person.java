package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Person extends contacts.Record implements Serializable {
    private String gender = "no data";
    private String surname = "no data";
    private LocalDate dateBirth = null;
    private static final long serialVersionUID = 1L;

    public Person(String name, String surname, String dateBirth, String gender, String phoneNumber ) {
        super(name, phoneNumber);
        setDateBirth(dateBirth);
        this.gender = gender;
        this.surname = surname;
    }

    @Override
    public String[] valuesOfFields() {
        String[] array = new String[]{getName(), surname, String.valueOf(dateBirth), gender, getPhoneNumber()};
        return array;
    }

    @Override
    public String toString() {
        if(dateBirth==null) return "Name: " + super.getName() + "\nSurname: " + surname + "\nBirth date: [no data]" + "\nGender: " + gender +  "\nNumber: " + super.getPhoneNumber() + "\nTime created: " + super.getCreatedDataTime() + "\nTime last edit: " + super.getLastEditTime();
        return "Name: " + super.getName() + "\nSurname: " + surname + "\nBirth date: " + dateBirth + "\nGender: " + gender +  "\nNumber: " + super.getPhoneNumber() + "\nTime created: " + super.getCreatedDataTime() + "\nTime last edit: " + super.getLastEditTime();
    }


    @Override
    public String fieldsToEdit() {
        return "name, surname, birth date, gender, number";
    }

    @Override
    public String shortInfo() {
        return super.shortInfo() + " " + surname;
    }

    private boolean isValidData(String data){
        try {
            LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    @Override
    public void setDateBirth(String dateBirth) {
        if(isValidData(dateBirth)){
            this.dateBirth = LocalDate.parse(dateBirth);
        } else {
            System.out.println("Invalid data");
        }
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
