package ru.demo.transaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demo.transaction.jpa.entity.Employee;
import ru.demo.transaction.jpa.repository.EmployeeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Transactional
  public void successSaveEmployeeWithException(String name) throws Exception {
    employeeRepository.saveAndFlush(Employee.builder().name("Alex").build());
    employeeRepository.saveAndFlush(Employee.builder().name("Bob").build());

    if (name == null) {
      throw new Exception("name cannot be null");
    }

    employeeRepository.saveAndFlush(Employee.builder().name(name).build());
  }

  @Transactional
  public void failureSaveEmployeeWithRunTimeException(String name) {
    employeeRepository.saveAndFlush(Employee.builder().name("Alex").build());
    employeeRepository.saveAndFlush(Employee.builder().name("Bob").build());

    if (name == null) {
      throw new RuntimeException("name cannot be null");
    }

    employeeRepository.saveAndFlush(Employee.builder().name(name).build());
  }

  @Transactional
  public void successSaveEmployeeWithRunTimeException() {
    employeeRepository.saveAndFlush(Employee.builder().name("Alex").build());
    employeeRepository.saveAndFlush(Employee.builder().name("Bob").build());
    try {
      throwRuntimeException();
    } catch (RuntimeException e) {
      log.error(e.getMessage());
    }
  }

  private void throwRuntimeException() {
    throw new RuntimeException("throw RunTime Exception");
  }
}
