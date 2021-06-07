package com.webbookdemo.service;

import com.webbookdemo.model.dto.WebBookChapterDetailDto;
import com.webbookdemo.model.dto.WebBookChapterDto;
import com.webbookdemo.model.dto.WebBookDto;
import com.webbookdemo.model.entity.WebBook;
import com.webbookdemo.model.entity.WebBookChapter;
import com.webbookdemo.model.entity.repository.WebBookChapterRepository;
import com.webbookdemo.model.entity.repository.WebBookRepository;
import com.webbookdemo.model.form.WebBookChapterRegisterForm;
import com.webbookdemo.model.form.WebBookRegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebBookService {

    private final WebBookRepository webBookRepository;
    private final WebBookChapterRepository webBookChapterRepository;

    public Long addWebBook(WebBookRegisterForm webBookRegisterForm) {

        return webBookRepository.save(
                WebBook.builder()
                        .writerId(webBookRegisterForm.getWriterId())
                        .createdAt(LocalDateTime.now())
                        .name(webBookRegisterForm.getName())
                        .description(webBookRegisterForm.getDescription())
                        .build()
        ).getWebBookId();
    }

    public Long addWebBookChapter(WebBookChapterRegisterForm webBookChapterRegisterForm) {
        if (webBookRepository.existsById(webBookChapterRegisterForm.getWebBookId())) {
            return webBookChapterRepository.save(
                    WebBookChapter.builder()
                            .webBookId(webBookChapterRegisterForm.getWebBookId())
                            .name(webBookChapterRegisterForm.getName())
                            .detail(webBookChapterRegisterForm.getDetail())
                            .price(webBookChapterRegisterForm.getPrice())
                            .build()
            ).getWebBookChapterId();
        } else {
            return null;
        }
    }

    public List<WebBookDto> getWebBookList() {
        return webBookRepository.findAll().stream().map(webBook -> WebBookDto.from(webBook)).collect(Collectors.toList());
    }
    public List<WebBookChapterDto> getWebBookChapterList(Long webBookId){
        return webBookChapterRepository.findAllByWebBookId(webBookId)
                .stream().map(webBookChapter -> WebBookChapterDto.from(webBookChapter)).collect(Collectors.toList());
    }

    public WebBookChapterDetailDto getWebBookChapterDetail(Long webBookChapterId){
        return WebBookChapterDetailDto.from(
                webBookChapterRepository.findById(webBookChapterId).get());

    }
    //getWebBookChapterDetail
}