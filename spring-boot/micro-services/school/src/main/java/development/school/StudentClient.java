package development.school;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "students")
public interface StudentClient {

    @GetMapping("/school/{schoolId}")
    List<StudentDTO> findAllStudentsBySchool(@PathVariable("schoolId") Integer schoolId);
}
