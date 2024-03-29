package com.employeedao.DAO;

import com.employeedao.Model.Employee;
import com.employeedao.Model.EmployeeProject;
import com.employeedao.Model.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    EntityManagerFactory man = Persistence.createEntityManagerFactory("re");
    EntityManager em ;

    public EmployeeDAOImpl() {
        em= man.createEntityManager();
    }

    @Override
    public void addEmployee(Employee employee) {

        em = man.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void updateEmployee(Employee employee) {

        em = man.createEntityManager();
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void deleteEmployee(Employee employee) {

        em = man.createEntityManager();
        em.getTransaction().begin();
        Employee managedEmployee = em.merge(employee);
        em.remove(managedEmployee);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public Employee getEmployee(int id) {
     return em.find(Employee.class,id);

    }

    @Override
    public List<Employee> getAllEmployees() {
        em= man.createEntityManager();

        return (List<Employee>) em.createNamedQuery("getAll").getResultList();
    }




    @Override
    public List<Project> getAllProjects() {
        em = man.createEntityManager();
        return em.createNamedQuery("Project.getAllProjects", Project.class)
                .getResultList();
    }
    @Override
    public List<EmployeeProject> getAllE_P() {
        em = man.createEntityManager();
        return em.createNamedQuery("getAllE_P", EmployeeProject.class)
                .getResultList();
    }

    public void assignEmployeeToProject(Long employeeId, Long projectId) {
        em = man.createEntityManager();
        em.getTransaction().begin();
        // Retrieve the Employee and Project entities from the database
        Employee employee = em.find(Employee.class, employeeId);
        Project project = em.find(Project.class, projectId);

        // Ensure that both entities are not null
        if (employee != null && project != null) {
            // Assign the employee to the project (assuming bidirectional relationship)
            employee.getProjects().add(project);
            project.getEmployees().add(employee);

            // Save the changes to the database
            em.merge(employee);
            em.merge(project);
            em.getTransaction().commit();

        } else {
            throw new IllegalArgumentException("Employee or Project not found with the given IDs.");
        }
        em.close();
    }


}
