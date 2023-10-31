package org.marketplace.server.service;

import org.apache.logging.log4j.util.Strings;
import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.InfoBooleanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoBooleanService {
    private final MapperService mapperService;
    private final InfoBooleanRepository infoBooleanRepository;
    @Value("${admin.page.size}")
    private int pageSize;

    public InfoBooleanService(
            MapperService mapperService,
            InfoBooleanRepository infoBooleanRepository) {
        this.mapperService = mapperService;
        this.infoBooleanRepository = infoBooleanRepository;
    }

    public List<OneFieldEntityDTO> getPage(int page, String name){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (Strings.isEmpty(name)){
            name="";
        }
        return infoBooleanRepository
                .findByNameContaining(name, pageable)
                .map(mapperService::fromInfoBoolean)
                .stream().collect(Collectors.toList());
    }

    public ServerMessageDTO add(OneFieldEntityDTO dto) {
        if (infoBooleanRepository.findByName(dto.getName()).isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        infoBooleanRepository.save(mapperService.infoBooleanFromDTO(dto));
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public ServerMessageDTO update(OneFieldEntityDTO dto) {
        var optId = infoBooleanRepository.findById(dto.getId());
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        var optName = infoBooleanRepository.findByName(dto.getName());
        if (optName.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        var entity = optId.get();
        entity.setName(dto.getName());
        infoBooleanRepository.save(entity);
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public long pageCount(String name) {
        if (Strings.isEmpty(name)){
            name="";
        }
        long totalCount = infoBooleanRepository.countByNameContaining(name);
        long totalPages = (long) Math.ceil((double) totalCount / pageSize);
        return totalPages;
    }

    public ServerMessageDTO delete(Long id) {
        var optId = infoBooleanRepository.findById(id);
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        infoBooleanRepository.delete(optId.get());
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }
}
