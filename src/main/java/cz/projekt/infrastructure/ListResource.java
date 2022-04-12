package cz.projekt.infrastructure;


import cz.projekt.application.helper.FlashMessageHelper;
import cz.projekt.application.assembler.list.ListAssembler;
import cz.projekt.application.assembler.list.NewListHandler;
import cz.projekt.domain.dto.ItemList;
import cz.projekt.domain.exception.MotherException;
import cz.projekt.domain.message.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("list/")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ListResource {

    private final ListAssembler listAssembler;
    private final NewListHandler newListHandler;

    @GetMapping("{listId}")
    public Response<List<ItemList>> getList(@PathVariable Long listId) {
        try {
            return new Response<>(true, listAssembler.assemble(listId),
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }

    @GetMapping("new")
    public Response<Long> createNew() {
        try {
            return new Response<>(true, newListHandler.handle(),
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }


}
