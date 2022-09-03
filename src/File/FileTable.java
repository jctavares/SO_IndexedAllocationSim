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
            System.out.println("Nome: " + fileInfo.getFileName() +  " Bloco Index: " + fileInfo.getIndexBlockPosition() +
                    " Qtd. de Blocos Alocados: " + fileInfo.getNumberOfAllocatedBlocks());
        }
    }

    public void addFileToTable(IndexBlock indexBlock, int numberOfAllocatedBlocks) {
        FileTableItem infoToAdd = new FileTableItem();
        infoToAdd.setFileName(indexBlock.getReferenceFileName());
        infoToAdd.setIndexBlockPosition(indexBlock.getPositionInMemory());
        infoToAdd.setNumberOfAllocatedBlocks(numberOfAllocatedBlocks);
        this.fileTableItem.add(infoToAdd);
    }
}
