package com.mxx.article.service.Impl;

import com.mxx.article.service.AppArticleService;
import com.mxx.models.article.dto.ArticleHomeDto;
import com.mxx.models.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class appArticleServiceImpl implements AppArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {


        return null;
    }
}
