package Simulation;

import java.util.Random;

public class DiskSimulation {

    private int diskSize;
    private final int MEM_SIM_SIZE = 64;

    public DiskSimulation() {
        this.diskSize = new Random().nextInt(this.MEM_SIM_SIZE);
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

    public byte[] populateDiskSpace(int size) {
        byte[] result = new byte[size];
        for (byte i = 0; i < size; i++) {
            result[i] = i;
        }
        return result;
    }
}
