package com.mxx.article.apis;

import com.mxx.models.article.dto.ArticleHomeDto;
import com.mxx.models.common.result.ResponseResult;

public interface ArticleHomeControllerApi {

    public ResponseResult load(ArticleHomeDto dto);

    public ResponseResult loadMore(ArticleHomeDto dto);

    public ResponseResult loadNew(ArticleHomeDto dto);
}
