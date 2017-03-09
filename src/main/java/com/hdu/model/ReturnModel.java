package com.hdu.model;


import com.hdu.model.Message;
import com.hdu.model.ResultModel;
import com.hdu.model.ObjectModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnModel<T> extends ObjectModelImpl<T> implements ResultModel<T>,Message<T>{

    private int code=200;
    private String id;
    private String message=null;
    private Boolean success;

    private List<Map> errors=new ArrayList<>();

    private Map<String,Object> dto=new HashMap();

    public ReturnModel(){}

    public ReturnModel(String message){
        this.message=message;
    }

    public ReturnModel(int code, String message){
        this.code=code;
        this.message=message;
    }

    public ReturnModel(int code, String message, T obj){
        this.code=code;
        this.message=message;
        super.setObj(obj);
    }
    @Override
    public boolean getSuccess() {
        this.setSuccess((null==this.success)?((this.getCode()==200 || this.getCode()==0)?true:false):this.success);
        return this.success;
    }

    @Override
    public ReturnModel setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    @Override
    public List<Map> getErrors() {
        return errors;
    }

    @Override
    public Message toMessage() {
        return this;
    }

    @Override
    public ReturnModel setErrors(List<Map> errors) {
        this.errors = errors;
        return this;
    }

    @Override
    public ReturnModel setErrors(String id,String msg) {
        Map<String,String> field=new HashMap<>(2);
        field.put("id",id);
        field.put("msg",msg);
        this.getErrors().add(field);
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public ReturnModel setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public ReturnModel setCode(int code) {
        this.code=code;
        return this;
    }

    @Override
    public ReturnModel setMessage(String message) {
        this.message=message;
        return this;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public ReturnModel setObj(T obj) {
        super.setObj(obj);
        return this;
    }

    @Override
    public Object getDTO(String name){
        return dto.get(name);
    }

    public ReturnModel setDTO(String name,Object value) {
        dto.put(name,value);
        return this;
    }

	@Override
	public T getObj() {
		// TODO Auto-generated method stub
		return null;
	}


}
