package cz.projekt.application.handler.item;


import cz.projekt.domain.entity.database.Item;
import cz.projekt.domain.enums.StatusEnum;
import cz.projekt.domain.exception.ItemNotFoundException;
import cz.projekt.domain.repository.database.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChangeStatusItemHandler {

    private final ItemRepository itemRepository;

    @Transactional
    public void handle(Long itemId, StatusEnum status) throws ItemNotFoundException {

        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new ItemNotFoundException("Item not found!")
        );
        item.setStatus(status);
        itemRepository.save(item);
    }

}
