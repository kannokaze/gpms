package com.gpms.util;
 
import java.util.List;
 
/*
 * ����JSON������ListObject
 */
public class ListObject extends AbstractJSON {
 
    private List<?> data;                       // �б����
 
	public List<?> getData() {
		return data;
	}
 
	public void setData(List<?> data) {
		this.data = data;
	}
    
}