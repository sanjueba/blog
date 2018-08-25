package com.example.blog.website.controller;

import com.example.blog.website.constant.WebConst;
import com.example.blog.website.model.Vo.ContentVo;
import com.example.blog.website.service.IContentService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 *Created by liangyongsen ,date 2018/8/21
 */
@Controller
public class IndexController extends BaseController{
   private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private IContentService contentService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping(value = "/")
    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "12") int limit) {
        return this.index(request, 1, limit);
    }
    /**
     * 首页分页
     *
     * @param request request
     * @param p       第几页
     * @param limit   每页大小
     * @return 主页
     */
    @GetMapping(value = "page/{p}")
    // @PathVariable:从url中取值
    public String index(HttpServletRequest request, @PathVariable int p,@RequestParam(value = "limit",defaultValue = "12") int limit){
        p = p < 0 || p > WebConst.MAX_PAGE ? 1 : p;
        PageInfo<ContentVo> articles = contentService.getContents(p, limit);
        request.setAttribute("articles", articles);
        if (p > 1) {
            this.title(request, "第" + p + "页");
        }
        return this.render("index");
    }
}
