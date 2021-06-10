package com.test.currency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactional")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_summ", nullable = false)
    private BigDecimal sourceSumm;

    @Column(name = "source_name", nullable = false)
    private String sourceName;

    @Column(name = "target_summ", nullable = false)
    private BigDecimal targetSumm;

    @Column(name = "target_name", nullable = false)
    private String targetName;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
