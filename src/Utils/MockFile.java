package Utils;

import Utils.IndexBlock;

import java.io.File;
import java.util.Random;

/**
 * Classe utilizada pra receber de fato o arquivo, e só disponibilizar o
 * array de bytes pra que a gente possa fazer as operações na table.
 *
 */
public class MockFile {

    private String fileName;
    private long fileSize;
    private byte[] fileContents;
    private IndexBlock fileIndexBlockl;

    public MockFile(File file) {
        this.fileName = file.getName();
        this.fileSize = file.getTotalSpace();
    }
}
