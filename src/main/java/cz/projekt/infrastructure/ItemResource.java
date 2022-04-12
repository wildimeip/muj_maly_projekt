package cz.projekt.infrastructure;


import cz.projekt.application.helper.FlashMessageHelper;
import cz.projekt.application.handler.item.ChangeStatusItemHandler;
import cz.projekt.application.handler.item.NewItemHandler;
import cz.projekt.domain.dto.ItemNew;
import cz.projekt.domain.enums.StatusEnum;
import cz.projekt.domain.exception.MotherException;
import cz.projekt.domain.message.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("item/")
@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemResource {

    private final NewItemHandler newItemHandler;
    private final ChangeStatusItemHandler changeStatusItemHandler;

    @PostMapping("new")
    public Response<Boolean> savePolozka(@RequestBody ItemNew itemNew) {
        try {
            newItemHandler.handle(itemNew);
            return new Response<>(true, true,
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }


    @GetMapping("{itemId}/{statusEnum}")
    public Response<Boolean> changeStatus(@PathVariable Long itemId,
            @PathVariable StatusEnum statusEnum) {
        try {
            changeStatusItemHandler.handle(itemId, statusEnum);
            return new Response<>(true, true,
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }

}
