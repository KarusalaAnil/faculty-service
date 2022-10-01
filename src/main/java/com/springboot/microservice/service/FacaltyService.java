package com.springboot.microservice.service;

import com.springboot.microservice.dto.CourseRequestDTO;
import com.springboot.microservice.dto.CourseResponseDTO;
import com.springboot.microservice.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacaltyService {
    private String base_URL = "http://localhost:9000/course";
    @Autowired
    private RestTemplate restTemplate;

    public ServiceResponse<CourseResponseDTO> addCourseDetails(CourseRequestDTO requestDTO) {
        return restTemplate.postForObject(base_URL, requestDTO, ServiceResponse.class);
    }

    public ServiceResponse<List<CourseResponseDTO>> getCourseDetails() {
        return restTemplate.getForObject(base_URL, ServiceResponse.class);
    }

    public ServiceResponse<CourseResponseDTO> findCourseWithPathParams(int courseId) {
        return restTemplate.getForObject(base_URL+"/find/path/"+courseId, ServiceResponse.class);
//        return restTemplate.getForObject(base_URL+"/find/path", ServiceResponse.class, courseId);
    }

    public ServiceResponse<CourseResponseDTO> findCourseWithRequestParams(Integer courseId) {
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("courseId", courseId);
        return restTemplate.getForObject(base_URL+"/find/request/?courseId={courseId}", ServiceResponse.class,uriVariables);
//        return restTemplate.getForObject(base_URL+"/find/request", ServiceResponse.class, uriVariables);
    }

    public String updateCourseDetails(Integer courseId, CourseRequestDTO requestDTO) {
//        restTemplate. put(base_URL +"/update", requestDTO,courseId);
        restTemplate. put(base_URL +"/update/"+courseId, requestDTO);
        return "Course Id Updated with " + courseId;
    }

    public String deleteCourseDetails(Integer courseId) {
        restTemplate.delete(base_URL+"/"+courseId);
//        restTemplate.delete(base_URL+courseId);
        return "Course Deleted Successfully with "+courseId;
    }
}
