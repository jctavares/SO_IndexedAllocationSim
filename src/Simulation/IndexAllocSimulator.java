package Simulation;

import File.FileTable;
import Utils.IndexBlock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndexAllocSimulator {

    private DiskSimulation diskSim;
    private FileTable fileTable;
    private byte[] diskBlocks;
    private List<IndexBlock> fileIndexes;
    private List<Byte> usedBlocks; // somente uma referência aos blocos que já foram ocupados com alguma coisa

    public IndexAllocSimulator(DiskSimulation diskSim) {
        this.diskSim = diskSim;
        this.diskBlocks = this.diskSim.populateDiskSpace(this.diskSim.getDiskSize());
        this.fileTable = new FileTable();
        this.fileIndexes = new ArrayList<>();
        this.usedBlocks = new ArrayList<>();
    }

    private void addFileToSimulation(File file) throws IOException {
        byte position; // vai definir a posição do bloco index do arquivo na memória
        do {
            position = (byte) new Random().nextInt(this.diskBlocks.length);
        } while (usedBlocks.contains(position));
        IndexBlock fileIndexBlock = new IndexBlock();
        fileIndexBlock.setFileInformation(file);
        fileIndexBlock.setPositionInMemory(position);
        this.usedBlocks.add(position);
        byte[] indexBlockReferencePointers = this.generatePointers(Files.readAllBytes(file.toPath()).length);
        fileIndexBlock.setFileAddressPointers(indexBlockReferencePointers);
        for (byte indexBlockReferencePointer : indexBlockReferencePointers) {
            this.usedBlocks.add(indexBlockReferencePointer);
        }
        this.fileTable.addFileToTable(fileIndexBlock, Files.readAllBytes(file.toPath()).length);
    }

    /**
     * Ao invés de chamar o método acima, é melhor chamar esse único daqui que aceita
     * qualquer quantidade de parâmetros de uma só vez
     * @param files
     * @throws IOException
     */
    public void addFileToSimulation(File... files) throws IOException {
        for (File fileToAdd : files) {
            this.addFileToSimulation(fileToAdd);
        }
    }

    private byte[] generatePointers(int size) {
        byte[] result = new byte[size];
        byte pointerAddress = -1;
        for (int i = 0; i < size; i++) {
            do {
                pointerAddress = (byte) new Random().nextInt(this.diskSim.getDiskSize());
            } while (this.usedBlocks.contains(pointerAddress));
            result[i] = pointerAddress;
            this.usedBlocks.add(pointerAddress);
        }
        return result;
    }

    public void displayTable() {
        this.fileTable.displayFileTableItems();
    }
}
