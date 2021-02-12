package com.medicare.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.medicare.enums.ECategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name = "category_name")
	@Enumerated(EnumType.STRING)
	private ECategory categoryName;
	
	@OneToMany(mappedBy = "category",
			cascade = CascadeType.ALL)
	private List<Product> product;

	
	
}
