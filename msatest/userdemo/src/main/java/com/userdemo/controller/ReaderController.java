package com.userdemo.controller;


import com.userdemo.model.dto.ReaderWebBookChapterDto;
import com.userdemo.model.dto.WebBookChapterDto;
import com.userdemo.model.dto.WebBookDto;
import com.userdemo.model.form.RegisterReaderForm;
import com.userdemo.model.form.WebBookChapterPaymentForm;
import com.userdemo.service.ReaderService;
import com.userdemo.service.ReaderWebBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService ReaderService;
    private final ReaderWebBookService readerWebBookService;
//    private final WebBookService webBookService;
//    private final WebBookPaymentService webBookPaymentService;

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/")
    public ResponseEntity<Long> registerReader(@RequestBody RegisterReaderForm registerReaderForm){
        return ResponseEntity.ok(ReaderService.registerReader(registerReaderForm));
    }

    @GetMapping("/webBook")
    public ResponseEntity<List<WebBookDto>> getWebBookList(){
        return ResponseEntity.ok(readerWebBookService.getWebBookList());
    }

    @GetMapping("/{readerId}/webBook/{webBookId}/chapter")
    public ResponseEntity<List<ReaderWebBookChapterDto>> getWebBookChapterList(
            @PathVariable(value = "readerId") Long readerId,
            @PathVariable(value = "webBookId") Long webBookId){
        return ResponseEntity.ok(readerWebBookService.getWebBookChapterList(readerId,webBookId));
    }

    @PostMapping("/{readerId}/payment/")
    public ResponseEntity<WebBookChapterDto> paymentWebBookChapter(
            @PathVariable(value = "readerId") Long readerId,
            @RequestBody WebBookChapterPaymentForm webBookChapterPaymentForm){
        return ResponseEntity.ok(readerWebBookService.paymentWebBookChapter(readerId,webBookChapterPaymentForm));
    }
}
