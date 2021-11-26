package ru.demo.transaction.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demo.transaction.jpa.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
