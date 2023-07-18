import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static BlockchainInterface blockchainInterface = new BlockchainInterface();

    public static void main(String[] args) {
        int choice;

        do {
            // Print the menu
            displayMenu();

            // If the user enters something that is not an integer, ask them to enter an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }

            choice = scanner.nextInt();
            switch (choice) {
                // Add a block
                case 1:
                    addBlock();
                    break;
                // Display a block
                case 2:
                    displayBlock();
                    break;
                // Edit a block
                case 3:
                    editBlock();
                    break;
                // Display the whole blockchain
                case 4:
                    displayBlockchain();
                    break;
                // Exit
                case 5:
                    System.out.println("Thank you for using my blockchain! Goodbye!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please pick a number between 1 and 5.");
                    break;
            }
        } while (choice != 5);
    }


    private static void displayMenu() {
        System.out.println("\nWelcome to the blockchainInterface! What would you like to do?");
        System.out.println("1. Add a block");
        System.out.println("2. Display a block");
        System.out.println("3. Edit a block");
        System.out.println("4. Display the whole blockchain");
        System.out.println("5. Exit");
    }

    private static void addBlock() {
        System.out.println("You chose to add a block");
        List<String> transactions = new ArrayList<>();
        String input;

        do {
            System.out.println("Please enter the Sender's name:");
            String sender = scanner.next();
            System.out.println("Please enter the Recipient's name:");
            String recipient = scanner.next();
            System.out.println("Please enter the amount sent: ");
            int amount = scanner.nextInt();

            // Add the transaction to the list of transactions
            transactions.add(sender + " pays " + amount + " BTC to " + recipient);

            System.out.println("Would you like to add another transaction? (y/n)");
            input = scanner.next();
        } while (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"));

        do {
            System.out.println("Do you want to Refresh the hash or Submit the block? (refresh/submit)");
            input = scanner.next();

            if (input.equalsIgnoreCase("refresh")) {
                blockchainInterface.refreshCurrentBlock(); // recalculates hash of current block based on its transactions
            } else if (!input.equalsIgnoreCase("submit")) {
                System.out.println("Invalid option, please enter 'refresh' or 'submit'");
            }
        } while (!input.equalsIgnoreCase("submit"));

        blockchainInterface.addBlock(transactions.toArray(new String[transactions.size()]));
        System.out.println("Block added successfully!");
    }

    private static void displayBlock() {
        if (blockchainInterface.displayBlockchain().isEmpty()) {
            System.out.println("Blockchain is empty");
            return;
        }
        System.out.println("You chose to display a block");
        while (true) {
            System.out.println("Please enter the block number you want to display:");
            int blockNumber = scanner.nextInt();
            if (blockchainInterface.isValidBlockNumber(blockNumber)) {
                blockchainInterface.displayBlock(blockNumber);
                break;
            }
        }
    }

    private static void editBlock() {
        if(blockchainInterface.displayBlockchain().isEmpty()) {
            System.out.println("Blockchain is empty");
            return;
        }
        System.out.println("You chose to edit a block");
        while (true) {
            System.out.println("Please enter the block number you want to edit:");
            int blockNumber = scanner.nextInt();

            if (blockchainInterface.isValidBlockNumber(blockNumber)) {
                List<String> newTransactions = new ArrayList<>();
                String input;
                do {
                    System.out.println("Please enter the Sender's name:");
                    String sender = scanner.next();
                    System.out.println("Please enter the Recipient's name:");
                    String recipient = scanner.next();
                    System.out.println("Please enter the amount sent: ");
                    int amount = scanner.nextInt();

                    // Add the transaction to the list of transactions
                    newTransactions.add(sender + " pays " + amount + " BTC to " + recipient);

                    System.out.println("Would you like to add another transaction? (y?)");
                    input = scanner.next();
                } while (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"));

                // Set the new transactions and refresh the block's hash
                blockchainInterface.editBlock(blockNumber, newTransactions.toArray(new String[newTransactions.size()]));
                break;
            }
        }
    }


    private static void displayBlockchain() {
        System.out.println("You chose to display the whole blockchain");
        List<Block> blockchain = blockchainInterface.displayBlockchain();

        if(blockchain.isEmpty()) {
            System.out.println("Blockchain is empty");
            return;
        }

        for(Block blocks : blockchain) {
            System.out.println("\nBlock: " + (blockchain.indexOf(blocks) + 1));
            System.out.println("\tPrevious hash: " + (blocks.getPreviousHash() == 0 ? "Genesis block, no previous hash" : blocks.getPreviousHash()));
            System.out.println("\tCurrent hash: " + blocks.getBlockHash());
            System.out.println("\tTransactions: " + String.join("; ", blocks.getTransactions()));
        }
    }
}