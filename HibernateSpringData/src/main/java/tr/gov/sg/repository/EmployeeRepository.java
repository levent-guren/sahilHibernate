package tr.gov.sg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tr.gov.sg.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByDepartmentDepartmentName(String adi);

	@Query("select d.departmentName, count(e)  from Department d join d.employees e where e.firstName like '%e%' group by d.departmentName")
	List<List<Object>> bolumAdiVeCalisanSayisi();

	@Query("select d.departmentName, count(e)  from Department d join d.employees e where e.firstName like :karakter group by d.departmentName")
	List<List<Object>> bolumAdiVeCalisanSayisi(@Param("karakter") String k);

	@Query(value = "select department_id, count(*)  from employees group by department_id", nativeQuery = true)
	List<List<Object>> bolumAdiVeCalisanSayisiNative();
}
