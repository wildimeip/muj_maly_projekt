package cz.projekt.domain.repository.database;

import cz.projekt.domain.dto.ProductSuggest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ProductNativeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    private static String SUGGEST_QUERY ="SELECT pr.name as name, pr.id as id "
            + "FROM list.product pr "
            + "WHERE LOWER(UNACCENT(pr.name)) LIKE LOWER(UNACCENT(:value))";

    public List<ProductSuggest> suggest(String value){

        Map<String, Object> params = new HashMap<>();
        params.put("value", "%"+value+"%");

        return namedParameterJdbcTemplate.query(
                SUGGEST_QUERY,
                params,
                new BeanPropertyRowMapper<>(ProductSuggest.class)
        );
    }


}
