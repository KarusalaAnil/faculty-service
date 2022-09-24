package com.springboot.microservice.controller;

import com.springboot.microservice.dto.CourseRequestDTO;
import com.springboot.microservice.dto.CourseResponseDTO;
import com.springboot.microservice.dto.ServiceResponse;
import com.springboot.microservice.service.FacaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-service")
public class FacultyController {

    @Autowired
    private FacaltyService facaltyService;

    @PostMapping
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO requestDTO) {
        return facaltyService.addCourseDetails(requestDTO);
    }

    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findAllCourse() {
        return facaltyService.getCourseDetails();
    }

    @GetMapping("/find/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable("courseId") int courseId) {
        return facaltyService.findCourseWithPathParams(courseId);
    }

    @GetMapping("/find/request")
    public ServiceResponse<CourseResponseDTO> findCourse(@RequestParam("courseId") Integer courseId) {

        return facaltyService.findCourseWithRequestParams(courseId);
    }

    @PutMapping("/update/{courseId}")
    public String updateCourse(@RequestBody CourseRequestDTO requestDTO, @PathVariable("courseId") Integer courseId) {
        return facaltyService.updateCourseDetails(courseId, requestDTO);
    }

    @DeleteMapping("/delete/{courseId}")
    public String updateCourse(@PathVariable("courseId") Integer courseId) {
        return facaltyService.deleteCourseDetails(courseId);
    }


}
