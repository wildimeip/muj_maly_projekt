package cz.projekt.domain.graphQL;

import com.coxautodev.graphql.tools.GraphQLResolver;
import cz.projekt.domain.dto.ItemDetail;
import cz.projekt.domain.repository.database.ItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ItemDetailResolver implements GraphQLResolver<ItemDetail> {

    private ItemRepository itemRepository;

    public List<ItemDetail> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(i -> ItemDetail.builder()
                        .id(i.getId())
                        .name(i.getProduct().getName())
                        .build())
                .collect(Collectors.toList());
    }

}
