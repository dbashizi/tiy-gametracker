package com.tiy.web;

import javax.persistence.*;

/**
 * Created by dbashizi on 1/26/17.
 */
@Entity
@Table(name = "fileasstring")
public class FileAsString {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = true)
    private String fileString;
    @Column(nullable = true)
    private byte[] fileBytes;
    @Column(nullable = true)
    private String fileName;

    public FileAsString() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fileName = " + fileName);
        if (fileString != null) {
            sb.append("File Size (str): " + fileString.length());
        }
        if (fileBytes != null) {
            sb.append("File Size (bytes): " + fileBytes.length);
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
