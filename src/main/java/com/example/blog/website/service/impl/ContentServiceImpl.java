package com.example.blog.website.service.impl;

import com.example.blog.website.dao.ContentVoMapper;
import com.example.blog.website.dto.Types;
import com.example.blog.website.model.Vo.ContentVo;
import com.example.blog.website.model.Vo.ContentVoExample;
import com.example.blog.website.service.IContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * create by liangyongsen,date:2018/08/21
 */
@Service
public class ContentServiceImpl implements IContentService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);
    @Resource
    private ContentVoMapper contentDao;

    @Override
    public PageInfo<ContentVo> getContents(Integer p, Integer limit) {
        LOGGER.debug("Enter getContents method");
        ContentVoExample example = new ContentVoExample();
        example.setOrderByClause("created desc");
        example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        PageHelper.startPage(p, limit);
        List<ContentVo> data = contentDao.selectByExampleWithBLOBs(example);
        PageInfo<ContentVo> pageInfo = new PageInfo<>(data);
        LOGGER.debug("Exit getContents method");
        return pageInfo;
    }
}
