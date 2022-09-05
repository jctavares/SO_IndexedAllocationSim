package Simulation;

import File.FileTable;
import Utils.IndexBlock;
import File.SimulationFileBuffer;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class IndexAllocSimulator {

    private DiskSimulation diskSim;
    private FileTable fileTable;

    public IndexAllocSimulator(DiskSimulation diskSim) {
        this.diskSim = diskSim;
        this.fileTable = new FileTable();
    }

    /**
     * Vai checar se o arquivo está presente no {@link DiskSimulation}, caso sim,
     * utiliza-se da table {@link FileTable} para inciar o processo da simulação
     * da alocação indexada.
     * @param file
     * @throws IOException
     */
    private void addFileToSimulation(File file) throws IOException {
        byte position; // posição do bloco index
        do {
            position = (byte) new Random().nextInt(this.diskSim.getDiskSize());
        } while (position < 0 || this.diskSim.getDiskSpace()[position] != 0);
        IndexBlock fileIndexBlock = new IndexBlock();
        fileIndexBlock.setFileInformation(file);
        fileIndexBlock.setPositionInMemory(position);
        SimulationFileBuffer.streamBytesToDisk(this.diskSim, file);
        fileIndexBlock.setFileAddressPointers(SimulationFileBuffer.getLatestSavedFilePointers());
        this.fileTable.addFileToTable(fileIndexBlock);
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

    public void readFileFromSimulation(String fileName) throws IOException {
        if (this.fileTable.hasItemInTable(fileName)) {
            IndexBlock fileIndexBlock = this.fileTable.getFileTableItem(fileName).getIndexBlock();
            byte[] fileContents = new byte[fileIndexBlock.getFileAddressPointers().length];
            for (int i = 0; i <  fileIndexBlock.getFileAddressPointers().length; i++) {
                fileContents[i] = this.diskSim.getDiskSpace()[fileIndexBlock.getFileAddressPointers()[i]];
            }
            this.diskSim.readFromDisk(fileContents, fileName);
        }
    }

    public void displayTable() {
        this.fileTable.displayFileTableItems();
    }
}
