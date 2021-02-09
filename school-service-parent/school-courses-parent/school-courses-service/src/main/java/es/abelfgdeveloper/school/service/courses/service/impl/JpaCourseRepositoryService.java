package es.abelfgdeveloper.school.service.courses.service.impl;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.common.exception.client.NotFoundException;
import es.abelfgdeveloper.common.jpa.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.domain.CoursePaginated;
import es.abelfgdeveloper.school.service.courses.mapper.CourseMapper;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.model.repository.CourseSpringDataJpaRepository;
import es.abelfgdeveloper.school.service.courses.model.repository.specification.CourseSpecification;
import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class JpaCourseRepositoryService implements CourseRepositoryService {

  private final CourseSpringDataJpaRepository courseRepository;
  private final CourseSpecification courseSpecification;
  private final CourseMapper courseMapper;
  private final PaginationMapper paginationMapper;

  @Override
  public Course save(Course course) {
    CourseEntity courseInDataBase = null;
    if (course.getId() == null) {
      courseInDataBase = create(course);
    } else {
      courseInDataBase = update(course);
    }
    return courseMapper.map(courseInDataBase);
  }

  @Override
  public void deleteById(String id) {
    courseRepository.delete(find(id));
  }

  @Override
  public Course findById(String id) {
    return courseMapper.map(find(id));
  }

  @Override
  public CoursePaginated findAll(PaginationIn pagination, String query) {
    PageRequest page = PageRequest.of(pagination.getPage(), pagination.getSize());
    Page<CourseEntity> coursesInDataBase = null;
    if (StringUtils.isEmpty(query)) {
      coursesInDataBase = courseRepository.findAll(page);
    } else {
      coursesInDataBase = courseRepository.findAll(courseSpecification.nameLike(query), page);
    }
    return CoursePaginated.builder()
        .pagination(paginationMapper.map(coursesInDataBase))
        .courses(
            coursesInDataBase
                .getContent()
                .stream()
                .map(courseMapper::map)
                .collect(Collectors.toList()))
        .build();
  }

  @Override
  public Optional<Course> findByName(String name) {
    Optional<CourseEntity> courseInDataBase = courseRepository.findByName(name);
    if (courseInDataBase.isPresent()) {
      return Optional.of(courseMapper.map(courseInDataBase.get()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public List<Course> findByStudentId(String studentId) {
    return courseRepository
        .findByStudentId(studentId)
        .stream()
        .map(courseMapper::map)
        .collect(Collectors.toList());
  }

  private CourseEntity create(Course course) {
    return courseRepository.save(courseMapper.map(course));
  }

  private CourseEntity update(Course course) {
    CourseEntity courseInDataBase = find(course.getId());
    courseInDataBase.setName(course.getName());
    return courseRepository.save(courseInDataBase);
  }

  private CourseEntity find(String id) {
    Optional<CourseEntity> courseInDataBase = courseRepository.findById(id);
    if (courseInDataBase.isPresent()) {
      return courseInDataBase.get();
    } else {
      throw new NotFoundException(CourseErrorCodes.ID_NOT_FOUND);
    }
  }
}
