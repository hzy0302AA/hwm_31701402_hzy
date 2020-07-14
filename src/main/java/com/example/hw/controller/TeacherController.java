package com.example.hw.controller;


import com.example.hw.db.model.Class;
import com.example.hw.db.model.*;
import com.example.hw.db.service.*;
import com.example.hw.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 老师操作控制器
 * 17301092 符永乐
 */

@Controller
@RequestMapping("/app/teacher/")
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    StudentHomeworkService studentHomeworkService;
    @Autowired
    ClassService classService;
    @Autowired
    ClassStudentService classStudentService;
    @Autowired
    HttpSession session;

    @RequestMapping("tLoginPage")//跳转到教师登录界面接口
    public String teacherLoginPage() {
        session.invalidate(); // session失效
        return "/index.jsp";
    }

    @RequestMapping(value = "tLogin")//根据学号的姓名登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        System.out.println(id + password);
        Optional<Teacher> result = teacherService.findById(Integer.valueOf(id));
        Teacher t = new Teacher();
        if (result.isPresent()) {
            t = result.get();
        }
        if (t.getId() == Integer.valueOf(id) && t.getPassword().equals(password)) {
            session.setAttribute("login", "1");
            session.setAttribute("tid", id);
            req.setAttribute("tid", id);
            req.getRequestDispatcher("/index/index.jsp").forward(req, resp);
            System.out.println("登陆成功");
        } else {
            req.setAttribute("error", "账号密码错误");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            System.out.println("登陆失败");
        }
    }

    @RequestMapping("registerPage")
    public String toRegisterPage() {
        return "/tregister.jsp";
    }

    @RequestMapping("tRegister")
    public void sRegister(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        System.out.println(id + password);

        Optional<Teacher> result = teacherService.findById(Integer.valueOf(id));
        if (result.isPresent()) {
            req.setAttribute("message", "当前账号已存在，请重新注册");
            req.getRequestDispatcher("/tregister.jsp").forward(req, resp);
        } else {
            Teacher t = new Teacher(Integer.valueOf(id), name, password);
            teacherService.addTeacher(t);
            req.setAttribute("message", "注册成功");
            req.getRequestDispatcher("/tregister.jsp").forward(req, resp);
        }
    }

    @RequestMapping("addStudentPage")//跳转到添加学生界面接口
    public void addStudentPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("/addStudent.jsp").forward(req, resp);
    }

    @RequestMapping("addHomeworkPage")//跳转发布作业界面
    public void addHomeworkPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("/addHomework.jsp").forward(req, resp);
    }

    @RequestMapping("addHomework")//发布作业处理
    private void addHomework(HttpServletRequest req, HttpServletResponse resp, MultipartFile hwAttachment) throws Exception {//老师添加作业
        Integer classId = Integer.valueOf(req.getParameter("hwClassId"));
        String title = req.getParameter("hwTitle");
        String content = req.getParameter("hwContent");
        String deadline = req.getParameter("deadline");
        // 上传的附件
        String attachment = FileUtil.upload(hwAttachment);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long createTime = System.currentTimeMillis();
        Date date = new Date(createTime);
        String create = simpleDateFormat.format(date);
        System.out.println(create);
        Homework hw = new Homework(0, classId, title, content, attachment, create, deadline);
        System.out.println(hw.toString());
        homeworkService.addHomework(hw);
        req.setAttribute("message", "添加成功");
        req.getRequestDispatcher("/addHomework.jsp").forward(req, resp);
    }

    @RequestMapping("listHomework")//跳转展示作业列表界面
    public void listHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Homework> list = homeworkService.findAll();
        req.setAttribute("hList", list);
        req.getRequestDispatcher("/listHomework.jsp").forward(req, resp);
    }

    @RequestMapping("listStudentHomework")//根据选中作业查看学生提交情况
    public void listStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("hwID");
        int hid = Integer.valueOf(id);
        List<StudentHomework> list = studentHomeworkService.findByhomeworkId(hid);
        req.setAttribute("shList", list);
        req.getRequestDispatcher("/listStudenthw.jsp").forward(req, resp);
    }

    @RequestMapping("updateGrade")//根据选中学生提交作业情况修改分数
    public void updateGrade(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("shwId");
        String grade = req.getParameter("grade");
        StudentHomework studentHomework = studentHomeworkService.findById(Integer.parseInt(id)).get();
        // 修改分数
        studentHomework.setGrade(Float.valueOf(grade));
        studentHomeworkService.submitHomework(studentHomework);

        int hid = studentHomework.getHomeworkId();
        List<StudentHomework> list = studentHomeworkService.findByhomeworkId(hid);
        req.setAttribute("shList", list);
        req.getRequestDispatcher("/listStudenthw.jsp").forward(req, resp);
    }

    @RequestMapping("download")// 下载附件
    public void download(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String fileName = req.getParameter("file");
        FileUtil.download(fileName, resp);
    }

    @RequestMapping("addClassPage")//跳转添加班级界面
    public void addClassPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.getRequestDispatcher("/addClass.jsp").forward(req, resp);
    }

    @RequestMapping("addClass")// 添加班级
    public void addClass(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String title = req.getParameter("title");
        String desc = req.getParameter("desc");
        // 教师编号
        int tid = Integer.parseInt((String) session.getAttribute("tid"));

        // id 写null 数据库自动自增
        Class cls = new Class(null, title, tid, desc);
        System.out.println(cls.toString());
        classService.addClass(cls);
        req.setAttribute("message", "添加成功");
        req.getRequestDispatcher("/addClass.jsp").forward(req, resp);
    }

    @RequestMapping("listClass")//跳转展示班级列表界面
    public void listClass(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 教师编号
        int tid = Integer.parseInt((String) session.getAttribute("tid"));

        List<Class> list = classService.findAllByTeacherId(tid);
        req.setAttribute("hList", list);
        req.getRequestDispatcher("/listClass.jsp").forward(req, resp);
    }

    @RequestMapping("listStudentWithClass")//根据选中班级查看学生申请情况
    public void listStudentWithClass(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 班级编号
        int classId = Integer.parseInt(req.getParameter("clsId"));

        // 找出班级所有申请的学生 申请通过和待申请的
        List<ClassStudent> students = classStudentService.findAllApplyStudent(classId);

        // 所有学生
        List<Student> studentList = studentService.findAll();

        // 所有学生姓名对照map
        Map<Integer, String> stuMap = studentList.stream().collect(Collectors.toMap(Student::getId, Student::getName));

        req.setAttribute("sList", students);
        req.setAttribute("sMap", stuMap);
        req.getRequestDispatcher("/listClassStudent.jsp").forward(req, resp);
    }

    @RequestMapping("applyClassJoin")//根据选中班级查看学生申请情况
    public void applyClassJoin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 编号以及是否通过
        int id = Integer.parseInt(req.getParameter("id"));
        boolean pass = Boolean.parseBoolean(req.getParameter("pass"));

        // 找出编号所在的申请
        ClassStudent apply = classStudentService.findById(id).get();

        if (pass) {
            apply.setActive(2); // 通过
        } else {
            apply.setActive(1); // 拒绝
        }

        classStudentService.updateClassStudent(apply);

        // 班级编号
        int classId = apply.getClassId();

        // 找出班级所有申请的学生 申请通过和待申请的
        List<ClassStudent> students = classStudentService.findAllApplyStudent(classId);

        // 所有学生
        List<Student> studentList = studentService.findAll();

        // 所有学生姓名对照map
        Map<Integer, String> stuMap = studentList.stream().collect(Collectors.toMap(Student::getId, Student::getName));

        req.setAttribute("sList", students);
        req.setAttribute("sMap", stuMap);
        req.getRequestDispatcher("/listClassStudent.jsp").forward(req, resp);
    }


}
