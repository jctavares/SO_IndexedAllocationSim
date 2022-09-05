import Simulation.IndexAllocSimulator;
import Simulation.DiskSimulation;

import java.io.File;
import java.io.IOException;

public class StartSim {

    public static void main(String[] args) throws IOException {
        DiskSimulation diskSim = new DiskSimulation();
        IndexAllocSimulator idx = new IndexAllocSimulator(diskSim);
        File file = new File("src/open.txt");
        File file2 = new File("src/open2.txt");
        idx.addFileToSimulation(file);
        idx.addFileToSimulation(file2);
        idx.readFileFromSimulation("open2.txt");
        idx.displayTable();
    }
}
