package com.hdu.model;
import java.util.List;
import java.util.Map;

public interface ResultModel<T> extends ObjectModel<T>{

    public int getCode();
    public String getId();
    public boolean getSuccess();
    public String getMessage();
    public List<Map> getErrors();
    public Message toMessage();
    public Object getDTO(String name);

}
