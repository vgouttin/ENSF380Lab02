/*
 * <h1 > Application . java </ h1 >
 *
 * <p > <b > Submission Date : </b > July 11th, 2024 </ p >
 *
 * @author Victor Gouttin
 * @version 1.0
 */

package ca.ucalgary.ensf380;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        // Sample usage of the classes
        Client client1 = new Client("John Doe", "123-456-7890", "123 Main St");
        Pet pet1 = new Pet("Buddy", "Golden Retriever", 5, new HealthRecord(new ArrayList<>(), new ArrayList<>()), new CareProfile("Feed twice a day", new ArrayList<>()));
        Booking booking1 = new Booking(new Date(), new Date(), pet1, client1);
        Payment payment1 = new Payment(100.0, new Date(), client1);
        Notification notification1 = new Notification("Reminder: Your booking is coming up!", new Date(), client1);

        // Add objects to client
        client1.addPet(pet1);
        client1.addBooking(booking1);
        client1.addPayment(payment1);
        client1.addNotification(notification1);

        // Interactive menu
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View client information");
            System.out.println("2. Change client information");
            System.out.println("3. View pet information");
            System.out.println("4. Change pet information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayClientInformation(client1);
                    break;
                case 2:
                    changeClientInformation(client1, scanner);
                    break;
                case 3:
                    displayPetInformation(client1);
                    break;
                case 4:
                    changePetInformation(client1, scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    private static void displayClientInformation(Client client) {
        System.out.println("\nClient Information:");
        System.out.println("Name: " + client.getName());
        System.out.println("Phone: " + client.getPhone());
        System.out.println("Address: " + client.getAddress());
        System.out.println("Pets: " + client.getPets().size());
        System.out.println("Bookings: " + client.getBookings().size());
        System.out.println("Payments: " + client.getPayments().size());
        System.out.println("Notifications: " + client.getNotifications().size());
    }

    private static void changeClientInformation(Client client, Scanner scanner) {
        System.out.println("\nCurrent Client Information:");
        System.out.println("1. Name: " + client.getName());
        System.out.println("2. Phone: " + client.getPhone());
        System.out.println("3. Address: " + client.getAddress());
        System.out.print("Enter the number of the information you want to change (1-3): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                client.setName(newName);
                System.out.println("Name updated successfully.");
                break;
            case 2:
                System.out.print("Enter new phone number: ");
                String newPhone = scanner.nextLine();
                client.setPhone(newPhone);
                System.out.println("Phone number updated successfully.");
                break;
            case 3:
                System.out.print("Enter new address: ");
                String newAddress = scanner.nextLine();
                client.setAddress(newAddress);
                System.out.println("Address updated successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void displayPetInformation(Client client) {
        List<Pet> pets = client.getPets();
        if (pets.isEmpty()) {
            System.out.println("\nNo pets registered for this client.");
        } else {
            System.out.println("\nPets Information:");
            for (int i = 0; i < pets.size(); i++) {
                Pet pet = pets.get(i);
                System.out.println("Pet " + (i + 1) + ":");
                System.out.println("Name: " + pet.getName());
                System.out.println("Breed: " + pet.getBreed());
                System.out.println("Age: " + pet.getAge());
                System.out.println("Health Record - Vaccinations: " + pet.getHealthRecord().getVaccinations());
                System.out.println("Care Profile - Feeding Instructions: " + pet.getCareProfile().getFeedingInstructions());
                System.out.println(); // Empty line for readability
            }
        }
    }

    private static void changePetInformation(Client client, Scanner scanner) {
        List<Pet> pets = client.getPets();
        if (pets.isEmpty()) {
            System.out.println("\nNo pets registered for this client.");
            return;
        }

        System.out.println("\nSelect a pet to update:");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getName());
        }
        System.out.print("Enter the number of the pet you want to update: ");
        int petChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (petChoice < 1 || petChoice > pets.size()) {
            System.out.println("Invalid pet choice.");
            return;
        }

        Pet selectedPet = pets.get(petChoice - 1);
        System.out.println("\nCurrent Pet Information:");
        System.out.println("1. Name: " + selectedPet.getName());
        System.out.println("2. Breed: " + selectedPet.getBreed());
        System.out.println("3. Age: " + selectedPet.getAge());
        System.out.print("Enter the number of the information you want to change (1-3): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                selectedPet.setName(newName);
                System.out.println("Name updated successfully.");
                break;
            case 2:
                System.out.print("Enter new breed: ");
                String newBreed = scanner.nextLine();
                selectedPet.setBreed(newBreed);
                System.out.println("Breed updated successfully.");
                break;
            case 3:
                System.out.print("Enter new age: ");
                int newAge = scanner.nextInt();
                selectedPet.setAge(newAge);
                System.out.println("Age updated successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

	// HealthRecord class
	class HealthRecord {
	    private List<String> vaccinations;
	    private List<String> medicalTreatments;

	    public HealthRecord(List<String> vaccinations, List<String> medicalTreatments) {
	        this.vaccinations = vaccinations;
	        this.medicalTreatments = medicalTreatments;
	    }

	    public List<String> getVaccinations() {
	        return vaccinations;
	    }

	    public void setVaccinations(List<String> vaccinations) {
	        this.vaccinations = vaccinations;
	    }

	    public List<String> getMedicalTreatments() {
	        return medicalTreatments;
	    }

	    public void setMedicalTreatments(List<String> medicalTreatments) {
	        this.medicalTreatments = medicalTreatments;
	    }
	}

	// Medication class
	class Medication {
	    private String name;
	    private String instructions;

	    public Medication(String name, String instructions) {
	        this.name = name;
	        this.instructions = instructions;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getInstructions() {
	        return instructions;
	    }

	    public void setInstructions(String instructions) {
	        this.instructions = instructions;
	    }
	}

	// CareProfile class
	class CareProfile {
	    private String feedingInstructions;
	    private List<Medication> medications;

	    public CareProfile(String feedingInstructions, List<Medication> medications) {
	        this.feedingInstructions = feedingInstructions;
	        this.medications = medications;
	    }

	    public String getFeedingInstructions() {
	        return feedingInstructions;
	    }

	    public void setFeedingInstructions(String feedingInstructions) {
	        this.feedingInstructions = feedingInstructions;
	    }

	    public List<Medication> getMedications() {
	        return medications;
	    }

	    public void setMedications(List<Medication> medications) {
	        this.medications = medications;
	    }
	}

	// Client class
	class Client {
	    private String name;
	    private String phone;
	    private String address;
	    private List<Pet> pets;
	    private List<Booking> bookings;
	    private List<Payment> payments;
	    private List<Notification> notifications;

	    public Client(String name, String phone, String address) {
	        this.name = name;
	        this.phone = phone;
	        this.address = address;
	        this.pets = new ArrayList<>();
	        this.bookings = new ArrayList<>();
	        this.payments = new ArrayList<>();
	        this.notifications = new ArrayList<>();
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public List<Pet> getPets() {
	        return pets;
	    }

	    public void setPets(List<Pet> pets) {
	        this.pets = pets;
	    }

	    public List<Booking> getBookings() {
	        return bookings;
	    }

	    public void setBookings(List<Booking> bookings) {
	        this.bookings = bookings;
	    }

	    public List<Payment> getPayments() {
	        return payments;
	    }

	    public void setPayments(List<Payment> payments) {
	        this.payments = payments;
	    }

	    public List<Notification> getNotifications() {
	        return notifications;
	    }

	    public void setNotifications(List<Notification> notifications) {
	        this.notifications = notifications;
	    }

	    public void addPet(Pet pet) {
	        this.pets.add(pet);
	    }

	    public void addBooking(Booking booking) {
	        this.bookings.add(booking);
	    }

	    public void addPayment(Payment payment) {
	        this.payments.add(payment);
	    }

	    public void addNotification(Notification notification) {
	        this.notifications.add(notification);
	    }
	}

	// Pet class
	class Pet {
	    private String name;
	    private String breed;
	    private int age;
	    private HealthRecord healthRecord;
	    private CareProfile careProfile;

	    public Pet(String name, String breed, int age, HealthRecord healthRecord, CareProfile careProfile) {
	        this.name = name;
	        this.breed = breed;
	        this.age = age;
	        this.healthRecord = healthRecord;
	        this.careProfile = careProfile;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getBreed() {
	        return breed;
	    }

	    public void setBreed(String breed) {
	        this.breed = breed;
	    }

	    public int getAge() {
	        return age;
	    }

	    public void setAge(int age) {
	        this.age = age;
	    }

	    public HealthRecord getHealthRecord() {
	        return healthRecord;
	    }

	    public void setHealthRecord(HealthRecord healthRecord) {
	        this.healthRecord = healthRecord;
	    }

	    public CareProfile getCareProfile() {
	        return careProfile;
	    }

	    public void setCareProfile(CareProfile careProfile) {
	        this.careProfile = careProfile;
	    }
	}

	// Booking class
	class Booking {
	    private Date startDate;
	    private Date endDate;
	    private Pet pet;
	    private Client client;

	    public Booking(Date startDate, Date endDate, Pet pet, Client client) {
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.pet = pet;
	        this.client = client;
	    }

	    public Date getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(Date startDate) {
	        this.startDate = startDate;
	    }

	    public Date getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(Date endDate) {
	        this.endDate = endDate;
	    }

	    public Pet getPet() {
	        return pet;
	    }

	    public void setPet(Pet pet) {
	        this.pet = pet;
	    }

	    public Client getClient() {
	        return client;
	    }

	    public void setClient(Client client) {
	        this.client = client;
	    }
	}

	// Payment class
	class Payment {
	    private double amount;
	    private Date date;
	    private Client client;

	    public Payment(double amount, Date date, Client client) {
	        this.amount = amount;
	        this.date = date;
	        this.client = client;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public Client getClient() {
	        return client;
	    }

	    public void setClient(Client client) {
	        this.client = client;
	    }
	}

	// Notification class
	class Notification {
	    private String message;
	    private Date sendDate;
	    private Client client;

	    public Notification(String message, Date sendDate, Client client) {
	        this.message = message;
	        this.sendDate = sendDate;
	        this.client = client;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public Date getSendDate() {
	        return sendDate;
	    }

	    public void setSendDate(Date sendDate) {
	        this.sendDate = sendDate;
	    }

	    public Client getClient() {
	        return client;
	    }

	    public void setClient(Client client) {
	        this.client = client;
	    }
}
