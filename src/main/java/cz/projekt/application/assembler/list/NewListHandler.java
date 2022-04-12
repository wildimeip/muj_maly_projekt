package cz.projekt.application.assembler.list;


import cz.projekt.domain.entity.database.ShoppingList;
import cz.projekt.domain.repository.database.ShoppingListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewListHandler {

    private final ShoppingListRepository shoppingListRepository;

    @Transactional
    public Long handle() {
        return shoppingListRepository.save(new ShoppingList()).getId();
    }

}
