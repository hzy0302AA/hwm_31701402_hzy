package com.example.hw.db.service;

import com.example.hw.db.mapper.ClassStudentMapper;
import com.example.hw.db.model.ClassStudent;
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
public class ClassStudentService {
    @Autowired
    ClassStudentMapper classStudentMapper;

    public Optional<ClassStudent> findById(int id) {
        return classStudentMapper.findById(id);
    }

    public void addClassStudent(ClassStudent cls) {
        classStudentMapper.save(cls);
    }

    public List<ClassStudent> findAll() {
        return classStudentMapper.findAll();
    }

    public String joinClass(int classId, int stuId) {

        // 是否已申请
        ClassStudent classStudent = classStudentMapper.findByClassIdAndStudentId(classId, stuId);
        if (null == classStudent) {
            // 没有的话就插数据库进行申请
            ClassStudent studentApply = new ClassStudent(null, classId, stuId, 0);
            classStudentMapper.save(studentApply);
            return "已申请，等待教师处理";
        }
        if (classStudent.getActive() == 0) {
            return "正在申请中，等待教师处理";
        } else if (classStudent.getActive() == 1) {
            return "申请不通过，教师已拒绝";
        } else {
            return "您已在该班级当中，无需申请";
        }
    }

    public List<ClassStudent> findAllApplyStudent(int classId) {
        return classStudentMapper.findAllByClassIdAndActiveIn(classId, new int[]{0, 2});
    }

    public void updateClassStudent(ClassStudent classService) {
        classStudentMapper.save(classService);
    }
}
