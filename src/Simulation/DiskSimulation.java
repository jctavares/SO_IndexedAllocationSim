package Simulation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class DiskSimulation {

    private byte[] diskSpace;
    private final int MEM_SIM_SIZE = 2048;

    public DiskSimulation() {
        this.diskSpace = new byte[MEM_SIM_SIZE];
    }

    public DiskSimulation(int diskSize) {
        this.diskSpace = new byte[MEM_SIM_SIZE];
    }

    public int getDiskSize() {
        return this.diskSpace.length;
    }

    public byte[] getDiskSpace() {
        return this.diskSpace;
    }

    public void saveFileInDisk(byte[] fileContents, byte[] filePointers) {
        for (int i = 0; i < fileContents.length; i++) {
            byte pointer = filePointers[i];
            this.diskSpace[pointer] = fileContents[i];
        }
    }

    public void readFromDisk(byte[] contents, String pathname) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(pathname)) {
            for (int i = 0; i < contents.length; i++) {
                fos.write(contents[i]);
            }
        }
    }
}
