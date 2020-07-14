package com.example.hw.db.service;

import com.example.hw.db.mapper.ClassMapper;
import com.example.hw.db.mapper.ClassStudentMapper;
import com.example.hw.db.mapper.HomeworkMapper;
import com.example.hw.db.model.Class;
import com.example.hw.db.model.ClassStudent;
import com.example.hw.db.model.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeworkService {
    @Autowired
    HomeworkMapper homeworkMapper;
    @Autowired
    HttpSession session;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    ClassStudentMapper classStudentMapper;

    /**
     * 学生只展示已加入班级作业
     * 教师只展示自身创建班级作业
     *
     * @return
     */
    public List<Homework> findAll() {
        List<Integer> classIds = new ArrayList<>();

        // session里面有没有学生的编号 登录的时候存了
        if (null != session.getAttribute("sId")) {
            int sid = (int) session.getAttribute("sId");
            // 学生加入的班级ids
            List<ClassStudent> classStudents = classStudentMapper.findAllByStudentId(sid);
            classIds = classStudents.stream().map(ClassStudent::getClassId).collect(Collectors.toList());
        }
        // 有没有教师的编号
        if (null != session.getAttribute("tid")) {
            int tid = Integer.parseInt((String) session.getAttribute("tid"));
            // 教师创建的班级ids
            List<Class> classes = classMapper.findAllByTeacherId(tid);
            classIds = classes.stream().map(Class::getId).collect(Collectors.toList());
        }
        // 找出班级底下的作业
        return homeworkMapper.findAllByClassIdIn(classIds);
    }

    public void addHomework(Homework homework) {
        homeworkMapper.save(homework);
    }

    public Homework findByHomeworkId(int id) {
        return homeworkMapper.findById(id);
    }
}
