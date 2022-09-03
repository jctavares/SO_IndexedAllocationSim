import Simulation.IndexAllocSimulator;
import Simulation.DiskSimulation;

import java.io.File;
import java.io.IOException;

public class StartSim {

    public static void main(String[] args) throws IOException {

        DiskSimulation diskSim = new DiskSimulation();
        IndexAllocSimulator idx = new IndexAllocSimulator(diskSim);
        File file = new File("src/open.txt");
        idx.addFileToSimulation(file);
        idx.displayTable();
    }
}
