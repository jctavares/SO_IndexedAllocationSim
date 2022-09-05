package File;

import Simulation.DiskSimulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationFileBuffer {

    private static byte[] byteStream;
    private static byte[] filePointers;
    private static List<Byte> referencePointers = new ArrayList<>(); // referência dos ponteiros que já foram usados

    public static void streamBytesToDisk(DiskSimulation diskSim, File file) throws IOException {
        byteStream = Files.readAllBytes(file.toPath());
        diskSim.saveFileInDisk(byteStream, generatePointers(diskSim));
    }

    private static byte[] generatePointers(DiskSimulation diskSim) {
        filePointers = new byte[byteStream.length];
        for (int i = 0; i < filePointers.length; i++) {
            do {
                filePointers[i] = (byte) new Random().nextInt(diskSim.getDiskSize());
            } while (filePointers[i] < 0 || diskSim.getDiskSpace()[filePointers[i]] != 0 ||
            referencePointers.contains(filePointers[i]));
            referencePointers.add(filePointers[i]);
        }
        return filePointers;
    }

    public static byte[] getLatestSavedFilePointers() {
        byte[] result = filePointers;
        filePointers = new byte[0];
        return result;
    }
}
