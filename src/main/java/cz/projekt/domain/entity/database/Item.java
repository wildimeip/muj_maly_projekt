package cz.projekt.domain.entity.database;


import com.sun.istack.NotNull;
import cz.projekt.domain.enums.StatusEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "item", schema = "list")
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    private ShoppingList shoppingList;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "created_by")
    private String createdBy;
}
