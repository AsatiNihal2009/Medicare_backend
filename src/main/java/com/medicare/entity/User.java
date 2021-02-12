package com.medicare.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user",uniqueConstraints = {
		@UniqueConstraint(columnNames = "username")
})
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name = "username",nullable = false,unique = true)
	private String username;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "enabled",nullable = false)
	private boolean enabled;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinTable(
		      name="user_role",
		      joinColumns={@JoinColumn(name="USER_ID")},
		      inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
	private Set<Role> roles=new HashSet<>();
	
	
}
