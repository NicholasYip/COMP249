//-----------------------------------------------------
//Assignment #1
//Written by Nicholas Yiphoiyen - 40117237
//COMP 249, Section QQ
//due on the 31st of January 2020
//The appliance class and the main method are in the same file.
//The user is prompted for the inventory size and then what he wants to do
//He can create new items or modify some
//or he can check all items made by a certain company or all items under a certain price

import java.util.Scanner;

public class appliance {
    private String type;
    private String brand;
    private long serialNumber;
    private double price;
    private static int number = 0;

    public appliance(String type, String brand, double price) {
        this.type = type;
        this.brand = brand;
        if (price >= 1)
            this.price = price;
        else {
            this.price = 1;
            System.out.println("Error in price");
        }
        this.serialNumber = number + 1000000;
        number++;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return ("Serial Number: " + serialNumber + ", Brand: " + brand +
                ", Type: " + type + ", Price: " + String.format("%.2f", price) + "$");
    }

    public static int findNumberOfCreatedAppliances() {
        return number;
    }

    public boolean equals(appliance compared) {
        return (this.type.equals(compared.type) && this.brand.equals(compared.brand) && (this.price == compared.price));
    }

    public boolean findApplianceby(String company) {
        return (brand.equalsIgnoreCase(company));
    }

    public boolean findCheaperThan(double cost) {
        return (price < cost);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final String password = "c249";
        int numberAppliances = -1;
        int choice = 0;
        int choice2 = 0;
        int wantedCreate = 0;
        int choice3 = 1;
        int choice4 = -1;
        int i = 1;
        int errorCounter = 0;
        boolean passwordCorrect = false;
        boolean stillEditing = true;
        String allowedAppliances = "fridge, air conditioner, washer, dryer, freezer, stove, dishwasher, water heater, microwave";

        System.out.println("Welcome to Nicholas's appliance store");
        while (numberAppliances < 0) {
            System.out.print("Please enter the number of appliances the story's inventory can contain: ");
            numberAppliances = input.nextInt();
        }

        int spotsLeft = numberAppliances;

        appliance inventory[] = new appliance[numberAppliances];
        while (choice != 5) {
            for (int test = 0; test == 0; ) {
                System.out.print("What do you want to do?\n" +
                        "\t1. Enter new appliances (password required)\n" +
                        "\t2. Change information of an appliance (password required)\n" +
                        "\t3. Display all appliances by a specific brand\n" +
                        "\t4. Display all appliances under a certain price\n" +
                        "\t5. Quit\nPlease enter your choice: ");
                choice = input.nextInt();
                if ((choice <= 5) && (choice >= 1))
                    test++;
            }

            if (choice == 5) { //option 5, exiting the program
                System.out.println("\nThank you for shopping at Nicholas's appliance store. Come again!");
                System.exit(0);
            }

            if (choice == 1 || choice == 2) { //password for choice 1 and 2 only
                String attempt = null;
                while (errorCounter < 12) {
                    do {
                        System.out.print("Attempt #" + (errorCounter + 1) + ", Enter your password: ");
                        attempt = input.next();
                        if (attempt.equals(password))
                            break;
                        errorCounter++;
                    } while (errorCounter % 3 != 0);
                    if (attempt.equals(password)) {
                        passwordCorrect = true;
                        break;
                    }
                    if (errorCounter == 12 && choice == 1) {
                        System.out.println("Program detected suspicious activities and  will terminate immediately!");
                        System.exit(0);
                    } else if (errorCounter%3 == 0 && choice == 1)
                        break;
                    else if(errorCounter%3 == 0 && choice == 2) {
                        errorCounter = 0;
                        break;
                    }
                }
                if (passwordCorrect && choice == 1) { //option 1, after password is correct
                    errorCounter = 0;
                    for (int correct = 0; correct == 0; ) {
                        System.out.print("How many appliances would you like to enter in the inventory: ");
                        wantedCreate = input.nextInt();
                        if (wantedCreate > spotsLeft)
                            System.out.println("Unfortunately the inventory isn't big enough to hold " + wantedCreate + " items. The inventory only has " + spotsLeft + " free slots.");
                        else
                            correct++;
                    }
                    System.out.println(wantedCreate + " appliances will be entered in the inventory");
                    for (; i <= wantedCreate; i++) {
                        String name = "abcdef";
                        while (!allowedAppliances.contains(name.toLowerCase())){
                            System.out.print("Please enter appliance #" + i + "\'s type: ");
                            name = input.next();
                            if (!allowedAppliances.contains(name.toLowerCase()))
                                System.out.println("Invalid type");
                        }
                        System.out.print("Please enter appliance #" + i + "\'s brand: ");
                        String brand = input.next();
                        double price = 0;
                        while (price < 1) {
                            System.out.print("Please enter appliance #" + i + "\'s price: ");
                            price = input.nextDouble();
                            if (price < 1)
                                System.out.println("Invalid price");
                        }
                        inventory[i - 1] = new appliance(name, brand, price);
                        System.out.println("You just created application #" + i + " " + inventory[i - 1] + "\n");
                        spotsLeft--;
                    }
                }

                if (passwordCorrect && choice == 2) { //option 2, after password is correct
                    errorCounter = 0;
                    choice3 = 1;
                    while (stillEditing) {
                        if (choice3 == 2) break;
                        System.out.print("Enter the serial number of the appliance you want to modify: ");
                        int serialNumber = input.nextInt();
                        boolean serialNumberFake = true;

                        for (i = 0; i < inventory.length; i++) {
                            if (inventory[i] == null) continue;
                            if (serialNumber == inventory[i].getSerialNumber()) {
                                serialNumberFake = false;
                                break;
                            }
                        }
                        if (serialNumberFake) {
                            System.out.println("Unfortunately the serial number " + serialNumber + " was not found in the store's inventory");
                            System.out.println("What would you like to do");
                            System.out.print("\t1. Enter another serial number\n\t2. Exit back to the menu\nPlease enter your choice: ");
                            choice3 = 0;

                            while (choice3 != 1 && choice3 != 2) {
                                choice3 = input.nextInt();
                                if (choice3 != 1 && choice3 != 2) {
                                    System.out.print("Not a valid option, retry: ");
                                    continue;
                                }
                            }
                            if (choice3 == 1) {
                                continue;
                            } else if (choice3 == 2) {
                                break;
                            }
                        }
                        if (choice3 == 2)
                            break;

                        System.out.println(inventory[i]);

                        while (true) {
                            choice4 = -1;
                            while (choice4 < 0 || choice4 > 4) {
                                System.out.print("\nWhat information would you like to change?\n" +
                                        "\t1. brand\n" +
                                        "\t2. type\n" +
                                        "\t3. price\n" +
                                        "\t4. Quit\n" +
                                        "Please enter your choice: ");
                                choice4 = input.nextInt();
                                if (choice4 < 0 || choice4 > 4)
                                    System.out.println("invalid choice");
                            }
                            if (choice4 == 4)
                                break;
                            else if (choice4 == 1) {
                                System.out.print("Please enter the new brand of your appliance: ");
                                String newBrand = input.next();
                                inventory[i].setBrand(newBrand);
                            } else if (choice4 == 2) {
                                String newType = "abcdef";
                                while (!allowedAppliances.contains(newType.toLowerCase())){
                                    System.out.print("Please enter the new type of your appliance: ");
                                    newType = input.next();
                                    if (!allowedAppliances.contains(newType.toLowerCase()))
                                        System.out.println("Invalid Type");
                                    else
                                        inventory[i].setType(newType);}
                            } else if (choice4 == 3) {
                                int newPrice = -1;
                                while (newPrice < 1) {
                                    System.out.print("Please enter the new price of your appliance: ");
                                    newPrice = input.nextInt();
                                    if (newPrice < 1)
                                        System.out.println("Invalid price");
                                    else
                                        inventory[i].setPrice(newPrice);
                                }
                            }
                            System.out.println(inventory[i]);
                        }
                        if (choice4 == 4)
                            break;
                    }
                }
            }

            if (choice == 3) { //option 3, no need for a password
                System.out.print("Please enter the brand that interests you: ");
                String company = input.next();
                System.out.println("Here is a list of all appliances made by " + company);
                for (int j = 0, display = 1; j < inventory.length; j++) {
                    if (inventory[j] == null) continue;
                    else if (inventory[j].findApplianceby(company)) {
                        System.out.println("\t" + display + ". " + inventory[j]);
                        display++;
                    }
                }
                System.out.println();
            }


            if (choice == 4) { //option 4, no need for a password
                System.out.print("Please enter the price that interests you: ");
                double cost = input.nextDouble();
                System.out.println("Here is a list of all appliances under " + String.format("%.2f", cost) + "$");
                for (int j = 0, display = 1; j < inventory.length; j++) {
                    if (inventory[j] == null) continue;
                    else if (inventory[j].findCheaperThan(cost)) {
                        System.out.println("\t" + display + ". " + inventory[j]);
                        display++;
                    }
                }
                System.out.println();
            }
        }
    }
}
	
