package Simulation;

import File.FileTable;
import Utils.IndexBlock;
import File.SimulationFileBuffer;

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
        this.diskBlocks = new byte[this.diskSim.getDiskSize()];
        this.fileTable = new FileTable();
        this.fileIndexes = new ArrayList<>();
        this.usedBlocks = new ArrayList<>();
    }

    /**
     * Vai checar se o arquivo está presente no {@link DiskSimulation}, caso sim,
     * utiliza-se da table {@link FileTable} para inciar o processo da simulação
     * da alocação indexada.
     * @param file
     * @throws IOException
     */
    private void addFileToSimulation(File file) throws IOException {
        byte position;
        do {
            position = (byte) new Random().nextInt(this.diskBlocks.length);
        } while (usedBlocks.contains(position));
        IndexBlock fileIndexBlock = new IndexBlock();
        fileIndexBlock.setFileInformation(file);
        fileIndexBlock.setPositionInMemory(position);
        this.usedBlocks.add(position);
        SimulationFileBuffer.streamBytesToDisk(this.diskSim, file);
        int fileLength = this.diskSim.retrieveFileContentsFromDisk(Files.readAllBytes(file.toPath())).length; // só funciona se o arquivo tiver sido salvo em disco previamente
        byte[] indexedBlockReferencePointers = this.generatePointers(fileLength);
        for (byte indexBlockReferencePointer : indexedBlockReferencePointers) {
            this.usedBlocks.add(indexBlockReferencePointer);
        }
        fileIndexBlock.setFileAddressPointers(indexedBlockReferencePointers);
        this.fileTable.addFileToTable(fileIndexBlock, fileLength);
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

    /**
     * Gerador dos pointeiros que irão "armazenar" informações do arquivo
     * @param size
     * @return
     */
    private byte[] generatePointers(int size) {
        byte[] result = new byte[size];
        byte pointerAddress;
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
