package File;

import Utils.IndexBlock;

public class FileTableItem {

    private String fileName;
    private int numberOfAllocatedBlocks;
    private IndexBlock indexBlock;

    public FileTableItem(String fileName, int numberOfAllocatedBlocks) {
        this.fileName = fileName;
        this.numberOfAllocatedBlocks = numberOfAllocatedBlocks;
    }

    public FileTableItem() {
        this.fileName = null;
        this.numberOfAllocatedBlocks = 0;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIndexBlockPosition() {
        return indexBlock.getPositionInMemory();
    }

    public int getNumberOfAllocatedBlocks() {
        return numberOfAllocatedBlocks;
    }

    public void setNumberOfAllocatedBlocks(int numberOfAllocatedBlocks) {
        this.numberOfAllocatedBlocks = numberOfAllocatedBlocks;
    }

    public void setIndexBlock(IndexBlock indexBlock) {
        this.indexBlock = indexBlock;
    }

    public IndexBlock getIndexBlock() {
        return this.indexBlock;
    }
}
