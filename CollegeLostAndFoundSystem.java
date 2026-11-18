import java.util.*;
public class CollegeLostAndFoundSystem {
    static Scanner sc = new Scanner(System.in);
    static final int MAX = 100;
    static ItemReport[] reports = new ItemReport[MAX];
    static int count = 0;
    static class ItemReport {
        int reportId;
        String itemName,description,location,type,status;
        ItemReport(int id, String name, String desc,String loc, String itemType) {
            reportId = id;
            itemName = name;
            description = desc;
            location = loc;
            type = itemType;
            if (itemType.equals("LOST")) {
                status = "LOST";
            } else {
                status = "FOUND";
            }
        }
        void display() { 
            System.out.println("Item Details:");
            System.out.println("Report ID   : " + reportId);
            System.out.println("Item Name   : " + itemName);
            System.out.println("Description : " + description);
            System.out.println("Location    : " + location);
            System.out.println("Type        : " + type);
            System.out.println("Status      : " + status);
        }
    }
    static void reportItem(String type) { 
        if (count == MAX) {
            System.out.println("Storage is full!");
            return;
        }
        System.out.println("\nReport " + type + " Item ");
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Location: ");
        String loc = sc.nextLine();
        System.out.println("Enter report Id: ");
        int nextId = sc.nextInt();
        reports[count] = new ItemReport(nextId, name, desc, loc, type);
        System.out.println("\nReport submitted successfully!");
        System.out.println("Your Report ID: " + nextId);
        count++;
    }
    static void searchItem() { 
        System.out.print("\nEnter item name to search: ");
        String search = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (reports[i].itemName.equalsIgnoreCase(search)) {
                reports[i].display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching item found!");
        }
    }
    static void viewLostItems() {
        boolean found = false;
        System.out.println("\nLOST ITEMS");
        for (int i = 0; i < count; i++) {
            if (reports[i].type.equals("LOST")) {
                reports[i].display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No lost items reported.");
        }
    }
    static void viewFoundItems() {
        boolean found = false;
        System.out.println("\nFOUND ITEMS");
        for (int i = 0; i < count; i++) {
            if (reports[i].type.equals("FOUND")) {
                reports[i].display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No found items reported.");
        }
    }
    static void markReturned() {
        System.out.print("\nEnter Report ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (reports[i].reportId == id) {
                if (reports[i].type.equals("LOST")) {
                    System.out.println("Select a FOUND item report");
                    return;
                }
                if (reports[i].status.equals("RETURNED")) {
                    System.out.println("This item is already returned");                
                    return;
                }
                reports[i].status = "RETURNED";
                System.out.println("Item successfully marked as RETURNED");
                return;
            }
        }
        System.out.println("Invalid Report ID!");
    }
    public static void main(String[] args) {
        int choice;
        System.out.println("COLLEGE LOST AND FOUND SYSTEM");
        do {
            System.out.println("\n1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. Search Item");
            System.out.println("4. View Lost Items");
            System.out.println("5. View Found Items");
            System.out.println("6. Mark Item as Returned");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    reportItem("LOST");
                    break;
                case 2:
                    reportItem("FOUND");
                    break;
                case 3:
                    searchItem();
                    break;
                case 4:
                    viewLostItems();
                    break;
                case 5:
                    viewFoundItems();
                    break;
                case 6:
                    markReturned();
                    break;
                case 7:
                    System.out.println("Thank you for using College Lost and Found System");
                    break;
                default:
                    System.out.println("Invalid choice, Try again.");
            }
        } while (choice != 7);
        sc.close();
    }
}