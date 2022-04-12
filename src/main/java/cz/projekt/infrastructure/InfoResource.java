package cz.projekt.infrastructure;


import cz.projekt.application.assembler.valueType.ValueTypeAssembler;
import cz.projekt.application.helper.FlashMessageHelper;
import cz.projekt.domain.dto.ValueTypeList;
import cz.projekt.domain.exception.MotherException;
import cz.projekt.domain.message.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("info/")
@RestController
@Slf4j
@RequiredArgsConstructor
public class InfoResource {

    private final ValueTypeAssembler valueTypeAssembler;

    @GetMapping("get-value-types")
    private Response<List<ValueTypeList>> getValueTypesList() {
        try {
            return new Response<>(true, valueTypeAssembler.assemble(),
                    FlashMessageHelper.getMessages());
        } catch (MotherException e) {
            FlashMessageHelper.addMessage(FlashMessageHelper.fromException(e));
            return new Response<>(false, null, FlashMessageHelper.getMessages());
        }
    }

}
