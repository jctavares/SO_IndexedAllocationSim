package File;

import Utils.IndexBlock;

import java.util.ArrayList;

public class FileTable {

    private ArrayList<FileTableItem> fileTableItem;

    public FileTable() {
        this.fileTableItem = new ArrayList<>();
    }

    public void displayFileTableItems() {
        for (FileTableItem fileInfo : fileTableItem) {
            System.out.println("[Nome: " + fileInfo.getFileName() +  "] [Bloco Index: " + fileInfo.getIndexBlockPosition() +
                    "] [Qtd. de Blocos Alocados: " + fileInfo.getNumberOfAllocatedBlocks() + "]");
        }
    }

    public void addFileToTable(IndexBlock indexBlock) {
        FileTableItem infoToAdd = new FileTableItem();
        infoToAdd.setIndexBlock(indexBlock);
        infoToAdd.setFileName(indexBlock.getReferenceFileName());
        infoToAdd.setNumberOfAllocatedBlocks(indexBlock.getFileAddressPointers().length);
        this.fileTableItem.add(infoToAdd);
    }

    public boolean hasItemInTable(String fileName) {
        boolean result = false;
        for (FileTableItem fileInfo : fileTableItem) {
            if (fileInfo.getFileName().equals(fileName)) {
                result = true;
            }
        }
        return result;
    }

    public FileTableItem getFileTableItem(String fileName) {
        for (FileTableItem fileInfo : fileTableItem) {
            if (fileInfo.getFileName().equals(fileName)) {
                return fileInfo;
            }
        }
        return null;
    }
}
