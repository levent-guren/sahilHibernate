package calistir;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Employee;

@SuppressWarnings({ "unused", "rawtypes" })
public class Calistir {
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		// ornek1(session);
		// session = ornek2(sessionFactory, session);
		// ornek3(session);
		// ornek4(session);
		// ornek5(session);
		// ornek6(session);
		// ornek7(session);
		ornek8(session);
		session.close();
		sessionFactory.close();
	}

	private static void ornek3(Session session) {

		List<List> sonuc = session.createQuery(
				"select e.firstName, e.lastName from Employee e where e.department.departmentName = 'Purchasing'",
				List.class).list();
		sonuc.forEach(d -> {
			System.out.println(d.get(0) + " " + d.get(1));
		});
	}

	private static void ornek4(Session session) {
		List<List> sonuc = session.createQuery("select d.departmentName, count(e)  from Department d"
				+ " join d.employees e where e.firstName like '%e%' group by d.departmentName", List.class).list();
		sonuc.forEach(s -> {
			System.out.println(s.get(0) + " " + s.get(1));
		});
	}

	private static void ornek5(Session session) {
		List<Object[]> sonuc = session.createNativeQuery(
				"select department_id, count(*)  from employees group by department_id", Object[].class).list();
		sonuc.forEach(s -> {
			int a = ((BigDecimal) s[1]).intValue() + 1;
			System.out.println(a + ":" + s[0] + " " + s[1]);
		});
	}

	private static void ornek6(Session session) {
		List<Employee> sonuc = session.createNamedQuery("Employee.findAll", Employee.class).list();
		sonuc.forEach(s -> {
			System.out.println(s.getFirstName());
		});
	}

	private static void ornek7(Session session) {
		Query<Employee> query = session.createQuery("from Employee e where e.salary>?1", Employee.class);
		query.setParameter(1, 15000);
		List<Employee> sonuc = query.list();
		sonuc.forEach(s -> {
			System.out.println(s.getFirstName());
		});
	}

	private static void ornek8(Session session) {
		Query<Employee> query = session.createQuery("from Employee e where e.salary>:maas", Employee.class);
		query.setParameter("maas", 15000);
		List<Employee> sonuc = query.list();
		sonuc.forEach(s -> {
			System.out.println(s.getFirstName());
		});
	}

	private static void ornek1(Session session) {
		Query<Employee> query = session.createQuery("from Employee", Employee.class);
		List<Employee> employees = query.list();
		employees.forEach(e -> System.out.println(e.getFirstName()));
	}

	private static Session ornek2(SessionFactory sessionFactory, Session session) {
		Employee e = session.find(Employee.class, 101);
		System.out.println(e.getFirstName());
		session.close();
		session = sessionFactory.openSession();
		e = session.find(Employee.class, 101);
		System.out.println(e.getFirstName());
		return session;
	}
}
