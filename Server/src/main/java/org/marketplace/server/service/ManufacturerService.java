package org.marketplace.server.service;

import org.apache.logging.log4j.util.Strings;
import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.InfoStringRepository;
import org.marketplace.server.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    private final MapperService mapperService;
    private final ManufacturerRepository manufacturerRepository;
    @Value("${admin.page.size}")
    private int pageSize;

    public ManufacturerService(
            MapperService mapperService,
            ManufacturerRepository manufacturerRepository) {
        this.mapperService = mapperService;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<OneFieldEntityDTO> getPage(int page, String name){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (Strings.isEmpty(name)){
            name="";
        }
        return manufacturerRepository
                .findByNameContaining(name, pageable)
                .map(mapperService::fromManufacturer)
                .stream().collect(Collectors.toList());
    }

    public ServerMessageDTO add(OneFieldEntityDTO dto) {
        if (manufacturerRepository.findByName(dto.getName()).isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        manufacturerRepository.save(mapperService.manufacturerFromDTO(dto));
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public ServerMessageDTO update(OneFieldEntityDTO dto) {
        var optId = manufacturerRepository.findById(dto.getId());
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        var optName = manufacturerRepository.findByName(dto.getName());
        if (optName.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким именем уже существует");
        }
        var entity = optId.get();
        entity.setName(dto.getName());
        manufacturerRepository.save(entity);
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public long pageCount(String name) {
        if (Strings.isEmpty(name)){
            name="";
        }
        long totalCount = manufacturerRepository.countByNameContaining(name);
        long totalPages = (long) Math.ceil((double) totalCount / pageSize);
        return totalPages;
    }

    public ServerMessageDTO delete(Long id) {
        var optId = manufacturerRepository.findById(id);
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Информация с таким Id отсутствует");
        }
        manufacturerRepository.delete(optId.get());
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }
}
