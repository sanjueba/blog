package com.example.blog.website.dao;

import com.example.blog.website.model.Vo.ContentVo;
import com.example.blog.website.model.Vo.ContentVoExample;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by liangyongsen ,date:2018/08/21
 */
@Component
public interface ContentVoMapper {
    List<ContentVo> selectByExampleWithBLOBs(ContentVoExample example);

}
