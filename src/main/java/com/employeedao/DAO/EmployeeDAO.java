package com.employeedao.DAO;

import com.employeedao.Model.Employee;
import com.employeedao.Model.EmployeeProject;
import com.employeedao.Model.Project;

import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    Employee getEmployee(int id);
    List<Employee> getAllEmployees();
    List<Project> getAllProjects();
    public List<EmployeeProject> getAllE_P();
    public void assignEmployeeToProject(Long employeeId, Long projectId);

}
