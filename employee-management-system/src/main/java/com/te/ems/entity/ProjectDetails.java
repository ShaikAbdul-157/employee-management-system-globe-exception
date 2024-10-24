package com.te.ems.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity

public class ProjectDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Integer projectId;
	@Column(name = "project_name")
	private String projectName;
	
	@ManyToMany
	@JoinTable(name = "map_pd_emp",joinColumns = @JoinColumn(name = "pd_fk"),
	             inverseJoinColumns = @JoinColumn(name = "emp_fk"))
	private List<Employee> employees = Lists.newArrayList();
}
