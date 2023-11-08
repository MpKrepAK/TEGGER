package org.marketplace.server.controller.admin;

import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.service.InfoBooleanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/info-booleans")
public class AdminInfoBooleanController {
    private final InfoBooleanService infoBooleanService;

    public AdminInfoBooleanController(InfoBooleanService infoBooleanService) {
        this.infoBooleanService = infoBooleanService;
    }
    @GetMapping("all")
    public List<OneFieldEntityDTO> getAll(){
        return infoBooleanService.getAll();
    }
    @GetMapping("")
    public Long getPageCount(@RequestParam(required = false) String name){
        return infoBooleanService.pageCount(name);
    }

    @GetMapping("{page}")
    public List<OneFieldEntityDTO> getPage(@PathVariable int page, @RequestParam(required = false) String name){
        return infoBooleanService.getPage(page, name);
    }

    @PostMapping
    public ServerMessageDTO add(@RequestBody OneFieldEntityDTO dto){
        return infoBooleanService.add(dto);
    }

    @PutMapping
    public ServerMessageDTO update(@RequestBody OneFieldEntityDTO dto){
        return infoBooleanService.update(dto);
    }

    @DeleteMapping("{id}")
    public ServerMessageDTO delete(@PathVariable Long id){
        return infoBooleanService.delete(id);
    }
}
