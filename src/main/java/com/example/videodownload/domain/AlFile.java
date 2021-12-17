package com.example.videodownload.domain;


/**
 * AlFile对象 al_file
 * 
 * @author test
 * @date 2021-04-23
 */
public class AlFile
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String fileUrl;

    private String qrCodeUrl;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }
    public void setQrCodeUrl(String qrCodeUrl) 
    {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getQrCodeUrl() 
    {
        return qrCodeUrl;
    }

}
