package cn.bos.portal.controller;

import cn.bos.content.service.ContentService;
import cn.bos.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
     * @ProjectName:    首页展示Controller
     * @Package:        cn.bos.portal
     * @ClassName:      IndexController
     * @Description:    java类作用描述
     * @Author:         胡铭锋
     * @CreateDate:     2018/5/22 18:47
     * @Version:        1.0
     */
@Controller
public class IndexController {

    @Value("${CONTENT_LUNBO_ID}")
    private Long CONTENT_LUNBO_ID;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        //查询内容列表
        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
        // 把结果传递给页面
        model.addAttribute("ad1List", ad1List);
        return "index";
    }
}