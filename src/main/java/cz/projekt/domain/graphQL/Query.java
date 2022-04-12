package cz.projekt.domain.graphQL;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import cz.projekt.domain.dto.ItemDetail;
import cz.projekt.domain.entity.database.Item;
import cz.projekt.domain.repository.database.ItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private ItemRepository itemRepository;

    public List<ItemDetail> get() {
        return itemRepository.findAll()
                .stream()
                .map(i -> ItemDetail.builder()
                        .id(i.getId())
                        .name(i.getProduct().getName())
                        .build())
                .collect(Collectors.toList());
    }


}
