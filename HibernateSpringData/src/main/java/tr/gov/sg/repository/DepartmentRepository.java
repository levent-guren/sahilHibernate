package tr.gov.sg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.gov.sg.entity.Department;
import tr.gov.sg.entity.Employee;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	List<Employee> findByDepartmentName(String adi);
}
