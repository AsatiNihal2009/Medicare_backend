package com.medicare.entity;

import java.util.List;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.medicare.enums.ERole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
    private ERole name;
	
	@ManyToMany(mappedBy="roles")
	private List<User> users;
}
