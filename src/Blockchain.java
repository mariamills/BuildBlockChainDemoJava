import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the blockchain itself.
 * It is a list of blocks.
 */

public class Blockchain {
    private List<Block> blockchain;

    public Blockchain() {
        this.blockchain = new ArrayList<>();
    }

    public void addBlock(String[] transactions) {
        if(blockchain.isEmpty()) {
            // Genesis block
            Block block = new Block(0, transactions);
            blockchain.add(block);
        } else {
            Block block = new Block(blockchain.get(blockchain.size() - 1).getBlockHash(), transactions);
            blockchain.add(block);
        }
    }

    public void editBlock(int blockNumber, String[] newTransactions) {
        if (isValidBlockNumber(blockNumber)) {
            blockNumber--;
            Block block = blockchain.get(blockNumber);
            block.setTransactions(newTransactions);
            block.refreshHash();
        }
    }


    public void displayBlock(int blockNumber) {
        if(isValidBlockNumber(blockNumber)) {
            blockNumber--; // block numbers start at 1, but the list starts at 0
            Block block = blockchain.get(blockNumber);
            System.out.println("Block: " + (blockNumber + 1));
            System.out.println("\tPrevious hash: " + (block.getPreviousHash() == 0 ? "Genesis block, no previous hash" : block.getPreviousHash()));
            System.out.println("\tCurrent hash: " + (block.getBlockHash()));
            System.out.println("\tTransactions: " + String.join("; ", block.getTransactions()));
        }
    }

    public void refreshCurrentBlock() {
        Block currentBlock = blockchain.get(blockchain.size() - 1);
        currentBlock.refreshHash();
    }

    public List<Block> displayBlockchain() {
        return blockchain;
    }

    public boolean isValidBlockNumber(int blockNumber) {
        if (blockNumber < 1 || blockNumber > blockchain.size()) {
            System.out.println("The block does not exist in this Blockchain");
            return false;
        } else {
            return true;
        }
    }
}
