package Simulation;

import java.util.ArrayList;
import java.util.Arrays;

public class DiskSimulation {

    private int diskSize;
    private final int MEM_SIM_SIZE = 2048;
    private ArrayList<byte[]> filesInDisk;

    public DiskSimulation() {
        this.diskSize = this.MEM_SIM_SIZE;
        this.filesInDisk = new ArrayList<>();
    }

    public DiskSimulation(int diskSize) {
        this.diskSize = diskSize;
    }

    public int getDiskSize() {
        return this.diskSize;
    }

    public void setDiskSize(int size) {
        this.diskSize = size;
    }

    private void addFileToDisk(byte[] fileContents) {
        this.filesInDisk.add(fileContents);
    }

    public void saveFileInDisk(byte[]... fileContents) {
        for (byte[] contents : fileContents) {
            this.addFileToDisk(contents);
        }
    }

    public byte[] retrieveFileContentsFromDisk(byte[] contents) {
        byte[] fileContent = new byte[0];
        for (byte[] byteStream : filesInDisk) {
            if (Arrays.equals(byteStream, contents)) {
                fileContent = contents;
                break;
            }
        }
        return fileContent;
    }
}
