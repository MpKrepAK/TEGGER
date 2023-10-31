package org.marketplace.server.service;

import org.apache.logging.log4j.util.Strings;
import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTypeService {
    private final MapperService mapperService;
    private final ProductTypeRepository productTypeRepository;
    @Value("${admin.page.size}")
    private int pageSize;

    public ProductTypeService(
            MapperService mapperService,
            ProductTypeRepository productTypeRepository) {
        this.mapperService = mapperService;
        this.productTypeRepository = productTypeRepository;
    }

    public List<OneFieldEntityDTO> getPage(int page, String name){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (Strings.isEmpty(name)){
            name="";
        }
        return productTypeRepository
                .findByNameContaining(name, pageable)
                .map(mapperService::fromProductType)
                .stream().collect(Collectors.toList());
    }

    public ServerMessageDTO add(OneFieldEntityDTO dto) {
        if (productTypeRepository.findByName(dto.getName()).isPresent()){
            return new ServerMessageDTO(400, "Тег с таким именем уже существует");
        }
        productTypeRepository.save(mapperService.productTypeFromDTO(dto));
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public ServerMessageDTO update(OneFieldEntityDTO dto) {
        var optId = productTypeRepository.findById(dto.getId());
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким Id отсутствует");
        }
        var optName = productTypeRepository.findByName(dto.getName());
        if (optName.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким именем уже существует");
        }
        var entity = optId.get();
        entity.setName(dto.getName());
        productTypeRepository.save(entity);
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public long pageCount(String name) {
        if (Strings.isEmpty(name)){
            name="";
        }
        long totalCount = productTypeRepository.countByNameContaining(name);
        long totalPages = (long) Math.ceil((double) totalCount / pageSize);
        return totalPages;
    }

    public ServerMessageDTO delete(Long id) {
        var optId = productTypeRepository.findById(id);
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким Id отсутствует");
        }
        productTypeRepository.delete(optId.get());
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }
}
