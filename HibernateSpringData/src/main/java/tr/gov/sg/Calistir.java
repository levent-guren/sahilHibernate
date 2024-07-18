package tr.gov.sg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tr.gov.sg.entity.Employee;
import tr.gov.sg.repository.DepartmentRepository;
import tr.gov.sg.repository.EmployeeRepository;

@Component
public class Calistir implements CommandLineRunner {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public void run(String... args) throws Exception {

		// Burada Hibernate ile çalışılmasını sağlayan kısım yazılabilir.
		// Örnek olarak, bir entity'nin eklenmesi, güncellenmesi, silinmesi gibi
		// işlemler burada yapılabilir.
		// Örnek olarak, bir Rol entity'ni eklemek için:
		// Rol rol = new Rol("Admin");
		// rolRepository.save(rol);

		// ornek1();
		// ornek2();
		// ornek3();
		ornek4();

	}

	private void ornek1() {
		List<Employee> emps = employeeRepository.findByDepartmentDepartmentName("Purchasing");
		emps.forEach(e -> {
			System.out.println(e.getFirstName());
		});
	}

	private void ornek2() {
		List<List<Object>> sonuclar = employeeRepository.bolumAdiVeCalisanSayisi();
		sonuclar.forEach(satir -> {
			System.out.println(satir.get(0) + " " + satir.get(1));
		});
	}

	private void ornek3() {
		List<List<Object>> sonuclar = employeeRepository.bolumAdiVeCalisanSayisi("%e%");
		System.out.println("Kayıt sayısı: " + sonuclar.size());
		sonuclar.forEach(satir -> {
			System.out.println(satir.get(0) + " " + satir.get(1));
		});
	}

	private void ornek4() {
		List<List<Object>> sonuclar = employeeRepository.bolumAdiVeCalisanSayisiNative();
		sonuclar.forEach(satir -> {
			System.out.println(satir.get(0) + " " + satir.get(1));
		});
	}

}
