package com.example.security.role;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "ROLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
