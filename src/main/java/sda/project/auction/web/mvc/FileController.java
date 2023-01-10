package sda.project.auction.web.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sda.project.auction.model.Auction;
import sda.project.auction.model.CategoryTree;
import sda.project.auction.model.File;
import sda.project.auction.model.User;
import sda.project.auction.service.AuctionService;
import sda.project.auction.service.CategoryService;
import sda.project.auction.service.FileStorageService;
import sda.project.auction.service.UserService;
import sda.project.auction.web.message.ResponseFile;
import sda.project.auction.web.message.ResponseMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping(
        path="/files",
        method={RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE, RequestMethod.PUT}
)
@Slf4j
public class FileController {

    @Autowired
    private FileStorageService storageService;
    private final AuctionService auctionService;
    private final CategoryService categoryService;

    private final UserService userService;
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            //storageService.store(file);

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
                    .path("/files/")
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

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id, ModelMap map) {

        File fileDB = storageService.getFile(id);
        storageService.delete(id);

        Auction auction = auctionService.findById(fileDB.getAuction().getID());
        map.addAttribute("auction", auction);

        User user = userService.findById(auction.getUser().getID());
        map.addAttribute("user", user);

        List<File> files = storageService.getFilesByAuctionId(auction.getID());

        //log.info("Deleted file: " + files);

        map.addAttribute("storedFiles", files);
        map.addAttribute("filesSize", files.size());

        List<CategoryTree> categories = categoryService.findAllCategoryTree();
        map.addAttribute("categories", categories);

        Map<String, String> promotedOptions = new HashMap<String, String>();
        promotedOptions.put("false", "nie");
        promotedOptions.put("true", "tak");
        map.addAttribute("promotedOptions", promotedOptions);

        return "create-auction";
    }
}

