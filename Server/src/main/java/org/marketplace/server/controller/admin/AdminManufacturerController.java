package org.marketplace.server.controller.admin;

import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.service.ManufacturerService;
import org.marketplace.server.service.ProductTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/manufacturers")
public class AdminManufacturerController {
    private final ManufacturerService manufacturerService;

    public AdminManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("")
    public Long getPageCount(@RequestParam(required = false) String name){
        return manufacturerService.pageCount(name);
    }

    @GetMapping("{page}")
    public List<OneFieldEntityDTO> getPage(@PathVariable int page, @RequestParam(required = false) String name){
        return manufacturerService.getPage(page, name);
    }

    @PostMapping
    public ServerMessageDTO add(@RequestBody OneFieldEntityDTO dto){
        return manufacturerService.add(dto);
    }

    @PutMapping
    public ServerMessageDTO update(@RequestBody OneFieldEntityDTO dto){
        return manufacturerService.update(dto);
    }

    @DeleteMapping("{id}")
    public ServerMessageDTO delete(@PathVariable Long id){
        return manufacturerService.delete(id);
    }
}
