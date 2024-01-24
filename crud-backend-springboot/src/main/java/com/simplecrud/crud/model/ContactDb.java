package com.simplecrud.crud.model;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;


@Entity
@Table(name="contactdb_info")
public class ContactDb
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_id")
    private int contactId;
    @Column(name="contact_name")
    private String contactName;
    @Column(name="contact_address")
    private String contactAddress;
    @Column(name="contact_phone_number")
    private String contactPhoneNumber;

    public ContactDb() {
    }

    public ContactDb(String contactName, String contactAddress, String contactPhoneNumber) {
        this.contactName = contactName;
        this.contactAddress = contactAddress;
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public ContactDb(ContactDb orElseThrow) {
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    // define to string
    @Override
    public String toString() {
        return "ContactDb{" +
                "contactId=" + contactId +
                ", contactName='" + contactName + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'' +
                '}';
    }
}
