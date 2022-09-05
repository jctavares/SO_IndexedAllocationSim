package Utils;

import java.io.File;
import java.util.ArrayList;

public class IndexBlock {

    private int positionInMemory;
    private String referenceFileName;
    private String referenceFilePath;
    private byte[] fileAddressPointers;

    public IndexBlock() {
        this.positionInMemory = -1;
        this.referenceFileName = "";
        this.referenceFilePath = "root";
    }

    public int getPositionInMemory() {
        return positionInMemory;
    }

    public void setPositionInMemory(int positionInMemory) {
        this.positionInMemory = positionInMemory;
    }

    public String getReferenceFileName() {
        return referenceFileName;
    }

    public void setFileInformation(File file) {
        this.referenceFileName = file.getName();
        this.referenceFilePath = file.getAbsolutePath();
    }

    public void setReferenceFileName(File file) {
        this.referenceFileName = file.getName();
    }

    public byte[] getFileAddressPointers() {
        return fileAddressPointers;
    }

    public void setFileAddressPointers(byte[] fileAddressPointers) {
        this.fileAddressPointers = fileAddressPointers;
    }
}
