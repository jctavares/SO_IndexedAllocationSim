import Simulation.IndexAllocSimulator;
import Simulation.DiskSimulation;
import File.SimulationFileBuffer;

import java.io.File;
import java.io.IOException;

public class StartSim {

    public static void main(String[] args) throws IOException {
        DiskSimulation diskSim = new DiskSimulation();
        IndexAllocSimulator idx = new IndexAllocSimulator(diskSim);
        File file = new File("src/open.txt");
        File secondFile = new File("src/open2.txt");
        SimulationFileBuffer.streamBytesToDisk(diskSim, file, secondFile);
        idx.addFileToSimulation(file, secondFile);
        idx.displayTable();
    }
}
