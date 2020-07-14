package com.example.hw.db.mapper;

import com.example.hw.db.model.StudentHomework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fuyongle
 * @version 1.0
 * @date 2020-06-17
 */
public interface StudentHomeworkMapper extends JpaRepository<StudentHomework, Integer> {

    public List<StudentHomework> findByHomeworkId(int id);

    public List<StudentHomework> findByHomeworkIdAndStudentId(int hid, int sid);

    public List<StudentHomework> findAllByStudentIdAndGradeAfter(int sid, float grade);

}
