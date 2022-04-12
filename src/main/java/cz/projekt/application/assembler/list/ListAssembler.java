package cz.projekt.application.assembler.list;


import cz.projekt.domain.dto.ItemList;
import cz.projekt.domain.exception.ListNotFoundException;
import cz.projekt.domain.repository.database.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListAssembler {

    private final ShoppingListRepository shoppingListRepository;

    public List<ItemList> assemble(Long listId) {

        return shoppingListRepository.findById(listId)
                .orElseThrow(() -> new ListNotFoundException("List not found!"))
                .getItemList()
                .stream()
                .map(item -> ItemList.builder()
                        .id(item.getId())
                        .created(item.getCreated())
                        .createdBy(item.getCreatedBy())
                        .amount(item.getAmount())
                        .status(item.getStatus())
                        .valueType(item.getProduct().getValueType() == null ? null
                                : item.getProduct().getValueType().getValue())
                        .name(item.getProduct().getName())
                        .build())
                .collect(Collectors.toList());

    }

}
