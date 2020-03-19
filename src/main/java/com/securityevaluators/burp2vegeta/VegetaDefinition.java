package com.securityevaluators.burp2vegeta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class VegetaDefinition {

    @SerializedName("body")
    @JsonAdapter(ByteArrayToBase64TypeAdapter.class)
    @Expose
    private byte[] body;
    @SerializedName("header")
    @Expose
    private Map<String, List<String>> headerCollection;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("method")
    @Expose
    private String method;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VegetaDefinition() {
    }

    /**
     * 
     * @param method
     * @param headerCollection
     * @param body
     * @param url
     */
    public VegetaDefinition(byte[] body, Map<String, List<String>> headerCollection, String method, String url) {
        super();
        this.body = body;
        this.headerCollection = headerCollection;
        this.method = method;
        this.url = url;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public VegetaDefinition withBody(byte[] body) {
        this.body = body;
        return this;
    }

    public Map<String, List<String>> getHeaderCollection() {
        return headerCollection;
    }

    public void setHeaderCollection(Map<String, List<String>> headerCollection) {
        this.headerCollection = headerCollection;
    }

    public VegetaDefinition withHeader(Map<String, List<String>> headerCollection) {
        this.headerCollection = headerCollection;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getMethod() {
        return method;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setMethod(String method) {
        this.method = method;
    }

    public VegetaDefinition withMethod(String method) {
        this.method = method;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public VegetaDefinition withUrl(String url) {
        this.url = url;
        return this;
    }

}
