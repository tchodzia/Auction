package sda.project.auction.web.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sda.project.auction.model.File;
import sda.project.auction.service.FileStorageService;
import sda.project.auction.web.message.ResponseFile;
import sda.project.auction.web.message.ResponseMessage;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping(
        path="/files",
        method={RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE, RequestMethod.PUT}
)
public class FileController {

        @Autowired
        private FileStorageService storageService;

        @PostMapping("/upload")
        public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
            String message = "";
            try {
                storageService.store(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        @GetMapping("/files")
        public ResponseEntity<List<ResponseFile>> getListFiles() {
            List<ResponseFile> files = storageService.getAllFiles().stream().map(dbFile -> {
                String fileDownloadUri = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/files/files/")
                        .path(dbFile.getID().toString())
                        .toUriString();

                return new ResponseFile(
                        dbFile.getFile_name(),
                        fileDownloadUri,
                        dbFile.getFile_type(),
                        dbFile.getFile_content().length);
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(files);
        }

        @GetMapping("/{id}")
        public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
            File fileDB = storageService.getFile(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFile_name() + "\"")
                    .body(fileDB.getFile_content());
        }
    }

