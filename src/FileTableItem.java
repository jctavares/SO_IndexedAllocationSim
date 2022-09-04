package File;

public class FileTableItem {

    private String fileName;
    private int indexBlockPosition;
    private int numberOfAllocatedBlocks;

    public FileTableItem(String fileName, int indexBlockPosition, int numberOfAllocatedBlocks) {
        this.fileName = fileName;
        this.indexBlockPosition = indexBlockPosition;
        this.numberOfAllocatedBlocks = numberOfAllocatedBlocks;
    }

    public FileTableItem() {
        this.fileName = null;
        this.indexBlockPosition = -1;
        this.numberOfAllocatedBlocks = 0;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIndexBlockPosition() {
        return indexBlockPosition;
    }

    public void setIndexBlockPosition(int indexBlockPosition) {
        this.indexBlockPosition = indexBlockPosition;
    }

    public int getNumberOfAllocatedBlocks() {
        return numberOfAllocatedBlocks;
    }

    public void setNumberOfAllocatedBlocks(int numberOfAllocatedBlocks) {
        this.numberOfAllocatedBlocks = numberOfAllocatedBlocks;
    }
}
