package com.example.hw.db.mapper;

import com.example.hw.db.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fuyongle
 * @version 1.0
 * @date 2020-06-17
 */
public interface ClassMapper extends JpaRepository<Class, Integer> {

    List<Class> findAllByTeacherId(int teacherId);

}
