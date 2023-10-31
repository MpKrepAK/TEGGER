package org.marketplace.server.service;

import org.apache.logging.log4j.util.Strings;
import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.InfoNumberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoNumberService {
    private final MapperService mapperService;
    private final InfoNumberRepository infoNumberRepository;
    @Value("${admin.page.size}")
    private int pageSize;

    public InfoNumberService(
            MapperService mapperService,
            InfoNumberRepository infoNumberRepository) {
        this.mapperService = mapperService;
        this.infoNumberRepository = infoNumberRepository;
    }

    public List<OneFieldEntityDTO> getPage(int page, String name){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (Strings.isEmpty(name)){
            name="";
        }
        return infoNumberRepository
                .findByNameContaining(name, pageable)
                .map(mapperService::fromInfoNumber)
                .stream().collect(Collectors.toList());
    }

    public ServerMessageDTO add(OneFieldEntityDTO dto) {
        if (infoNumberRepository.findByName(dto.getName()).isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        infoNumberRepository.save(mapperService.infoNumberFromDTO(dto));
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public ServerMessageDTO update(OneFieldEntityDTO dto) {
        var optId = infoNumberRepository.findById(dto.getId());
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        var optName = infoNumberRepository.findByName(dto.getName());
        if (optName.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        var entity = optId.get();
        entity.setName(dto.getName());
        infoNumberRepository.save(entity);
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public long pageCount(String name) {
        if (Strings.isEmpty(name)){
            name="";
        }
        long totalCount = infoNumberRepository.countByNameContaining(name);
        long totalPages = (long) Math.ceil((double) totalCount / pageSize);
        return totalPages;
    }

    public ServerMessageDTO delete(Long id) {
        var optId = infoNumberRepository.findById(id);
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        infoNumberRepository.delete(optId.get());
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }
}
