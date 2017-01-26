package com.tiy.web;

/**
 * Created by dbashizi on 1/26/17.
 */
public class FileAsString {

    private String fileString;
    private byte[] fileBytes;
    private String fileName;

    public FileAsString() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

}
