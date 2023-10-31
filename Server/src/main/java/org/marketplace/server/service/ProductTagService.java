package org.marketplace.server.service;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.repository.ProductTagRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTagService {
    private final MapperService mapperService;
    private final ProductTagRepository productTagRepository;
    @Value("${admin.page.size}")
    private int pageSize;

    public ProductTagService(
            MapperService mapperService,
            ProductTagRepository productTagRepository) {
        this.mapperService = mapperService;
        this.productTagRepository = productTagRepository;
    }

    public List<OneFieldEntityDTO> getPage(int page, String name){
        Pageable pageable = PageRequest.of(page, pageSize);
        if (Strings.isEmpty(name)){
            name="";
        }
        return productTagRepository
                .findByNameContaining(name, pageable)
                .map(mapperService::fromProductTag)
                .stream().collect(Collectors.toList());
    }

    public ServerMessageDTO add(@NotNull OneFieldEntityDTO productTagDTO) {
        if (productTagRepository.findByName(productTagDTO.getName()).isPresent()){
            return new ServerMessageDTO(400, "Тег с таким именем уже существует");
        }
        productTagRepository.save(mapperService.productTagFromDTO(productTagDTO));
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public ServerMessageDTO update(@NotNull OneFieldEntityDTO productTagDTO) {
        var optId = productTagRepository.findById(productTagDTO.getId());
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким Id отсутствует");
        }
        var optName = productTagRepository.findByName(productTagDTO.getName());
        if (optName.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким именем уже существует");
        }
        var entity = optId.get();
        entity.setName(productTagDTO.getName());
        productTagRepository.save(entity);
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }

    public long pageCount(String name) {
        if (Strings.isEmpty(name)){
            name="";
        }
        long totalCount = productTagRepository.countByNameContaining(name);
        long totalPages = (long) Math.ceil((double) totalCount / pageSize);
        return totalPages;
    }

    public ServerMessageDTO delete(Long id) {
        var optId = productTagRepository.findById(id);
        if (!optId.isPresent()){
            return new ServerMessageDTO(400, "Тег с таким Id отсутствует");
        }
        productTagRepository.delete(optId.get());
        return new ServerMessageDTO(200, "Операция произведена успешно");
    }
}
