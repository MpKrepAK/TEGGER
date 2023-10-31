package org.marketplace.server.service;

import org.apache.logging.log4j.util.Strings;
import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.ComplaintTypeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintTypeService {
    private final MapperService mapperService;
    private final ComplaintTypeRepository complaintTypeRepository;
    @Value("${admin.page.size}")
    private int pageSize;

    public ComplaintTypeService(
            MapperService mapperService,
            ComplaintTypeRepository complaintTypeRepository) {
        this.mapperService = mapperService;
        this.complaintTypeRepository = complaintTypeRepository;
    }

    public List<OneFieldEntityDTO> getPage(int page, String name){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (Strings.isEmpty(name)){
            name="";
        }
        return complaintTypeRepository
                .findByNameContaining(name, pageable)
                .map(mapperService::fromComplaintType)
                .stream().collect(Collectors.toList());
    }

    public ServerMessageDTO add(OneFieldEntityDTO dto) {
        if (complaintTypeRepository.findByName(dto.getName()).isPresent()){
            return new ServerMessageDTO(400, "Тег с таким именем уже существует");
        }
        complaintTypeRepository.save(mapperService.complaintTypeFromDTO(dto));
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public ServerMessageDTO update(OneFieldEntityDTO dto) {
        var optId = complaintTypeRepository.findById(dto.getId());
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким Id отсутствует");
        }
        var optName = complaintTypeRepository.findByName(dto.getName());
        if (optName.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким именем уже существует");
        }
        var entity = optId.get();
        entity.setName(dto.getName());
        complaintTypeRepository.save(entity);
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public long pageCount(String name) {
        if (Strings.isEmpty(name)){
            name="";
        }
        long totalCount = complaintTypeRepository.countByNameContaining(name);
        long totalPages = (long) Math.ceil((double) totalCount / pageSize);
        return totalPages;
    }

    public ServerMessageDTO delete(Long id) {
        var optId = complaintTypeRepository.findById(id);
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким Id отсутствует");
        }
        complaintTypeRepository.delete(optId.get());
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }
}
