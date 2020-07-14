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
@Entity
@Table(name = "s_class")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer teacherId;

    private String description;

}
