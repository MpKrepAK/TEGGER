package org.marketplace.server.controller.admin;

import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.service.InfoNumberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/info-numbers")
public class AdminInfoNumberController {
    private final InfoNumberService infoNumberService;

    public AdminInfoNumberController(InfoNumberService infoNumberService) {
        this.infoNumberService = infoNumberService;
    }
    @GetMapping("all")
    public List<OneFieldEntityDTO> getAll(){
        return infoNumberService.getAll();
    }
    @GetMapping("")
    public Long getPageCount(@RequestParam(required = false) String name){
        return infoNumberService.pageCount(name);
    }

    @GetMapping("{page}")
    public List<OneFieldEntityDTO> getPage(@PathVariable int page, @RequestParam(required = false) String name){
        return infoNumberService.getPage(page, name);
    }

    @PostMapping
    public ServerMessageDTO add(@RequestBody OneFieldEntityDTO dto){
        return infoNumberService.add(dto);
    }

    @PutMapping
    public ServerMessageDTO update(@RequestBody OneFieldEntityDTO dto){
        return infoNumberService.update(dto);
    }

    @DeleteMapping("{id}")
    public ServerMessageDTO delete(@PathVariable Long id){
        return infoNumberService.delete(id);
    }
}
