package org.marketplace.server.controller.admin;

import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.service.InfoStringService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/info-strings")
public class AdminInfoStringController {
    private final InfoStringService infoStringService;

    public AdminInfoStringController(InfoStringService infoStringService) {
        this.infoStringService = infoStringService;
    }

    @GetMapping("")
    public Long getPageCount(@RequestParam(required = false) String name){
        return infoStringService.pageCount(name);
    }

    @GetMapping("{page}")
    public List<OneFieldEntityDTO> getPage(@PathVariable int page, @RequestParam(required = false) String name){
        return infoStringService.getPage(page, name);
    }

    @PostMapping
    public ServerMessageDTO add(@RequestBody OneFieldEntityDTO dto){
        return infoStringService.add(dto);
    }

    @PutMapping
    public ServerMessageDTO update(@RequestBody OneFieldEntityDTO dto){
        return infoStringService.update(dto);
    }

    @DeleteMapping("{id}")
    public ServerMessageDTO delete(@PathVariable Long id){
        return infoStringService.delete(id);
    }
}
