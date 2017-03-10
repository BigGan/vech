package com.hdu.model;

public class ObjectModelImpl<T> implements ObjectModel<T>{
    
	private T obj=null;
	
	@Override
	public T getObj() {
		// TODO Auto-generated method stub
		return obj;
	}

	@Override
	public Model setObj(T obj) {
		 this.obj=obj;
	     return this;
	}

}
