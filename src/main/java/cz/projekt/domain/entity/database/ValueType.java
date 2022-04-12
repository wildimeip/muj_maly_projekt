package cz.projekt.domain.entity.database;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "value_type",schema = "list")
@Data
public class ValueType {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String value;



}
