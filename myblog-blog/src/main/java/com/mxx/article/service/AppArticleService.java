package com.mxx.article.service;

import com.mxx.models.article.dto.ArticleHomeDto;
import com.mxx.models.common.result.ResponseResult;

public interface AppArticleService {
    public ResponseResult load(ArticleHomeDto dto,Short type);
}
