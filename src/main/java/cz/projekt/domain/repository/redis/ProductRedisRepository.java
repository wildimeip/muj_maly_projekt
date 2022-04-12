package cz.projekt.domain.repository.redis;

import cz.projekt.domain.entity.redis.ProductRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisRepository extends CrudRepository<ProductRedis, String> {
}
