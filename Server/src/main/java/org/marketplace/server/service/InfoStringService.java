package org.marketplace.server.service;

import org.apache.logging.log4j.util.Strings;
import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.InfoStringRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoStringService {
    private final MapperService mapperService;
    private final InfoStringRepository infoStringRepository;
    @Value("${admin.page.size}")
    private int pageSize;

    public List<OneFieldEntityDTO> getAll(){
        return infoStringRepository.findAll().stream().map(mapperService::fromInfoString).collect(Collectors.toList());
    }
    public InfoStringService(
            MapperService mapperService,
            InfoStringRepository infoStringRepository) {
        this.mapperService = mapperService;
        this.infoStringRepository = infoStringRepository;
    }

    public List<OneFieldEntityDTO> getPage(int page, String name){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (Strings.isEmpty(name)){
            name="";
        }
        return infoStringRepository
                .findByNameContaining(name, pageable)
                .map(mapperService::fromInfoString)
                .stream().collect(Collectors.toList());
    }

    public ServerMessageDTO add(OneFieldEntityDTO dto) {
        if (infoStringRepository.findByName(dto.getName()).isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        infoStringRepository.save(mapperService.infoStringFromDTO(dto));
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public ServerMessageDTO update(OneFieldEntityDTO dto) {
        var optId = infoStringRepository.findById(dto.getId());
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        var optName = infoStringRepository.findByName(dto.getName());
        if (optName.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        var entity = optId.get();
        entity.setName(dto.getName());
        infoStringRepository.save(entity);
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public long pageCount(String name) {
        if (Strings.isEmpty(name)){
            name="";
        }
        long totalCount = infoStringRepository.countByNameContaining(name);
        long totalPages = (long) Math.ceil((double) totalCount / pageSize);
        return totalPages;
    }

    public ServerMessageDTO delete(Long id) {
        var optId = infoStringRepository.findById(id);
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        infoStringRepository.delete(optId.get());
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }
}
