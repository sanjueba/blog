package com.example.blog.website.service;

import com.example.blog.website.model.Vo.ContentVo;
import com.github.pagehelper.PageInfo;

/**
 * create by liangyongsen ,date:2018/08/21
 */
public interface IContentService {
        /**
         *查询文章返回多条数据
         * @param p 当前页
         * @param limit 每页条数
         * @return ContentVo
         */
        PageInfo<ContentVo> getContents(Integer p, Integer limit);


}
