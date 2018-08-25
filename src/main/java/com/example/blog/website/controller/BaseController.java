package com.example.blog.website.controller;

import com.example.blog.website.model.Vo.UserVo;
import com.example.blog.website.utils.MapCache;

import javax.servlet.http.HttpServletRequest;

/**
 * create by liangyongsen, date 2018/8/21
 */
public abstract class BaseController {
    public static String THEME = "themes/default";
    protected MapCache cache = MapCache.single();

    /**
     * 主页的页面主题
     * @param viewName
     * @return
     */
    public String render(String viewName){ return THEME + "/" + viewName;}

    public BaseController title(HttpServletRequest request, String title){
        request.setAttribute("title",title);
        return this;
    }
    public BaseController keywords(HttpServletRequest request,String keywords){
        request.setAttribute("keywords",keywords);
        return this;
    }
   // public UserVo user(HttpServletRequest request){return TaleUtils.getLoginUser(request);}

    //public Integer getUid(HttpServletRequest request){return  this.user(request).getUid};

    public String render_404(){return "comm/error_404";}
}
