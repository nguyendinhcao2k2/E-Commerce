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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
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
    @JoinColumn(name = "id_mausac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "id_season")
    private Season season;

//    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<ProductSize> productSizes;

    @Column(name = "mo_ta", nullable = false)
    @Nationalized
    private String moTa;

    @Column(name = "img",nullable = false)
    @Nationalized
    private String Img;

    @Column(name = "so_luong_ton")
    private Integer soLuongTon;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Enumerated(EnumType.STRING)
    private TypeStatus typeStatus;

}
