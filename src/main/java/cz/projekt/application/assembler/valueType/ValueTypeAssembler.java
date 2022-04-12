package cz.projekt.application.assembler.valueType;


import cz.projekt.domain.dto.ValueTypeList;
import cz.projekt.domain.repository.database.ValueTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ValueTypeAssembler {

    private final ValueTypeRepository valueTypeRepository;

    public List<ValueTypeList> assemble() {
        return valueTypeRepository.findAll()
                .stream()
                .map(e-> ValueTypeList.builder()
                        .id(e.getId())
                        .value(e.getValue())
                        .build())
                .collect(Collectors.toList());
    }

}
