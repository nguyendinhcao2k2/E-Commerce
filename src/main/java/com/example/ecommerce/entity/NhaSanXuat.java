package com.example.ecommerce.entity;

/**
 * @author caodinh
 */


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "nha_san_xuat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaSanXuat {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "ma", unique = true)
    private String ma;

    @Column(name = "ten", nullable = false)
    @Nationalized
    private String ten;

}

