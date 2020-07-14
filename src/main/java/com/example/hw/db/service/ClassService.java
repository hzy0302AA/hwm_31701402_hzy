package com.example.hw.db.service;

import com.example.hw.db.mapper.ClassMapper;
import com.example.hw.db.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author fuyongle
 * @version 1.0
 * @date 2020-06-17
 */
@Service
public class ClassService {
    @Autowired
    ClassMapper classMapper;

    public Optional<Class> findById(int id) {
        return classMapper.findById(id);
    }

    public void addClass(Class cls) {
        classMapper.save(cls);
    }

    public List<Class> findAllByTeacherId(int teacherId) {
        return classMapper.findAllByTeacherId(teacherId);
    }

    public List<Class> findAll() {
        return classMapper.findAll();
    }
}
