package com.wust.entity;

public class NewsType {
	private int typeId;
	private String typename;
	private String typedescription;
	



public NewsType(String typename, String typedescription) {
	super();
	this.typename = typename;
	this.typedescription = typedescription;
    }


public NewsType() {
}


public int getTypeId() {
	return typeId;
}


public void setTypeId(int typeId) {
	this.typeId = typeId;
}


public String getTypename() {
	return typename;
}


public void setTypename(String typename) {
	this.typename = typename;
}


public String getTypedescription() {
	return typedescription;
}


public void setTypedescription(String typedescription) {
	this.typedescription = typedescription;
}

}