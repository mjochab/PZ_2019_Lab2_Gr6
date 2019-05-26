package ur.inf.lab2.pz.servicemanmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ur.inf.lab2.pz.servicemanmanagement.domain.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();


    @Query("SELECT t FROM Task t ORDER BY t.creationDate DESC")
    Page<Task> findNewest(Pageable pageable);




}
