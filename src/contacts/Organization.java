package contacts;


import java.io.Serializable;

public class Organization extends contacts.Record implements Serializable {
    private String address;
    private static final long serialVersionUID = 1L;

    public Organization(String name, String address,String phoneNumber) {
        super(name, phoneNumber);
        this.address = address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String[] valuesOfFields() {
        String[] array = new String[]{getName(), address, getPhoneNumber()};
        return array;
    }

    @Override
    public String toString() {
        return "Organization name: " + super.getName() + "\nAddress: " + address + "\nNumber: " + super.getPhoneNumber() + "\nTime created: " + super.getCreatedDataTime() + "\nTime last edit: " + super.getLastEditTime();
    }

    @Override
    public String fieldsToEdit() {
        return "name, address , number";
    }

    @Override
    public String shortInfo() {
        return super.shortInfo();
    }
}
