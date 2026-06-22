
import java.util.*;

// [OOPS Concept: Class & Object] - Template for a Menu Item
class MenuItem {
    private String name;
    private double price;
    private String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // [OOPS Concept: Encapsulation]
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}

// [OOPS Concept: Composition] - Linking Item with its Quantity
class OrderItem {
    MenuItem item;
    int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

public class Main {
    private static List<MenuItem> menu = new ArrayList<>();
    private static List<OrderItem> currentOrder = new ArrayList<>();
    private static double lastBillAmount = 0; 
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMenu();
        System.out.println("====================================================");
        System.out.println("   ✨ PARVEZ YUM YARD RESTAURANT SYSTEM ✨");
        System.out.println("====================================================");
        System.out.println("Members: Parvez, Srivalli, Ruthvik, Rajvarsh");

        boolean running = true;
        while (running) {
            try {
                System.out.println("\n[1] View Menu  [2] Place Order  [3] Generate Bill  [4] Pay Now  [5] Exit");
                System.out.print("Select Option: ");
                
                String input = scanner.nextLine(); 
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1: displayMenu(); break;
                    case 2: takeOrder(); break;
                    case 3: generateBill(); break;
                    case 4: processPayment(); break;
                    case 5: 
                        if (lastBillAmount > 0) {
                            System.out.println("⚠️ Warning: You have a pending payment of Rs. " + lastBillAmount);
                        } else {
                            printExitBanner();
                            running = false;
                        }
                        break;
                    default: System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    private static void initializeMenu() {
        menu.add(new MenuItem("Veg Manchurian", 150.0, "Starter"));
        menu.add(new MenuItem("Chicken 65", 220.0, "Starter"));
        menu.add(new MenuItem("French Fries", 100.0, "Snacks"));
        menu.add(new MenuItem("Veg Burger", 120.0, "Fast Food"));
        menu.add(new MenuItem("Chicken Pizza", 280.0, "Fast Food"));
        menu.add(new MenuItem("Chicken Biryani", 350.0, "Main Course"));
        menu.add(new MenuItem("Paneer Butter Masala", 240.0, "Main Course"));
        menu.add(new MenuItem("Veg Fried Rice", 180.0, "Chinese"));
        menu.add(new MenuItem("Hakka Noodles", 170.0, "Chinese"));
        menu.add(new MenuItem("Soft Drink", 50.0, "Beverage"));
        menu.add(new MenuItem("Chocolate Milkshake", 120.0, "Beverage"));
        menu.add(new MenuItem("Ice Cream", 80.0, "Dessert"));
    }

    private static void displayMenu() {
        System.out.println("\n------------------ DIGITAL MENU ------------------");
        System.out.printf("%-4s %-22s %-15s %-10s\n", "ID", "Item Name", "Category", "Price");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < menu.size(); i++) {
            MenuItem m = menu.get(i);
            System.out.printf("%-4d %-22s %-15s %.2f\n", (i + 1), m.getName(), m.getCategory(), m.getPrice());
        }
    }

    private static void takeOrder() {
        displayMenu();
        try {
            System.out.print("\nEnter Item ID to add: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            if (id > 0 && id <= menu.size()) {
                System.out.print("Enter Quantity: ");
                int qty = Integer.parseInt(scanner.nextLine());
                
                if (qty > 0) {
                    currentOrder.add(new OrderItem(menu.get(id - 1), qty));
                    System.out.println("✅ SUCCESS: " + qty + " x " + menu.get(id - 1).getName() + " added!");
                } else {
                    System.out.println("❌ Quantity must be at least 1.");
                }
            } else {
                System.out.println("❌ Invalid Item ID.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: Use numbers only.");
        }
    }

    private static void generateBill() {
        if (currentOrder.isEmpty()) {
            System.out.println("Cart is empty! Place an order first.");
            return;
        }
        
        double total = 0;
        System.out.println("\n========== TEAM 09 FINAL RECEIPT ==========");
        System.out.printf("%-20s %-10s %-8s %-10s\n", "Item", "Rate", "Qty", "Subtotal");
        System.out.println("-------------------------------------------");
        
        for (OrderItem oi : currentOrder) {
            double lineTotal = oi.item.getPrice() * oi.quantity;
            System.out.printf("%-20s %-10.2f %-8d %-10.2f\n", 
                oi.item.getName(), oi.item.getPrice(), oi.quantity, lineTotal);
            total += lineTotal;
        }
        
        System.out.println("-------------------------------------------");
        System.out.printf("GRAND TOTAL:                   Rs. %.2f\n", total);
        System.out.println("===========================================");
        lastBillAmount = total; 
    }

    private static void processPayment() {
        if (lastBillAmount == 0) {
            System.out.println("No pending bill! Please generate a bill first.");
            return;
        }

        System.out.println("\nProcessing Payment of Rs. " + lastBillAmount + "...");
        System.out.println("Select Payment Mode: [1] Cash [2] UPI/Card");
        System.out.print("Choice: ");
        scanner.nextLine(); // Consuming choice
        
        System.out.println("\n✅ PAYMENT SUCCESSFUL! ✅");
        System.out.println("Transaction ID: " + (1000 + new Random().nextInt(9000)));
        System.out.println("Thank you for the payment. Enjoy your meal! 🍽️");
        
        // Resetting for next order
        currentOrder.clear();
        lastBillAmount = 0;
    }

    private static void printExitBanner() {
        System.out.println("\n******************");
        System.out.println("* *");
        System.out.println("* THANK YOU FOR VISITING PARVEZ YUM YARD!        *");
        System.out.println("* VISIT US AGAIN SOON! 😊🙏✨               *");
        System.out.println("* Have a Great Day! See you soon! 🍔            *");
        System.out.println("* *");
        System.out.println("******************");
    }
} 
