package cz.projekt.domain.entity.database;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "product",schema = "list")
@Data
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    List<Item> itemList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "value_type_id")
    private ValueType valueType;

}
