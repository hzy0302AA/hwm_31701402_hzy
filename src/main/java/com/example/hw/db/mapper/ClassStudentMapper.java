package com.example.hw.db.mapper;

import com.example.hw.db.model.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fuyongle
 * @version 1.0
 * @date 2020-06-17
 */
public interface ClassStudentMapper extends JpaRepository<ClassStudent, Integer> {

    ClassStudent findByClassIdAndStudentId(int classId, int studentId);

    List<ClassStudent> findAllByClassIdAndActiveIn(int classId, int[] active);

    List<ClassStudent> findAllByStudentId(int studentId);
}
