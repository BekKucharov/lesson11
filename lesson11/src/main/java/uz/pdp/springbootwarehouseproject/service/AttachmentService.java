package uz.pdp.springbootwarehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.springbootwarehouseproject.entity.Attachment;
import uz.pdp.springbootwarehouseproject.entity.AttachmentContent;
import uz.pdp.springbootwarehouseproject.payload.Result;
import uz.pdp.springbootwarehouseproject.repository.AttachmentContentRepository;
import uz.pdp.springbootwarehouseproject.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public List<Attachment> getAttachment(){
        return attachmentRepository.findAll();
    }

    public Attachment getAttachmentById(Integer id) {
        Optional<Attachment> attachmentId = attachmentRepository.findById(id);
        if (!attachmentId.isPresent()) return new Attachment();
        Attachment attachment = attachmentId.get();
        return attachment;
    }

    public Result downloadAttachment(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> fileId = attachmentRepository.findById(id);
        if (!fileId.isPresent()) return new Result("File not found", false);
        Attachment attachment = fileId.get();

        Optional<AttachmentContent> attachmentContentId = attachmentContentRepository.findByAttachment_Id(id);
        if (!attachmentContentId.isPresent()) return new Result("Attachment content not found", false);
        AttachmentContent attachmentContent = attachmentContentId.get();

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + attachment.getName() + "\"");
        response.setContentType(attachment.getContentType());

        FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        return new Result("Attachment downloaded successfully", true);
    }

    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("Attachment added successfully", true, attachment.getId());
    }

    public Result delAttachment(Integer id){
        Optional<Attachment> attachmentId = attachmentRepository.findById(id);
        if (!attachmentId.isPresent())
            return new Result("Attachment not found", false);
        attachmentRepository.deleteById(id);
        attachmentContentRepository.deleteById(id);
        return new Result("Attachment and its content deleted", true);
    }

}
