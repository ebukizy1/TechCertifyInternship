package com.techcertify.techcertify_taskmanagement_application.data.repository;

import com.techcertify.techcertify_taskmanagement_application.data.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
