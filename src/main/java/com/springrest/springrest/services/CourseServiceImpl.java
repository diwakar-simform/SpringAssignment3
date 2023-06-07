package com.springrest.springrest.services;

import com.springrest.springrest.respository.CourseRepository;
import com.springrest.springrest.entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        if (courseRepository.count() == 0){
            logger.warn("No course is available");
        }
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(long courseId) {
        Optional<Course> course;
        if (courseRepository.existsById(courseId)){
            course = courseRepository.findById(courseId);
            return course;
        }
        logger.error("No course available with courseId {} ",courseId);
        return null;
    }

    @Override
    public Course addCourse(Course course) {
        logger.info("-----data addition starts----");
        courseRepository.save(course);
        logger.info("-----data added successfully----");
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        if (courseRepository.existsById(course.getId())){
            courseRepository.save(course);
            return course;
        }
        logger.warn("Sorry you can not perform update because {} course id doesn't exit");
        return null;
    }

    @Override
    public void deleteCourse(long courseId) {
        if (courseRepository.existsById(courseId)){
            courseRepository.deleteById(courseId);
        }else {
            logger.error("course doesn't exit");
        }
    }

}
