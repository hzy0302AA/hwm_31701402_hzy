package com.example.hw.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author fuyongle
 * @version 1.0
 * @date 2020-06-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_class_student")
public class ClassStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer classId;

    private Integer studentId;

    /**
     * 是否审核通过
     * 0 待审核
     * 1 不通过
     * 2 通过
     */
    private Integer active;

}
