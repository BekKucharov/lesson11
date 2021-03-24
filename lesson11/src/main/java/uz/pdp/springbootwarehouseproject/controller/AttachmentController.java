package uz.pdp.springbootwarehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.springbootwarehouseproject.entity.Attachment;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public List<Attachment> getAttachment(){
        return attachmentService.getAttachment();
    }
    @GetMapping("/{id}")
    public Attachment getAttachment(@PathVariable Integer id){
        return attachmentService.getAttachmentById(id);
    }
    @GetMapping("/download/{id}")
    public Result downloadAttachment(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        return attachmentService.downloadAttachment(id, response);
    }
    @PostMapping
    public Result upload(MultipartHttpServletRequest request) throws IOException {
        Result result = attachmentService.uploadFile(request);
        return result;
    }
    @DeleteMapping("/{id}")
    public Result delAttachment(@PathVariable Integer id){
        return attachmentService.delAttachment(id);
    }
}
