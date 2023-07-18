import java.util.List;

/**
 * This class is used to interface with the blockchain.
 * Abstraction layer between the blockchain and the user.
 */
public class BlockchainInterface {
    private final Blockchain blockchain;

    public BlockchainInterface() {
        this.blockchain = new Blockchain();
    }

    public void addBlock(String[] transactions) {
        blockchain.addBlock(transactions);
    }

    public void refreshCurrentBlock() {
        blockchain.refreshCurrentBlock();
    }
    public void editBlock(int blockNumber, String[] newTransactions) {
        blockchain.editBlock(blockNumber, newTransactions);
    }

    public void displayBlock(int blockNumber) {
        blockchain.displayBlock(blockNumber);
    }

    public List<Block> displayBlockchain() {
        return blockchain.displayBlockchain();
    }

    public boolean isValidBlockNumber(int blockNumber) {
        return blockchain.isValidBlockNumber(blockNumber);
    }
}
