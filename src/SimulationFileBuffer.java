package File;

import Simulation.DiskSimulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SimulationFileBuffer {

    /**
     * Faz o processo de transformar 1 ou mais arquivos
     * @param diskSim O disco simulado onde o arquivo vai ser salvo
     * @param file O(s) arquivos {@link File} em questão
     * @throws IOException
     */
    public static void streamBytesToDisk(DiskSimulation diskSim, File... file) throws IOException {
        for (File fileToStream : file) {
            byte[] byteStream = Files.readAllBytes(fileToStream.toPath());
            diskSim.saveFileInDisk(byteStream);
        }
    }
}
