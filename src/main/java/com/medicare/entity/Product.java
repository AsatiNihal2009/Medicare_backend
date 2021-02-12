package com.medicare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.medicare.enums.Age;
import com.medicare.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product{

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "itemName")
	private String itemName;
	
	@Column(name = "itemTotal")
	private double itemTotal;
	
	@Column(name="toggle")
	private boolean toggle;
	
	@Column(name = "age")
	@Enumerated(EnumType.STRING)
	private Age age;
	
	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;


}
