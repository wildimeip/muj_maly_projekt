package cz.projekt.domain.entity.redis;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("Product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRedis {

    @Id
    private String id; //name of product

    private Long productId;
}
