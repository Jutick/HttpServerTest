package com.company;

public class HttpResponseStatus
{
    private String status;
    private String description;

    public  HttpResponseStatus() {}

    public HttpResponseStatus(String status, String description)
    {
        this.status = status;
        this.description = description;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
}
