import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and setters

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
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String searchName) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Address book is empty.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void saveToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
            }
        } catch (IOException e) {
            System.err.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0];
                    String phoneNumber = data[1];
                    String email = data[2];
                    contacts.add(new Contact(name, phoneNumber, email));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}

public class task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        String filePath = "contacts.txt";

        
        addressBook.loadFromFile(filePath);

        while (true) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Save contacts to file");
            System.out.println("6. Exit");

            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();

                    
                    if (!name.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
                        addressBook.addContact(new Contact(name, phoneNumber, email));
                        System.out.println("Contact added successfully!");
                    } else {
                        System.out.println("Invalid input. All fields are required.");
                    }
                    break;

                case "2":
                    System.out.print("Enter the name of the contact to remove: ");
                    String searchName = scanner.nextLine();
                    Contact contactToRemove = addressBook.searchContact(searchName);
                    if (contactToRemove != null) {
                        addressBook.removeContact(contactToRemove);
                        System.out.println(contactToRemove.getName() + " removed from the address book.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter the name of the contact to search for: ");
                    searchName = scanner.nextLine();
                    Contact contactFound = addressBook.searchContact(searchName);
                    if (contactFound != null) {
                        System.out.println("Contact found:");
                        System.out.println(contactFound);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;

                case "4":
                    addressBook.displayAllContacts();
                    break;

                case "5":
                    addressBook.saveToFile(filePath);
                    System.out.println("Contacts saved to file.");
                    break;

                case "6":
                    addressBook.saveToFile(filePath); 
                    System.out.println("Exiting the Address Book System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
    }
}
