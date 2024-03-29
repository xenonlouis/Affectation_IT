package com.employeedao.Model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Employee_Project")
@NamedQuery(name="getAllE_P", query = "select s from EmployeeProject s")
public class EmployeeProject {
    @Id
    private Long id;
    @Column(name = "implication")
    private BigDecimal implication;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Project project;



    // Constructors, getters, and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BigDecimal getImplication() {
        return implication;
    }

    public void setImplication(BigDecimal implication) {
        this.implication = implication;
    }

    @Override
    public String toString() {
        return "EmployeeProject{" +
                "project=" + project.getName() +
                ", implication=" + implication +
                '}';
    }
}
