package ru.demo.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.demo.transaction.jpa.repository.EmployeeRepository;
import ru.demo.transaction.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TransactionApplicationTests {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeeService employeeService;

  @BeforeEach
  void beforeEach() {
    employeeRepository.deleteAll();
  }

  @Test
  void contextLoads() {
    assertThrows(Exception.class, () -> employeeService.successSaveEmployeeWithException(null));
    System.out.println("*** Count employee ***");
    System.out.println(employeeRepository.count());
  }

  @Test
  void failureSaveEmployeeWithRunTimeException() {
    assertThrows(RuntimeException.class, () -> employeeService.failureSaveEmployeeWithRunTimeException(null));
    System.out.println("*** Count employee ***");
    System.out.println(employeeRepository.count());
  }

  @Test
  void successSaveEmployeeWithRunTimeException() {
    employeeService.successSaveEmployeeWithRunTimeException();
    System.out.println("*** Count employee ***");
    System.out.println(employeeRepository.count());
  }

}
