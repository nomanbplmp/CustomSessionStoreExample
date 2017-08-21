package com.nk.test.spring.dbconfig;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item implements Serializable{
	
@Id
@GeneratedValue
Long Id;

public Long getId() {
	return Id;
}

public Item(){
	
}

public Item(String name){
	this.itemName=name;
	
}

public void setId(Long id) {
	Id = id;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

@Column(name="ITEM_NAME")
String itemName;

}
