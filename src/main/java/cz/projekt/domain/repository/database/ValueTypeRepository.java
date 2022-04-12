package cz.projekt.domain.repository.database;

import cz.projekt.domain.entity.database.ValueType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueTypeRepository extends JpaRepository<ValueType,Long> {

    ValueType findFirstByOrderById();
}
