package com.monoloticdemo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class WebBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long webBookId;
    //제목
    private String name;
    //설명
    private String description;

    private LocalDateTime createdAt;

}
