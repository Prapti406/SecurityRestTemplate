package com.dev.spring.security.jwt.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.dev.spring.security.jwt.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="users_table")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int uid;
	@NotEmpty
	@Size(max=10,message="Username not less than 2")
	@Column(name="first_Name")
	private String first_Name;
	@NotEmpty
	@Size(max=10,message="Username not less than 2")
	@Column(name="last_Name")
	private String last_Name;
	@Column(name="userName")
	private String userName;
	@Email(message="Please enter a valid email!!!!")
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	@Column(name="phoneno")
	private int phoneno;
	@Column(name="gender")
	private String gender;
	
	@OneToMany(targetEntity = Address.class  ,cascade = CascadeType.ALL)
	@JoinColumn(name = "usadd_fk", referencedColumnName = "uid")
	private List<Address> addresses;
	@ElementCollection(fetch= FetchType.EAGER)
	@CollectionTable(
			name="roles",
			joinColumns = @JoinColumn(name="user_id")
			)
	@Column(name="user_role")
	private Set<String> roles;
	
	
}