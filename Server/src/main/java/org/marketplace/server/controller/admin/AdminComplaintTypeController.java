package org.marketplace.server.controller.admin;

import org.marketplace.server.model.dto.OneFieldEntityDTO;
import org.marketplace.server.model.dto.ServerMessageDTO;
import org.marketplace.server.service.ComplaintTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/complaint-types")
public class AdminComplaintTypeController {
    private final ComplaintTypeService complaintTypeService;

    public AdminComplaintTypeController(ComplaintTypeService complaintTypeService) {
        this.complaintTypeService = complaintTypeService;
    }

    @GetMapping("")
    public Long getPageCount(@RequestParam(required = false) String name){
        return complaintTypeService.pageCount(name);
    }

    @GetMapping("{page}")
    public List<OneFieldEntityDTO> getPage(@PathVariable int page, @RequestParam(required = false) String name){
        return complaintTypeService.getPage(page, name);
    }

    @PostMapping
    public ServerMessageDTO add(@RequestBody OneFieldEntityDTO dto){
        return complaintTypeService.add(dto);
    }

    @PutMapping
    public ServerMessageDTO update(@RequestBody OneFieldEntityDTO dto){
        return complaintTypeService.update(dto);
    }

    @DeleteMapping("{id}")
    public ServerMessageDTO delete(@PathVariable Long id){
        return complaintTypeService.delete(id);
    }
}
