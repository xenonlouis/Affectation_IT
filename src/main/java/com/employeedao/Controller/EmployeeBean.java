package com.employeedao.Controller;

import com.employeedao.DAO.EmployeeDAO;
import com.employeedao.DAO.EmployeeDAOImpl;
import com.employeedao.Model.Employee;

import com.employeedao.Model.Project;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;

import java.util.List;


public class EmployeeBean {

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    private List<Employee> allEmployees;
    private List<Project> allProjects;
    private Double implication;

    public Double getImplication() {
        return implication;
    }

    public void setImplication(Double implication) {
        this.implication = implication;
    }

    public List<Project> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    // Properties for assigning employee to project
    private Employee selectedEmployee;
    private Project selectedProject; // Assuming project ID or name is used for selection

    @PostConstruct
    public void init() {
        refresh_employee();
        refresh_project();
    }

    public void refresh_employee() {
        allEmployees = employeeDAO.getAllEmployees();
    }

    public void refresh_project() {
        allProjects = employeeDAO.getAllProjects();
    }


    public List<Employee> getAllEmployees() {
        return allEmployees;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // Getters and setters for selectedEmployee and selectedProject
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    // Method to assign employee to project
    public void assignEmployeeToProject() {
        try {
            if (selectedEmployee != null && selectedProject != null) {
                employeeDAO.assignEmployeeToProject(selectedEmployee.getId(), selectedProject.getId());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee assigned to project successfully."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please select an employee and a project."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to assign employee to project: " + e.getMessage()));
        }

    }

    public void deleteEmployee(Employee employee) {
        try {
            employeeDAO.deleteEmployee(employee);
            refresh_employee();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee deleted successfully."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete employee: " + ex.getMessage()));
        }
    }
}

