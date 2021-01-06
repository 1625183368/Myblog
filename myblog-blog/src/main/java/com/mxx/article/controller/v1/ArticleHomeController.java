package com.mxx.article.controller.v1;

import com.mxx.article.apis.ArticleHomeControllerApi;
import com.mxx.article.service.AppArticleService;
import com.mxx.common.article.constans.ArticleContans;
import com.mxx.models.article.dto.ArticleHomeDto;
import com.mxx.models.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/article")
@Slf4j
public class ArticleHomeController implements ArticleHomeControllerApi {
    @Autowired
    private AppArticleService  appArticleService;
    @Override
    @GetMapping("/load")
    public ResponseResult load(ArticleHomeDto dto) {
        return appArticleService.load(dto,ArticleContans.LOADTYPE_LOAD_MORE);
    }

    @Override
    @GetMapping("/loadmore")
    public ResponseResult loadMore(ArticleHomeDto dto) {
        return appArticleService.load(dto,ArticleContans.LOADTYPE_LOAD_NEW);
    }

    @Override
    @GetMapping("/loadnew")
    public ResponseResult loadNew(ArticleHomeDto dto) {
        return appArticleService.load(dto,ArticleContans.LOADTYPE_LOAD_NEW);
    }
}
