package com.example.ecommerce.entity;

import com.example.ecommerce.infrastructures.constants.TypeStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author caodinh
 */
@Entity
@Table(name = "chi_tiet_sp")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChiTietSanPham {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "ten_sp", nullable = false)
    @Nationalized
    private String tenSP;

    @ManyToOne
    @JoinColumn(name = "id_nsx")
    private NhaSanXuat nhaSanXuat;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;


    @ManyToOne
    @JoinColumn(name = "id_season")
    private Season season;


    @Column(name = "mo_ta", nullable = false, length = 2500)
    @Nationalized
    private String moTa;

    @Column(name = "img",nullable = false)
    @Nationalized
    private String Img;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Enumerated(EnumType.STRING)
    private TypeStatus typeStatus;

}
