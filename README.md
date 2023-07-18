# Simple Blockchain Demo in Java

## Overview

This is a basic Java application that simulates a blockchain. You can interact with the blockchain by adding blocks, editing blocks, and viewing blocks or the entire blockchain.

## Running the Application

1. Make sure that you have a Java Runtime Environment (JRE) installed on your machine.

2. Download the Java files for the application and place them in the same directory.

3. Open a terminal window and navigate to the directory containing the Java files.

4. Compile the Java files by typing `javac Main.java` and pressing Enter.

5. Run the application by typing `java Main` and pressing Enter.

## Using the Application

The application will display a menu with the following options:

1. Add a block
2. Display a block
3. Edit a block
4. Display the whole blockchain
5. Exit

Enter the number corresponding to the action you want to perform and press Enter. Follow the prompts to complete the action. For example, if you choose to add a block, you will be asked to enter the sender's name, recipient's name, and the amount sent. You can add multiple transactions to a block before submitting it.

## Classes Structure

- **Block**: This class represents a block in the blockchain. Each block includes the previous block's hash, a list of transactions, and its own hash. The block's hash is calculated based on the transactions and the previous hash.

- **Blockchain**: This class represents the blockchain itself. It's a list of blocks, implemented as an ArrayList. When a new block is added to the blockchain, the hash of the last block in the blockchain is provided as the previous hash for the new block.

- **BlockchainInterface**: This class acts as an intermediary between the user and the blockchain. It contains methods that allow the user to interact with the blockchain, such as adding blocks, editing blocks, and viewing blocks or the whole blockchain. It uses a Blockchain object to perform these actions.

- **Main**: This class is the entry point of the application. It contains the main method that runs when the application is started. It uses BlockchainInterface to interact with the blockchain based on the user's choices.


Enjoy interacting with my simple demo blockchain!
