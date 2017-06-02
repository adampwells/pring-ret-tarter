package com.starter.model.document;

import com.starter.dto.DocumentType;
import com.starter.model.AbstractEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by adam.wells on 5/06/2016.
 */
@Audited
@Entity
public class Document extends AbstractEntity {

    @Column(columnDefinition = "bytea")
    private byte[] fileData;

    @Column(columnDefinition = "TEXT")
    private String fileName;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType = DocumentType.IMAGE;

    public byte[] getFileData() {
        return fileData;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
